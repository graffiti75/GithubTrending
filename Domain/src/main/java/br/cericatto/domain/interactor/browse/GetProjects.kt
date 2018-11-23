package br.cericatto.domain.interactor.browse

import br.cericatto.domain.executor.PostExecutionThread
import br.cericatto.domain.interactor.ObservableUseCase
import br.cericatto.domain.model.Project
import br.cericatto.domain.repository.ProjectsRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetProjects @Inject constructor(
    private val projectsRepository: ProjectsRepository,
    postExecutionThread: PostExecutionThread)
    : ObservableUseCase<List<Project>, Nothing?>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return projectsRepository.getProjects()
    }
}