package vn.tale.architecture.common.base

import android.support.v4.app.Fragment
import vn.tale.architecture.common.base.MvpPresenter
import vn.tale.architecture.common.di.Injector

/**
 * Created by Giang Nguyen on 1/14/17.
 */
open class BaseFragment : Fragment() {

  private var presenter: MvpPresenter<*>? = null

  protected fun takePresenter(presenter: MvpPresenter<*>) {
    this.presenter = presenter
  }

  protected fun injector(): Injector {
    if (activity !is Injector) {
      throw IllegalStateException("host activity must implement " + Injector::class.java.name)
    }
    return activity as Injector
  }

  override fun onStop() {
    super.onStop()
    presenter?.detachView()
  }
}