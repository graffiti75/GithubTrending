package br.cericatto.mobile_ui.injection.module

import br.cericatto.data.repository.ProjectsRemote
import br.cericatto.mobile_ui.BuildConfig
import br.cericatto.remote.ProjectsRemoteImpl
import br.cericatto.remote.service.GithubTrendingService
import br.cericatto.remote.service.GithubTrendingServiceFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {
    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideGithubService(): GithubTrendingService {
            return GithubTrendingServiceFactory.makeGithubTrendingService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindProjectsRemote(projectsRemote: ProjectsRemoteImpl): ProjectsRemote
}