package vn.tale.architecture.top_repos

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_top_repos.*
import vn.tale.architecture.R
import vn.tale.architecture.common.ImageLoader
import vn.tale.architecture.common.base.BaseFragment
import vn.tale.architecture.common.util.InfiniteScrollListener
import vn.tale.architecture.common.viewholder.LoadMoreViewHolder
import vn.tale.architecture.common.viewholder.RetryViewHolder
import vn.tale.architecture.data.model.Repo
import vn.tale.architecture.domain.exception.NetworkException
import vn.tale.architecture.pattern.list.ListPresenter
import vn.tale.architecture.pattern.list.ListView
import vn.tale.architecture.pattern.list.LoadingItem
import vn.tale.architecture.pattern.list.RetryItem
import vn.tiki.noadapter2.OnlyAdapter
import javax.inject.Inject

/**
 * Created by Giang Nguyen on 1/15/17.
 */
class TopReposFragment : BaseFragment(), ListView {

  @Inject lateinit var presenter: ListPresenter<Repo>
  @Inject lateinit var imageLoader: ImageLoader
  private var adapter: OnlyAdapter? = null

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_top_repos, container, false)
    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    injector().inject(this)

    setupProductListView()

    takePresenter(presenter)
    presenter.attachView(this)
    presenter.getItems()
  }

  override fun showLoading() {
    vRootView.show(R.id.vLoading)
  }

  override fun showError(error: Throwable) {
    if (error is NetworkException) {
      vRootView.show(R.id.vErrorNetwork)
    } else {
      vRootView.show(R.id.vError)
    }
  }

  override fun showItems(products: List<Any>) {
    vRootView.show(R.id.rvList)
    adapter?.setItems(products)
  }

  private fun setupProductListView() {
    val layoutManager = LinearLayoutManager(
        context,
        LinearLayoutManager.VERTICAL,
        false)

    rvList.layoutManager = layoutManager

    rvList.addItemDecoration(DividerItemDecoration(
        context,
        LinearLayoutManager.VERTICAL))

    rvList.setHasFixedSize(true)

    rvList.addOnScrollListener(InfiniteScrollListener(linearLayoutManager = layoutManager,
        visibleThreshold = 3,
        callbacks = Runnable { presenter.getMoreItems() }))

    adapter = productListAdapter()
    rvList.adapter = adapter
  }

  private fun productListAdapter(): OnlyAdapter {
    return OnlyAdapter.Builder()
        .typeFactory { item ->
          when (item) {
            is LoadingItem -> 1
            is RetryItem -> 2
            else -> 0
          }
        }
        .viewHolderFactory { parent, type ->
          when (type) {
            1 -> LoadMoreViewHolder.create(parent)
            2 -> RetryViewHolder.create(parent)
            else -> RepoViewHolder.create(parent, imageLoader)
          }
        }
        .build()
  }

}