package vn.tale.architecture.pattern.list

/**
 * Created by Giang Nguyen on 1/7/17.
 */
data class RetryItem private constructor(val id: Long) {

  companion object {
    val retryItem = RetryItem(System.currentTimeMillis())
  }
}
