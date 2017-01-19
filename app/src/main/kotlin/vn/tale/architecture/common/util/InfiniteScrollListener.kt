package vn.tale.architecture.common.util

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class InfiniteScrollListener(private val linearLayoutManager: LinearLayoutManager,
    private val visibleThreshold: Int,
    private val callbacks: Runnable) : RecyclerView.OnScrollListener() {

  private var previousTotal = 0 // The total number of items in the dataset after the last load
  private var loading = true // True if we are still waiting for the last set of data to load.

  override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
    super.onScrolled(recyclerView, dx, dy)
    val visibleItemCount = recyclerView!!.childCount
    val totalItemCount = linearLayoutManager.itemCount
    val firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition()

    if (loading) {
      if (totalItemCount > previousTotal || totalItemCount == 0) {
        loading = false
        previousTotal = totalItemCount
      }
    }

    // End has been reached
    if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {
      recyclerView.post(callbacks)
      loading = true
    }
  }
}