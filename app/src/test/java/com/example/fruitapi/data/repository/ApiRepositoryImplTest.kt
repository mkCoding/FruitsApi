package com.example.fruitapi.data.repository

import com.example.fruitapi.data.api.ApiEndpoints
import com.example.fruitapi.data.model.Fruits
import com.example.fruitapi.data.model.FruitsItemModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import kotlin.RuntimeException

@ExperimentalCoroutinesApi
class ApiRepositoryImplTest{

    @Mock
    private lateinit var apiEndpoints:ApiEndpoints
    private lateinit var repository: ApiRepositoryImpl

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
        repository = ApiRepositoryImpl(apiEndpoints)
    }

    /*
    This test verifies that repository.getAllFruits() correctly
    retrieves and returns all expected fruits
     */
    @Test
    fun getAllFruitsTest()= runTest{
        val expectedFruits = Fruits().apply { (listOf(
            FruitsItemModel(name = "Banana"),
            FruitsItemModel(name = "Apple")
        ))}

        `when`(apiEndpoints.getAllFruits()).thenReturn(expectedFruits)

        //Act
        val actualFruits = repository.getAllFruits()

        // Assert
        assertEquals(expectedFruits, actualFruits)
        verify(apiEndpoints).getAllFruits()
    }

    /*
     This test verifies that repository.getFruitDetails() correctly
     retrieves and returns expected fruit details.
     */
    @Test
    fun getFruitDetails() = runTest {
        //The fruit in which the details are expectd
        val fruit = "Strawberry"

        //Create mock response object (expected results)
        val expectedFruitDetails = FruitsItemModel(fruit)

        //Mock the behavior of apiEndpoints.getFruitDetails(fruitName) to return
        //expectedFruitDetails when called with the specified fruitName.
        `when`(apiEndpoints.getFruitDetails(fruit)).thenReturn(expectedFruitDetails)

        //Actual
        val actualFruitDetails = repository.getFruitDetails(fruit)

        assertEquals(expectedFruitDetails, actualFruitDetails)

        verify(apiEndpoints).getFruitDetails(fruit)
    }

    @Test
    fun fruitsListEmpty()= runTest{
        //get list of empty fruits
        val expectedFruits = Fruits() //simulate the response of an empty fruit list

        //mock behavior of API endpoint getAllFruits()
        `when`(apiEndpoints.getAllFruits()).thenReturn(expectedFruits)

        //Actual results
        val actualFruits = repository.getAllFruits() //after mocking the repo above this will be empty

        assertEquals(expectedFruits, actualFruits) //assert that expected fruits is empty and actual fruits is empty

        verify(apiEndpoints).getAllFruits() //verify that getAllFruits() was called once during this test
    }

    @Test
    fun getAllFruitsNull() = runTest {
        `when`(apiEndpoints.getAllFruits()).thenReturn(null)

        val actualExpectedFruits = repository.getAllFruits()

        assertEquals(null, actualExpectedFruits) //no fruits should be returned

        verify(apiEndpoints).getAllFruits() //verify that getAllFruits was called once during this test
    }

    //Get all Fruits returns failure
    @Test
    fun getAllFruitsFailure() = runTest {
        val exception = RuntimeException("API error") //specify the runtime exception

        `when`(apiEndpoints.getAllFruits()).thenThrow(exception) //when getAllFruits() is called throw a Runtime Exception

        //Actual results
        try {
            repository.getAllFruits()
            fail("Expect Runtime exception to be thrown")
        }catch (e:RuntimeException){
            assertEquals(exception.message, e.message) //assert that an exception is thrown after getAllFruits() is called
        }


        verify(apiEndpoints).getAllFruits() //verify that getAllFruits() is called once within this test
    }






}