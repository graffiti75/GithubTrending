package br.cericatto.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import br.cericatto.cache.db.ConfigConstants

@Entity(tableName = ConfigConstants.TABLE_NAME)
data class Config(
    @PrimaryKey(autoGenerate = true)
    var id: Int = -1,
    var lastCacheTime: Long)

/*
@Entity(tableName = ConfigConstants.TABLE_NAME)
class Config (val lastCacheTime: Long)
*/