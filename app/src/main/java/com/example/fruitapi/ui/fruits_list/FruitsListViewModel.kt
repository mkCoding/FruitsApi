package com.example.fruitapi.ui.fruits_list

import android.util.Log
import androidx.compose.ui.text.toUpperCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fruitapi.data.model.Fruits
import com.example.fruitapi.data.model.FruitsItemModel
import com.example.fruitapi.data.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FruitsListViewModel @Inject constructor(private val repository: ApiRepository) : ViewModel() {

    //Use Mutable Flow to hold data retrieved from API
    private val _fruitsList = MutableStateFlow<ArrayList<FruitsItemModel>>(arrayListOf())
    val fruitList: MutableStateFlow<ArrayList<FruitsItemModel>> = _fruitsList

    init {
        getAllFruits()
    }

    fun getAllFruits() {
        viewModelScope.launch {
           try {


            val allFruits = repository.getAllFruits()

            if (allFruits != null) {

                Log.d("FruitsListViewModel", allFruits.toString())
                //Transformation #1: filter (mutable flow)
                val filterFruits = allFruits.filter { it.name?.get(0) == 'B' || it.name?.get(0) == 'b'}

                //Transformation #2: map (mutable flow)
                val mappedFruits = filterFruits.map { it.copy(name = it.name?.uppercase()) }

                _fruitsList.value = ArrayList(mappedFruits)
            }

           }catch (e:Exception){
               Log.d("FruitsListViewModel", "Error Fetching Fruits")
           }

        }
    }


}