package com.lnews.evgen.data.local.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.lnews.evgen.domain.entities.Article;
import io.reactivex.Single;
import java.util.List;

@Dao
public interface DescriptionDao {
    @Query("SELECT * FROM article WHERE category LIKE :category")
    Single<List<Article>> getAllByKey(String category);

    @Insert
    void insert(Article article);

    @Query("DELETE FROM article WHERE category LIKE :category")
    void clearByKey(String category);
}
