package com.cas.veritasapp.core.data.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.cas.veritasapp.core.data.entities.Staff;

import java.util.List;

/**
 * Created by funmiayinde on 2019-09-27.
 */

public interface StaffDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long save(Staff staff);

    @Query("SELECT * from staffs  LIMIT 1")
    LiveData<List<Staff>> findAll();

    @Query("SELECT * from staffs WHERE _id= :id LIMIT 1 ")
    LiveData<Staff> getOne(String id);

    @Delete
    LiveData<Staff> delete();


}
