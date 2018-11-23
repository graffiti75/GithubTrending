package br.cericatto.mobile_ui.injection.module

import android.app.Application
import br.cericatto.cache.ProjectsCacheImpl
import br.cericatto.cache.db.ProjectsDatabase
import br.cericatto.data.repository.ProjectsCache
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class CacheModule {
    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDataBase(application: Application): ProjectsDatabase {
            return ProjectsDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindProjectsCache(projectsCache: ProjectsCacheImpl): ProjectsCache
}