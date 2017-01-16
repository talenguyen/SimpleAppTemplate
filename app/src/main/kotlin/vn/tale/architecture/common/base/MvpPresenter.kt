package vn.tale.architecture.common.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

/**
 * Created by Giang Nguyen on 11/14/16.
 */

abstract class MvpPresenter<MvpView> {

  private val disposables = CompositeDisposable()

  private var viewRef: WeakReference<MvpView>? = null

  val view: MvpView?
    get() = if (viewRef == null) null else viewRef!!.get()

  protected fun autoDispose(disposable: Disposable) {
    disposables.add(disposable)
  }

  fun attachView(view: MvpView) {
    this.viewRef = WeakReference(view)
  }

  fun detachView() {
    viewRef?.clear()
    disposables.clear()
  }
}
