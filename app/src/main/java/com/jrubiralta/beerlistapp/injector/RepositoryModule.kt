package com.jrubiralta.beerlistapp.injector

import com.jrubiralta.beerlistapp.data.repositories.BeerRepositoryImpl
import com.jrubiralta.beerlistapp.domain.repository.BeerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindBeerRepository(
        repositoryImpl: BeerRepositoryImpl
    ): BeerRepository
}