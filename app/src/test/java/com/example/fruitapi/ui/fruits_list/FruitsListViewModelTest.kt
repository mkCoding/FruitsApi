package com.example.fruitapi.ui.fruits_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.fruitapi.data.model.Fruits
import com.example.fruitapi.data.model.FruitsItemModel
import com.example.fruitapi.data.repository.ApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class FruitsListViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private val testScheduler = TestCoroutineScheduler()
    private val testDispatcher = UnconfinedTestDispatcher(testScheduler)

    @Mock
    private lateinit var repository: ApiRepository //mock repository
    private lateinit var fruitListViewModel: FruitsListViewModel //mock viewModel


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        fruitListViewModel = FruitsListViewModel(repository)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()

    }

    /*
    This setup ensures that you can test the ViewModel's behavior,
    including its interaction with the repository and the transformations applied
    to the data
     */
    @Test
    fun testGetAllFruits() = runTest(testScheduler) {
        // Arrange
        val mockFruits = Fruits().apply {
            add(FruitsItemModel(name = "Banana"))
            add(FruitsItemModel(name = "Apple"))
            add(FruitsItemModel(name = "Berry"))
        }

        // Mock the repository to return the mock fruits list
        `when`(repository.getAllFruits()).thenReturn(mockFruits)

        // Act
        fruitListViewModel.getAllFruits()

        // Ensure all coroutines have completed
        advanceUntilIdle()

        // Assert
        // Collect the first emitted value from the StateFlow
        val actualFruits = fruitListViewModel.fruitList.first { it.isNotEmpty() }

        // Verify that repository.getAllFruits() was called
        verify(repository).getAllFruits()

        // Verify the transformation logic
        val expectedFruits = arrayListOf(
            FruitsItemModel(name = "BANANA"),
            FruitsItemModel(name = "BERRY")
        )

        assertEquals(expectedFruits, actualFruits)
    }

}