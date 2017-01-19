package vn.tale.architecture.top_repos

import dagger.Module
import dagger.Provides
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import vn.tale.architecture.data.model.IntegerPage
import vn.tale.architecture.data.model.Repo
import vn.tale.architecture.domain.interactor.GetTopJavaRepoInteractor
import vn.tale.architecture.pattern.list.GetListInteractor
import vn.tale.architecture.pattern.list.ListPresenter
import vn.tale.architecture.pattern.list.Page

/**
 * Created by Giang Nguyen on 1/14/17.
 */
@Module
class TopReposModule {

  @Provides fun provideGetListInteractor(
      getTopJavaRepoInteractor: GetTopJavaRepoInteractor): GetListInteractor<Repo> {
    return object : GetListInteractor<Repo> {
      override fun items(page: Page): Single<List<Repo>> {
        return getTopJavaRepoInteractor.getTopJavaRepos(page as IntegerPage)
      }
    }
  }

  @Provides fun provideTopReposPresenter(
      getListInteractor: GetListInteractor<Repo>): ListPresenter<Repo> {
    return ListPresenter(getListInteractor, IntegerPage.FIRST, Schedulers.io(),
        AndroidSchedulers.mainThread())
  }
}
