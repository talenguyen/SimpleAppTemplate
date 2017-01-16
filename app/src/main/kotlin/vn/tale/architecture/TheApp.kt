package vn.tale.architecture

import android.app.Application
import android.content.Context
import android.util.Log
import timber.log.Timber
import vn.tale.architecture.common.di.Injector
import vn.tale.architecture.common.di.SuperComponent
import vn.tale.architecture.data.DataModule
import vn.tale.architecture.top_repos.TopReposModule

/**
 * Created by Giang Nguyen on 12/15/16.
 */

class TheApp : Application(), Injector, SuperComponent {

  var appComponent: AppComponent? = null
    private set

  override fun onCreate() {
    super.onCreate()

    initAppComponent()

    initTimber()
  }

  private fun initAppComponent() {
    appComponent = DaggerAppComponent.builder()
        .appModule(AppModule(this))
        .dataModule(DataModule())
        .build()
  }

  private fun initTimber() {
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    } else {
      Timber.plant(CrashReportingTree())
    }
  }

  override fun inject(obj: Any) {

  }

  @Suppress("UNCHECKED_CAST")
  override fun <T> plus(module: Any): T? {
    if (module is TopReposModule) {
      return appComponent!!.plus(module) as T
    }
    return null
  }

  private inner class CrashReportingTree : Timber.Tree() {
    override fun log(priority: Int, tag: String, message: String, t: Throwable?) {
      // No-Op
      if (priority == Log.VERBOSE || priority == Log.DEBUG) {
        return
      }

      Log.println(priority, tag, message)

      if (t != null) {
        if (priority == Log.ERROR) {
          Log.e(tag, message, t)
        } else if (priority == Log.WARN) {
          Log.w(tag, message, t)
        }
      }
    }
  }

  companion object {

    operator fun get(context: Context): TheApp {
      return context.applicationContext as TheApp
    }
  }
}
