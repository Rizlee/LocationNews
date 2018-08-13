package com.lnews.evgen.locationnews.di.modules;

import android.arch.persistence.room.Room;
import android.content.Context;
import com.lnews.evgen.data.local.db.dao.CategoryDao;
import com.lnews.evgen.data.local.db.dao.DescriptionDao;
import com.lnews.evgen.data.local.db.storage.DBStorage;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public interface RoomModule {
    String DB_NAME = "location-news-db";

    @Singleton
    @Provides
    static DBStorage providesRoomDatabase(Context context) {
        return Room.databaseBuilder(context, DBStorage.class, DB_NAME).build();
    }

    @Singleton
    @Provides
    static CategoryDao providesCategoryDao(DBStorage dbStorage) {
        return dbStorage.getCategoryDao();
    }

    @Singleton
    @Provides
    static DescriptionDao providesDescriptionDao(DBStorage dbStorage){
        return dbStorage.getDescriptionDao();
    }
}
