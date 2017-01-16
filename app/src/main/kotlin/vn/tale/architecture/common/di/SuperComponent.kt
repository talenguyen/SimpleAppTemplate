package vn.tale.architecture.common.di

/**
 * Created by Giang Nguyen on 1/14/17.
 */
interface SuperComponent {

  fun <T> plus(module: Any): T?
}