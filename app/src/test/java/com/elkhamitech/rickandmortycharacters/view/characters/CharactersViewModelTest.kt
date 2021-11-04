package com.elkhamitech.rickandmortycharacters.view.characters

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.elkhamitech.rickandmortycharacters.data.repository.FakeRickAndMortyRepository
import com.elkhamitech.rickandmortycharacters.data.stub.CharactersStub
import com.elkhamitech.rickandmortycharacters.view.MainCoroutineRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

/**
 * Created by A.Elkhami on 03,November,2021
 */
@ExperimentalCoroutinesApi
class CharactersViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: CharactersViewModel
    private lateinit var repository : FakeRickAndMortyRepository

    @Before
    fun setUp() {
        repository = FakeRickAndMortyRepository()
        viewModel = CharactersViewModel(repository)
    }

    @Test
    fun `get first page characters, returns success`() = runBlockingTest {
        viewModel.getCharacters(pageNumber = 1)

        assertThat(viewModel.uiStateFlow.value.characters).isEqualTo(CharactersStub.characters.results)
    }

    @Test
    fun `get first page characters, returns error`() = runBlockingTest {
        repository.setShouldReturnError(true)

        viewModel.getCharacters(pageNumber = 1)

        assertThat(viewModel.uiStateFlow.value.message).isEqualTo("error")
    }


}