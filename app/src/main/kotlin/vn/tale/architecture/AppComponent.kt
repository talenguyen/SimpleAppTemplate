package vn.tale.architecture

import dagger.Component
import vn.tale.architecture.data.DataModule
import vn.tale.architecture.domain.DomainModule
import vn.tale.architecture.top_repos.TopReposComponent
import vn.tale.architecture.top_repos.TopReposModule
import javax.inject.Singleton

/**
 * Created by Giang Nguyen on 12/16/16.
 */
@Singleton
@Component(
    modules = arrayOf(
        AppModule::class,
        DataModule::class,
        DomainModule::class
    )
)
interface AppComponent {

  fun plus(topReposModule: TopReposModule): TopReposComponent
}
