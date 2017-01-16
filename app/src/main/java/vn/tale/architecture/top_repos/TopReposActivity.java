package vn.tale.architecture.top_repos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiConsumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;
import timber.log.Timber;
import vn.tale.architecture.R;
import vn.tale.architecture.common.base.BaseActivity;
import vn.tale.architecture.data.model.IntegerPage;
import vn.tale.architecture.data.model.Repo;
import vn.tale.architecture.domain.interactor.GetTopJavaRepoInteractor;

/**
 * Created by Giang Nguyen on 1/14/17.
 */

public class TopReposActivity extends BaseActivity {

  @Inject GetTopJavaRepoInteractor getTopJavaRepoInteractor;

  private TopReposComponent topReposComponent;

  private TopReposComponent topReposComponent() {
    if (topReposComponent == null) {
      topReposComponent = superComponent().plus(new TopReposModule());
    }
    return topReposComponent;
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_top_repos);
    topReposComponent().inject(this);

    getTopJavaRepoInteractor.getTopJavaRepos(IntegerPage.Companion.getFIRST())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new BiConsumer<List<Repo>, Throwable>() {
          @Override public void accept(List<Repo> repos, Throwable throwable) throws Exception {
            if (throwable != null) {
              throwable.printStackTrace();
            } else {
              Timber.d("repos: %s", repos);
            }
          }
        });
  }
}
