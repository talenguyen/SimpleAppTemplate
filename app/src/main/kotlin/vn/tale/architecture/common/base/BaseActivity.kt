package vn.tale.architecture.common.base

import android.support.v7.app.AppCompatActivity
import vn.tale.architecture.TheApp
import vn.tale.architecture.common.base.MvpPresenter
import vn.tale.architecture.common.di.Injector
import vn.tale.architecture.common.di.SuperComponent

/**
 * Created by Giang Nguyen on 1/14/17.
 */
open class BaseActivity : AppCompatActivity() {

  private var presenter: MvpPresenter<*>? = null

  protected fun takePresenter(presenter: MvpPresenter<*>) {
    this.presenter = presenter
  }

  protected fun injector(): Injector = TheApp[this]

  protected fun superComponent(): SuperComponent = TheApp[this]

  override fun onStop() {
    super.onStop()
    presenter?.detachView()
  }
}