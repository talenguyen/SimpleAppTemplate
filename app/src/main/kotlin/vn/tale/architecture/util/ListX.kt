package vn.tale.architecture.util


/**
 * Created by Giang Nguyen on 1/14/17.
 */

fun <T, D> List<T>.cast(type: Class<D>): List<D> {
  return map { it -> type.cast(it) }
}

fun <T> List<T>.appending(t: T): List<T> {
  val mutableList = toMutableList()
  mutableList.add(t)
  return mutableList.toList()
}
