package com.example.fruitapi.data.api

import com.example.fruitapi.data.model.Fruits
import com.example.fruitapi.data.model.FruitsItemModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiEndpoints {

    @GET(ApiDetails.ENDPOINT_FRUITS)
    suspend fun getAllFruits():Fruits //all fruits

    @GET(ApiDetails.ENDPOINT_FRUIT_DETAILS)
    suspend fun getFruitDetails(@Path("fruitName") fruitName: String?):FruitsItemModel //specific fruit

}