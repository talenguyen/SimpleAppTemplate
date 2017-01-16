package vn.tale.architecture.pattern.list

/**
 * Created by Giang Nguyen on 12/31/16.
 */

interface ListView {

  fun showLoading()

  fun showError(error: Throwable)

  fun showItems(products: List<Any>)
}
