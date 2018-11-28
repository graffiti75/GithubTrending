package br.cericatto.data

import br.cericatto.data.mapper.ProjectMapper
import br.cericatto.data.repository.ProjectsCache
import br.cericatto.data.store.ProjectsDataStoreFactory
import br.cericatto.domain.model.Project
import br.cericatto.domain.repository.ProjectsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class ProjectsDataRepository @Inject constructor(
    private val mapper: ProjectMapper,
    private val cache: ProjectsCache,
    private val factory: ProjectsDataStoreFactory)
    : ProjectsRepository {

    override fun getProjects(): Observable<List<Project>> {
        return Observable.zip(
            cache.areProjectsCached().toObservable(),
            cache.isProjectsCacheExpired().toObservable(),
            BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> {
                areCached, isExpired -> Pair(areCached, isExpired)
            })
            .flatMap {
                factory.getDataStore(it.first, it.second).getProjects()
            }
            .flatMap {
                projects -> factory.getCacheDataStore()
                .saveProjects(projects)
                .andThen(Observable.just(projects))
            }
            .map { projectEntity ->
                projectEntity.map {
                    mapper.mapFromEntity(it)
                }
            }
    }

    override fun bookmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setProjectAsBookmarked(projectId)
    }

    override fun unbookmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setProjectAsNotBookmarked(projectId)
    }

    override fun getBookmarkedProjects(): Observable<List<Project>> {
        return factory.getCacheDataStore().getBookmarkedProjects()
            .map {
                it.map { projectEntity -> mapper.mapFromEntity(projectEntity) }
            }
    }
}