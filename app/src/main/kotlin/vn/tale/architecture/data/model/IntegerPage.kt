package vn.tale.architecture.data.model

import vn.tale.architecture.pattern.list.Page

/**
 * Created by Giang Nguyen on 1/14/17.
 */

data class IntegerPage(val index: Int) : Page {

  companion object {
    val FIRST = IntegerPage(1)
  }

  override fun next(): Page = IntegerPage(index + 1)
}
