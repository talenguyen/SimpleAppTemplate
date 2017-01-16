package vn.tale.architecture.data.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import vn.tale.architecture.data.entity.SearchRepoResponse

/**
 * Created by Giang Nguyen on 1/14/17.
 */

interface GitHubService {

  @GET("/search/repositories") fun searchRepos(
      @Query("q") query: String,
      @Query("sort") sort: String,
      @Query("order") order: String,
      @Query("page") page: Int,
      @Query("per_page") perPage: Int): Single<SearchRepoResponse>
}
