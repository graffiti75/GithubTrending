package br.cericatto.remote

import br.cericatto.data.model.ProjectEntity
import br.cericatto.remote.mapper.ProjectsResponseModelMapper
import br.cericatto.remote.model.ProjectModel
import br.cericatto.remote.model.ProjectsResponseModel
import br.cericatto.remote.service.GithubTrendingService
import br.cericatto.remote.test.factory.ProjectDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProjectsRemoteImplTest {

    private val mapper = mock<ProjectsResponseModelMapper>()
    private val service = mock<GithubTrendingService>()
    private val remote = ProjectsRemoteImpl(service, mapper)

    @Test
    fun getProjectsCompletes() {
        stubGithubTrendingServiceSearchRepositories(Observable.just(
            ProjectDataFactory.makeProjectsResponse()))
//        stubProjectsResponseModelMapperMapFromModel(any(),
//            ProjectDataFactory.makeProjectEntity())

        val testObserver = remote.getProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsCallsServer() {
        stubGithubTrendingServiceSearchRepositories(Observable.just(
            ProjectDataFactory.makeProjectsResponse()))
//        stubProjectsResponseModelMapperMapFromModel(any(),
//            ProjectDataFactory.makeProjectEntity())

        remote.getProjects().test()
        verify(service).searchRepositories(any(), any(), any())
    }

    @Test
    fun getProjectsReturnsData() {
        val response = ProjectDataFactory.makeProjectsResponse()
        stubGithubTrendingServiceSearchRepositories(Observable.just(response))
        val entities = mutableListOf<ProjectEntity>()
        response.items.forEach {
            val entity = ProjectDataFactory.makeProjectEntity()
            entities.add(entity)
            stubProjectsResponseModelMapperMapFromModel(it, entity)
        }
        val testObserver = remote.getProjects().test()
        testObserver.assertValue(entities)
    }

    @Test
    fun getProjectsCallsServerWithCorrectParameters() {
        stubGithubTrendingServiceSearchRepositories(Observable.just(
            ProjectDataFactory.makeProjectsResponse()))
//        stubProjectsResponseModelMapperMapFromModel(any(),
//            ProjectDataFactory.makeProjectEntity())

        remote.getProjects().test()
        verify(service).searchRepositories("language:kotlin", "stars", "desc")
    }

    private fun stubGithubTrendingServiceSearchRepositories(observable: Observable<ProjectsResponseModel>) {
        whenever(service.searchRepositories(any(), any(), any()))
            .thenReturn(observable)
    }

    private fun stubProjectsResponseModelMapperMapFromModel(model: ProjectModel, entity: ProjectEntity) {
        whenever(mapper.mapFromModel(model))
            .thenReturn(entity)
    }
}