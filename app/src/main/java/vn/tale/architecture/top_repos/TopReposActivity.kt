package vn.tale.architecture.top_repos

import android.os.Bundle
import vn.tale.architecture.R
import vn.tale.architecture.common.base.BaseActivity
import vn.tale.architecture.common.di.Injector

/**
 * Created by Giang Nguyen on 1/14/17.
 */

class TopReposActivity : BaseActivity(), Injector {

  private var topReposComponent: TopReposComponent? = null

  private fun topReposComponent(): TopReposComponent {
    if (topReposComponent == null) {
      topReposComponent = superComponent().plus<TopReposComponent>(TopReposModule())
    }
    return topReposComponent!!
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_top_repos)
    if (savedInstanceState == null) {
      supportFragmentManager
          .beginTransaction()
          .replace(R.id.fragmentContainer, TopReposFragment())
          .commit()
    }
  }

  override fun inject(obj: Any) {
    if (obj is TopReposFragment) {
      topReposComponent().inject(obj)
    }
  }
}
