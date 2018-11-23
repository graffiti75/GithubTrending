package br.cericatto.data.repository

import br.cericatto.data.model.ProjectEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface ProjectsDataStore {
    fun getProjects(): Observable<List<ProjectEntity>>
    fun saveProjects(projects: List<ProjectEntity>): Completable
    fun clearProjects(): Completable
    fun getBookmarkedProjects(): Observable<List<ProjectEntity>>
    fun setProjectAsBookmarked(projectId: String): Completable
    fun setProjectAsNotBookmarked(projectId: String): Completable
}