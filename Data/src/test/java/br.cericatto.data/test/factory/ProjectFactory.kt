package br.cericatto.data.test.factory

import br.cericatto.data.model.ProjectEntity
import br.cericatto.domain.model.Project

object ProjectFactory {
    fun makeProjectEntity(): ProjectEntity {
        return ProjectEntity(DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            false)
    }

    fun makeProject(): Project {
        return Project(DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            false)
    }
}