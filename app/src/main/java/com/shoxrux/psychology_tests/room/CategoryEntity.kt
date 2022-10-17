package com.shoxrux.psychology_tests.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class CategoryEntity (



    @ColumnInfo(name = "category_name")
    var category_name: String? = null,

    @ColumnInfo(name = "category_img")
    var category_img: Int? = null,

    @ColumnInfo(name = "category_position")
    var category_position: Int?=null,

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

):Serializable