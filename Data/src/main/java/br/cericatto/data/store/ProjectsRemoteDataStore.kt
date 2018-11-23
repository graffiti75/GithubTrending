package br.cericatto.data.store

import br.cericatto.data.model.ProjectEntity
import br.cericatto.data.repository.ProjectsCache
import br.cericatto.data.repository.ProjectsDataStore
import br.cericatto.data.repository.ProjectsRemote
import io.reactivex.Completable
import io.reactivex.Observable
import java.lang.UnsupportedOperationException
import javax.inject.Inject

class ProjectsRemoteDataStore @Inject constructor(
    private val projectsRemote: ProjectsRemote)
    : ProjectsDataStore {

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectsRemote.getProjects()
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        throw UnsupportedOperationException("Saving projects isn't supported here...")
    }

    override fun clearProjects(): Completable {
        throw UnsupportedOperationException("Clearing projects isn't supported here...")
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        throw UnsupportedOperationException("Getting bookmarked projects isn't supported here...")
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("Saving projects isn't supported here...")
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("Saving projects isn't supported here...")
    }
}