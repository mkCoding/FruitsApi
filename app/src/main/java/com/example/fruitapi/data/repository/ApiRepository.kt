package com.example.fruitapi.data.repository

import com.example.fruitapi.data.model.Fruits
import com.example.fruitapi.data.model.FruitsItemModel

interface ApiRepository {

    suspend fun getAllFruits():Fruits //all fruits directly from Repo

    suspend fun getFruitDetails(fruitName:String):FruitsItemModel //specific fruit
}