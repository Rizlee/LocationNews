package com.lnews.evgen.locationnews.di.modules;

import android.content.Context;
import com.lnews.evgen.data.local.preferences.IPreferencesStorage;
import com.lnews.evgen.data.local.preferences.PreferencesStorage;
import com.lnews.evgen.data.repository.Repository;
import com.lnews.evgen.data.threads.JobExecutor;
import com.lnews.evgen.domain.executor.ExecutionThread;
import com.lnews.evgen.domain.executor.PostExecutionThread;
import com.lnews.evgen.domain.repository.IRepository;
import com.lnews.evgen.locationnews.threads.UIThread;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module(includes = AppModule.InnerAppModule.class)
public class AppModule {
    private final Context appContext;

    public AppModule(Context context) {
        appContext = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return appContext;
    }

    @Module
    public interface InnerAppModule {

        @Binds
        @Singleton
        ExecutionThread provideExecutionThread(JobExecutor jobExecutor);

        @Binds
        @Singleton
        PostExecutionThread providePostExecutionThread(UIThread uiThread);

        @Binds
        @Singleton
        IRepository provideIRepository(Repository repository);

        @Binds
        @Singleton
        IPreferencesStorage provideIPreferencesStorage(PreferencesStorage preferencesStorage);
    }
}
