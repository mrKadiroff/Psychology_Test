package com.shoxrux.psychology_tests.room.themes

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ThemesDao {

    @Query("select * from themesentity")
    fun getAllThemes(): List<ThemesEntity>

    @Query("select * from themesentity where id=:id")
    fun getThemesById(id:Int): List<ThemesEntity>

    @Insert
    fun addThemes(themesEntity: ThemesEntity)

    @Update
    fun updateTheme(themesEntity: List<ThemesEntity>)

    @Delete
    fun deleteTheme(themesEntity:ThemesEntity)

}