package vn.tale.architecture

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import vn.tale.architecture.common.ImageLoader
import vn.tale.architecture.common.di.ApplicationContext

/**
 * Created by Giang Nguyen on 12/16/16.
 */
@Module
class AppModule(private val application: Application) {

  @Provides @ApplicationContext internal fun provideApplicationContext(): Context = application

  @Provides fun provideImageLoader(): ImageLoader = GlideImageLoader()
}