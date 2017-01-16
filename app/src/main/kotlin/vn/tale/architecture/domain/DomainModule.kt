package vn.tale.architecture.domain

import dagger.Module
import dagger.Provides
import vn.tale.architecture.data.api.GitHubService
import vn.tale.architecture.domain.interactor.GetTopJavaRepoInteractor

/**
 * Created by Giang Nguyen on 1/14/17.
 */
@Module
class DomainModule {

  @Provides internal fun provideGetTopJavaReposInteractor(
      gitHubService: GitHubService) = GetTopJavaRepoInteractor(gitHubService)
}
