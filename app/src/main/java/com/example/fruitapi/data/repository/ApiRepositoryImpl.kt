package com.example.fruitapi.data.repository

import com.example.fruitapi.data.api.ApiEndpoints
import com.example.fruitapi.data.model.Fruits
import com.example.fruitapi.data.model.FruitsItemModel
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(private val apiEndpoints: ApiEndpoints):ApiRepository {
    override suspend fun getAllFruits(): Fruits = apiEndpoints.getAllFruits()

    override suspend fun getFruitDetails(fruitName: String): FruitsItemModel  = apiEndpoints.getFruitDetails(fruitName)

}