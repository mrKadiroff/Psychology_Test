package com.shoxrux.psychology_tests.room.themes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class ThemesEntity (



    @ColumnInfo(name = "theme")
    var switched: Boolean? = null,

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

):Serializable