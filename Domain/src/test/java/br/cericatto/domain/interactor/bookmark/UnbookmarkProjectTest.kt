package br.cericatto.domain.interactor.bookmark

import br.cericatto.domain.executor.PostExecutionThread
import br.cericatto.domain.repository.ProjectsRepository
import br.cericatto.domain.test.ProjectDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UnbookmarkProjectTest {
    private lateinit var unbookmarkProject: UnbookmarkProject
    @Mock lateinit var projectsRepository: ProjectsRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        unbookmarkProject =  UnbookmarkProject(projectsRepository, postExecutionThread)
    }

    @Test
    fun unbookmarkProjectCompletes() {
        stubUnbookmarkProject(Completable.complete())
        val testObserver = unbookmarkProject.buildUseCaseCompletable(
            UnbookmarkProject.Params.forProject(ProjectDataFactory.randomUuid())
        ).test()
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun unbookmarkProjectThrowsException() {
        unbookmarkProject.buildUseCaseCompletable().test()
    }

    private fun stubUnbookmarkProject(completable: Completable) {
        whenever(projectsRepository.unbookmarkProject(any()))
            .thenReturn(completable)
    }
}