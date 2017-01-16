package vn.tale.architecture.pattern.list

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import vn.tale.architecture.pattern.list.LoadingItem.Companion.loadingItem
import vn.tale.architecture.pattern.list.RetryItem.Companion.retryItem
import vn.tale.architecture.util.CollectionX.concat
import vn.tale.architecture.util.mock

/**
 * Created by Giang Nguyen on 1/14/17.
 */
class ListPresenterTest {

  interface GetListInteractorFake : GetListInteractor<String>

  private var firstPage: Page = mock()
  private var secondPage: Page = mock()

  private var listViewMocked: ListView = mock()
  private var getListInteractor: GetListInteractor<String> = mock()

  private var presenter: ListPresenter<String>? = null

  @Before
  @Throws(Exception::class)
  fun setUp() {
    Mockito.`when`(firstPage.next()).thenReturn(secondPage)

    getListInteractor = Mockito.mock<GetListInteractorFake>(GetListInteractorFake::class.java)
    listViewMocked = Mockito.mock(ListView::class.java)

    presenter = ListPresenter(
        getListInteractor,
        firstPage,
        Schedulers.trampoline(),
        Schedulers.trampoline())

    givenItemsWhenLoad(firstPage, FIRST_PAGE_ITEMS_MOCKED)
    givenItemsWhenLoad(secondPage, SECOND_PAGE_ITEMS_MOCKED)

    presenter!!.attachView(listViewMocked)
  }

  @Test
  @Throws(Exception::class)
  fun should_not_interact_after_view_detached() {
    presenter!!.detachView()
    presenter!!.getItems()

    Mockito.verifyZeroInteractions(listViewMocked)
  }

  @Test
  @Throws(Exception::class)
  fun should_show_loading_when_load() {
    presenter!!.getItems()

    Mockito.verify(listViewMocked).showLoading()
  }

  @Test
  @Throws(Exception::class)
  fun should_show_error_when_error_occurred_during_load() {
    val error = RuntimeException()
    givenErrorWhenLoad(firstPage, error)

    presenter!!.getItems()

    Mockito.verify(listViewMocked).showError(error)
  }

  @Test
  @Throws(Exception::class)
  fun should_show_products_when_load_success() {
    presenter!!.getItems()

    Mockito.verify(listViewMocked)
        .showItems(
            concat(
                FIRST_PAGE_ITEMS_MOCKED,
                listOf(loadingItem)
            ).toList()
        )
  }

  @Test
  @Throws(Exception::class)
  fun should_not_show_loading_when_load_more() {
    presenter!!.getItems()
    Mockito.verify<ListView>(listViewMocked).showLoading()

    presenter!!.getMoreItems()
    Mockito.verify(listViewMocked, Mockito.times(1)).showLoading()
  }

  @Test
  @Throws(Exception::class)
  fun should_show_retry_when_error_occur_during_load_more() {
    givenItemsWhenLoad(firstPage, FIRST_PAGE_ITEMS_MOCKED)
    givenErrorWhenLoad(secondPage, RuntimeException())

    presenter!!.getItems()
    presenter!!.getMoreItems()

    Mockito.verify(listViewMocked).showItems(
        concat(
            FIRST_PAGE_ITEMS_MOCKED,
            listOf(retryItem)
        ).toList()
    )
  }

  @Test
  @Throws(Exception::class)
  fun should_show_appended_product_when_success_during_load_more() {
    presenter!!.getItems()
    presenter!!.getMoreItems()

    Mockito.verify(listViewMocked).showItems(
        concat(
            FIRST_PAGE_ITEMS_MOCKED,
            SECOND_PAGE_ITEMS_MOCKED,
            listOf(loadingItem)
        ).toList()
    )
  }

  private fun givenItemsWhenLoad(page: Page, products: List<String>) {
    Mockito.`when`(getListInteractor.items(page))
        .thenReturn(Single.just(products))
  }

  private fun givenErrorWhenLoad(page: Page, error: Throwable) {
    Mockito.`when`(getListInteractor.items(page))
        .thenReturn(Single.error<List<String>>(error))
  }

  companion object {

    private val FIRST_PAGE_ITEMS_MOCKED = listOf(
        "Item 1",
        "Item 2"
    )

    private val SECOND_PAGE_ITEMS_MOCKED = listOf(
        "Item 3",
        "Item 4"
    )
  }
}