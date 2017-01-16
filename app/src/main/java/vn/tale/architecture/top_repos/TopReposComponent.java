package vn.tale.architecture.top_repos;

import dagger.Subcomponent;

/**
 * Created by Giang Nguyen on 1/14/17.
 */
@Subcomponent(modules = TopReposModule.class)
public interface TopReposComponent {

  void inject(TopReposActivity topReposActivity);
}
