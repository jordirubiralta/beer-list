package com.jrubiralta.beerlistapp.injector

import com.jrubiralta.beerlistapp.domain.repository.BeerRepository
import com.jrubiralta.beerlistapp.domain.usecases.GetBeerListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCasesModule {

    @Provides
    fun getBeerListUseCaseProvider(repository: BeerRepository) =
        GetBeerListUseCase(repository)

}