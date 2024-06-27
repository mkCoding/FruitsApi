package com.example.fruitapi.ui.fruits_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fruitapi.data.model.FruitsItemModel
import com.example.fruitapi.data.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FruitsListFlowViewModel @Inject constructor(val repository: ApiRepository): ViewModel() {

    // Define a Flow that emits lists of fruits
    private val fruitsListFlow: Flow<ArrayList<FruitsItemModel>> = MutableStateFlow(arrayListOf())

    // Expose the Flow as a property
    val fruitList: Flow<ArrayList<FruitsItemModel>> = fruitsListFlow

    init {
        getAllFruits()
    }

    private fun getAllFruits() {
        viewModelScope.launch {
            // Simulate fetching fruits asynchronously
            val allFruits = repository.getAllFruits()

            //val filteredFruits = allFruits.filter { (it.name?.get(0) == 'P' || it.name?.get(0) == 'p') }

            (fruitsListFlow as MutableStateFlow<ArrayList<FruitsItemModel>>).value = ArrayList(allFruits)


        }
    }

}