package com.shoxrux.psychology_tests.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoryDao {

    @Query("select * from categoryentity")
    fun getAllCategory(): List<CategoryEntity>

    @Query("select * from categoryentity where category_position=:position")
    fun getCategoryByPosition(position:Int): CategoryEntity

    @Insert
    fun addCategory(categoryEntity: CategoryEntity)

}