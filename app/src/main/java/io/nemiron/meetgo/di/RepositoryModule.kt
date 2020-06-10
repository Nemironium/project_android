package io.nemiron.meetgo.di

import io.nemiron.meetgo.data.repositories.AuthorizationRepository
import io.nemiron.meetgo.data.repositories.TagsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AuthorizationRepository(authorizationClient = get(), authorizationManager = get()) }
    single { TagsRepository(tagsClient = get(), authorizationManager = get()) }
}
