package br.cericatto.remote

import br.cericatto.data.model.ProjectEntity
import br.cericatto.data.repository.ProjectsRemote
import br.cericatto.remote.mapper.ProjectsResponseModelMapper
import br.cericatto.remote.service.GithubTrendingService
import io.reactivex.Observable
import javax.inject.Inject

class ProjectsRemoteImpl @Inject constructor(
    private val service: GithubTrendingService,
    private val mapper: ProjectsResponseModelMapper)
    : ProjectsRemote {

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return service.searchRepositories("language:kotlin", "stars", "desc")
            .map {
                it.items.map { mapper.mapFromModel(it) }
            }
    }
}