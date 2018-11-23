package br.cericatto.mobile_ui.injection.module

import br.cericatto.data.ProjectsDataRepository
import br.cericatto.domain.repository.ProjectsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {
    @Binds
    abstract fun bindDataRepository(dataRepository: ProjectsDataRepository): ProjectsRepository
}