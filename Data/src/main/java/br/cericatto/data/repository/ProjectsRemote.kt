package br.cericatto.data.repository

import br.cericatto.data.model.ProjectEntity
import io.reactivex.Observable

interface ProjectsRemote {
    fun getProjects(): Observable<List<ProjectEntity>>
}