package vn.tale.architecture.pattern.list

/**
 * Created by Giang Nguyen on 1/7/17.
 */
data class LoadingItem private constructor(val id: Long) {

  companion object {
    val loadingItem = LoadingItem(System.currentTimeMillis())
  }

}
