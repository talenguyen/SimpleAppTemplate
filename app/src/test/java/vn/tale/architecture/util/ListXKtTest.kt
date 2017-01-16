package vn.tale.architecture.util

import org.junit.Assert
import org.junit.Test

/**
 * Created by Giang Nguyen on 1/14/17.
 */
class ListXKtTest {

  @Test
  fun shouldAppending() {
    Assert.assertEquals(listOf(1, 2, 3, 4), listOf(1, 2, 3).appending(4))
  }

  @Test
  fun shouldCast() {
    Assert.assertEquals(listOf<Any>(1, 2), listOf(1, 2).cast(Any::class.java))
  }
}