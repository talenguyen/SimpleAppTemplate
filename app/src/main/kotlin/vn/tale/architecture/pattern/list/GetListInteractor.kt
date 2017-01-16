package vn.tale.architecture.pattern.list

import io.reactivex.Single

/**
 * Created by Giang Nguyen on 1/11/17.
 */

interface GetListInteractor<T> {

  fun items(page: Page): Single<List<T>>
}
