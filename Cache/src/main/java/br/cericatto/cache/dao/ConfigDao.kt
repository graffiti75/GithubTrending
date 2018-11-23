package br.cericatto.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import br.cericatto.cache.db.ConfigConstants
import br.cericatto.cache.model.Config
import io.reactivex.Flowable

@Dao
abstract class ConfigDao {
    @Query(ConfigConstants.QUERY_CONFIG)
    abstract fun getConfig(): Flowable<Config>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertConfig(config: Config)
}