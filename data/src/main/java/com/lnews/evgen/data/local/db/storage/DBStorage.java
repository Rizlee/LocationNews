package com.lnews.evgen.data.local.db.storage;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.lnews.evgen.data.local.db.dao.CategoryDao;
import com.lnews.evgen.data.local.db.dao.DescriptionDao;
import com.lnews.evgen.domain.entities.Article;
import com.lnews.evgen.domain.entities.Category;
import javax.inject.Singleton;

@Singleton
@Database(entities = { Category.class, Article.class}, version = 1)
public abstract class DBStorage extends RoomDatabase {

    public abstract CategoryDao getCategoryDao();

    public abstract DescriptionDao getDescriptionDao();
}
