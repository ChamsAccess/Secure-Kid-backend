package com.cas.veritasapp.core.data.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.cas.veritasapp.core.data.entities.User;

import java.util.List;

/**
 * Created by funmiayinde on 2019-09-27.
 */
@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long save(User user);

    @Query("SELECT * FROM user")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM user WHERE email= :email LIMIT 1")
    LiveData<User> getOne(String email);

    @Delete
    void delete(User user);

}
