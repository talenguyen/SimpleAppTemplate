package vn.tale.architecture.util

import junit.framework.Assert.assertEquals
import org.junit.Test
import vn.tale.architecture.util.CollectionX.concat
import vn.tale.architecture.util.CollectionX.just

/**
 * Created by Giang Nguyen on 1/19/17.
 */
class CollectionXTest {

  @Test
  fun should_return_concatenated_iterable_when_concat_two_iterable_collections() {
    val concatenatedIterable = concat(listOf(1, 2, 3), listOf(4))

    assertEquals(listOf(1, 2, 3, 4), concatenatedIterable.toList())
  }

  @Test
  fun should_return_iterable_when_just_an_object() {
    val concatenatedIterable = just(1)

    assertEquals(listOf(1), concatenatedIterable.toList())
  }

}