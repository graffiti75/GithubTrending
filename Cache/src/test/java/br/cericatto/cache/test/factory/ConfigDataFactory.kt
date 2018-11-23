package br.cericatto.cache.test.factory;

import br.cericatto.cache.model.Config

object ConfigDataFactory {
    fun makeCachedConfig(): Config {
        return Config(-1, DataFactory.randomLong())
    }
}