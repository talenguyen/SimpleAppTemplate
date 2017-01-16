package vn.tale.architecture.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import vn.tale.architecture.BuildConfig
import vn.tale.architecture.data.api.GitHubService
import javax.inject.Singleton

/**
 * Created by Giang Nguyen on 12/15/16.
 */
@Module
class DataModule {

  @Provides @Singleton internal fun provideGson(): Gson {
    return GsonBuilder()
        .create()
  }

  @Provides @Singleton internal fun provideConvertFactory(gson: Gson): Converter.Factory {
    return GsonConverterFactory.create(gson)
  }

  @Provides @Singleton internal fun provideOkHttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()
    builder.addInterceptor { chain ->
      val originalRequest = chain.request()
      val request = originalRequest.newBuilder()
          .header("Accept", "application/vnd.github.v3.full+json")
          .build()
      chain.proceed(request)
    }

    if (BuildConfig.DEBUG) {
      val logging = HttpLoggingInterceptor(
          HttpLoggingInterceptor.Logger { message -> Timber.tag("OkHttp").d(message) })
      logging.level = HttpLoggingInterceptor.Level.BASIC
      builder.addInterceptor(logging)
    }

    return builder.build()
  }

  @Provides @Singleton internal fun provideGitHubService(httpClient: OkHttpClient,
      factory: Converter.Factory): GitHubService {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .client(httpClient)
        .addConverterFactory(factory)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    return retrofit.create(GitHubService::class.java)
  }
}
