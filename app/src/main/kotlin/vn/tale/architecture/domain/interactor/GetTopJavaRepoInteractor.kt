package vn.tale.architecture.domain.interactor

import io.reactivex.Single
import vn.tale.architecture.data.api.GitHubService
import vn.tale.architecture.data.model.IntegerPage
import vn.tale.architecture.data.model.Repo

/**
 * Created by Giang Nguyen on 1/14/17.
 */
class GetTopJavaRepoInteractor(private val gitHubService: GitHubService) {
  companion object {
    val QUERY = "language:java"
    val SORT_BY_STARS = "stars"
  }

  fun getTopJavaRepos(page: IntegerPage): Single<List<Repo>> = gitHubService.searchRepos(QUERY,
      SORT_BY_STARS, "desc", page.index, 10)
      .map { it ->
        it.items.map { it ->
          Repo(
              it.id,
              it.name,
              it.fullName,
              it.description,
              it.htmlUrl,
              it.stargazersCount,
              it.forksCount,
              it.language,
              it.userResponse
          )
        }
      }
}