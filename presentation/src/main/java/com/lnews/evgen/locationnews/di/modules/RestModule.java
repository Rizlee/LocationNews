package com.lnews.evgen.locationnews.di.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lnews.evgen.data.network.RestApi;
import com.lnews.evgen.locationnews.BuildConfig;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public interface RestModule {
    @Provides
    @Singleton
    static RestApi provideRestApi(Retrofit retrofit) {
        return retrofit.create(RestApi.class);
    }

    @Provides
    @Singleton
    static Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
            .baseUrl("http://mytasks.elatesof.w07.hoster.by/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build();
    }

    @Provides
    @Singleton
    static OkHttpClient provideOkHttpClient(HttpLoggingInterceptor logging) {
        return new OkHttpClient.Builder()
            .addInterceptor(logging)
            .build();
    }

    @Provides
    @Singleton
    static HttpLoggingInterceptor provideLogging() {
        return new HttpLoggingInterceptor().setLevel(BuildConfig.DEBUG ?
            HttpLoggingInterceptor.Level.BODY :
            HttpLoggingInterceptor.Level.NONE);
    }

    @Provides
    @Singleton
    static Gson provideGson() {
        return new GsonBuilder().setPrettyPrinting().create();
    }
}
