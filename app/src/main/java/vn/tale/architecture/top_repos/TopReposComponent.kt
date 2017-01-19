package vn.tale.architecture.top_repos

import dagger.Subcomponent

/**
 * Created by Giang Nguyen on 1/14/17.
 */
@Subcomponent(modules = arrayOf(TopReposModule::class))
interface TopReposComponent {

  fun inject(topReposFragment: TopReposFragment)
  fun inject(topReposActivity: TopReposActivity)
}
