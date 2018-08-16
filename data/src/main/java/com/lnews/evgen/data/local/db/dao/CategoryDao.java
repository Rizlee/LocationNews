package com.lnews.evgen.data.local.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.lnews.evgen.domain.entities.Category;
import io.reactivex.Completable;
import io.reactivex.Single;
import java.util.List;

@Dao
public interface CategoryDao {
    @Query("SELECT * FROM category")
    Single<List<Category>> getAll();

    @Insert
    void insert(Category category);

    @Delete
    void delete(Category category);

    @Query("DELETE FROM category")
    void nukeTable();
}
