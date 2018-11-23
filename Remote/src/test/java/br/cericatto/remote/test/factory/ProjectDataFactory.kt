package br.cericatto.remote.test.factory

import br.cericatto.data.model.ProjectEntity
import br.cericatto.remote.model.OwnerModel
import br.cericatto.remote.model.ProjectModel
import br.cericatto.remote.model.ProjectsResponseModel

object ProjectDataFactory {
    fun makeOwner(): OwnerModel {
        return OwnerModel(DataFactory.randomUuid(), DataFactory.randomUuid())
    }

    fun makeProject(): ProjectModel {
        return ProjectModel(DataFactory.randomUuid(), DataFactory.randomUuid(),
            DataFactory.randomUuid(), DataFactory.randomInt(),
            DataFactory.randomUuid(), makeOwner())
    }

    fun makeProjectEntity(): ProjectEntity {
        return ProjectEntity(DataFactory.randomUuid(), DataFactory.randomUuid(),
            DataFactory.randomUuid(), DataFactory.randomUuid(),
            DataFactory.randomUuid(), DataFactory.randomUuid(),
            DataFactory.randomUuid())
    }

    fun makeProjectsResponse(): ProjectsResponseModel {
        return ProjectsResponseModel(listOf(makeProject(), makeProject()))
    }
}