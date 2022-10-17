package com.shoxrux.psychology_tests.room.scrores

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ScoresDao {

    @Query("select * from scoresentity")
    fun getAllScores(): List<ScoresEntity>

    @Query("select * from ScoresEntity where sarlav=:sarlavha")
    fun getScoresByTitle(sarlavha:String): ScoresEntity

    @Insert
    fun addScores(scoresEntity: ScoresEntity)

}