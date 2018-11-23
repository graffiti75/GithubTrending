package br.cericatto.domain.interactor.browse

import br.cericatto.domain.executor.PostExecutionThread
import br.cericatto.domain.model.Project
import br.cericatto.domain.repository.ProjectsRepository
import br.cericatto.domain.test.ProjectDataFactory
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetProjectsTest {
    private lateinit var getProjects: GetProjects
    @Mock lateinit var projectsRepository: ProjectsRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getProjects = GetProjects(projectsRepository, postExecutionThread)
    }

    @Test
    fun getProjectsCompletes() {
        stubGetProjects(Observable.just(ProjectDataFactory.makeProjectList(2)))
        val testObserver = getProjects.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsReturnsData() {
        val projects = ProjectDataFactory.makeProjectList(2)
        stubGetProjects(Observable.just(projects))
        val testObserver = getProjects.buildUseCaseObservable().test()
        testObserver.assertValue(projects)
    }

    private fun stubGetProjects(observable: Observable<List<Project>>) {
        whenever(projectsRepository.getProjects())
            .thenReturn(observable)
    }
}