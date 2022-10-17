package com.shoxrux.psychology_tests.room.scrores

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class ScoresEntity (



    @ColumnInfo(name = "sarlav")
    var category_name: String? = null,

    @ColumnInfo(name = "min")
    var minimum: Int? = null,

    @ColumnInfo(name = "premidd")
    var premiddle: Int?=null,

    @ColumnInfo(name = "middle")
    var middle: Int?=null,


    @ColumnInfo(name = "high")
    var high: Int?=null,


    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

):Serializable