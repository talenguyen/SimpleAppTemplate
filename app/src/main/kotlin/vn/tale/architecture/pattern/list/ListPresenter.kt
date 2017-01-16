package vn.tale.architecture.pattern.list

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import vn.tale.architecture.common.base.MvpPresenter
import vn.tale.architecture.pattern.list.LoadingItem.Companion.loadingItem
import vn.tale.architecture.pattern.list.RetryItem.Companion.retryItem
import vn.tale.architecture.util.CollectionX.concat
import vn.tale.architecture.util.CollectionX.just
import vn.tale.architecture.util.cast
import java.util.*

/**
 * Created by Giang Nguyen on 12/31/16.
 */

class ListPresenter<T>(private val interactor: GetListInteractor<T>, private var nextPage: Page,
    private val workerScheduler: Scheduler,
    private val uiScheduler: Scheduler) : MvpPresenter<ListView>() {
  private var items: List<T> = emptyList()

  fun getItems() {
    if (view == null) {
      return
    }
    view?.showLoading()

    autoDispose(interactor.items(nextPage)
        .doOnSuccess {
          cache(it)
          increasePage()
        }
        .map { repos ->
          concat(
              repos.cast(Any::class.java),
              just(loadingItem)
          ).toList()
        }
        .subscribeOn(workerScheduler)
        .observeOn(uiScheduler)
        .subscribe({ items ->
          view?.showItems(items)
        }, { throwable ->
          view?.showError(throwable)
        }))
  }

  fun getMoreItems() {
    if (view == null) {
      return
    }

    val loadProducts = Single.zip(
        Single.just(items),
        interactor.items(nextPage),
        BiFunction<List<T>, List<T>, List<T>> { items1, items2 ->
          concat(
              items1,
              items2
          ).toList()
        })

    autoDispose(loadProducts
        .doOnSuccess {
          cache(it)
          increasePage()
        }
        .subscribeOn(workerScheduler)
        .observeOn(uiScheduler)
        .map { repos ->
          concat(
              repos.cast(Any::class.java),
              just(loadingItem)
          ).toList()
        }
        .subscribe({ items ->
          view?.showItems(items)
        }) { throwable ->
          when (throwable) {
            is NoSuchElementException -> view?.showItems(
                this@ListPresenter.items.cast(Any::class.java))
            else -> view?.showItems(
                concat(
                    this@ListPresenter.items.cast(Any::class.java),
                    just(retryItem)
                ).toList()
            )
          }
        })
  }

  private fun increasePage() {
    nextPage = nextPage.next()
  }

  private fun cache(items: List<T>) {
    this.items = items
  }
}
