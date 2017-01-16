package vn.tale.architecture.util

import java.util.*

/**
 * Created by Giang Nguyen on 1/14/17.
 */

object CollectionX {

  fun <T> just(element: T): Iterable<T> = listOf(element)

  fun <T> concat(vararg iterables: Iterable<T>): Iterable<T> {
    val result = ArrayList<T>()
    for (iterable in iterables) {
      result.addAll(iterable)
    }
    return result
  }
}
