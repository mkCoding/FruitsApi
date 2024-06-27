package com.example.fruitapi.ui.fruits_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.example.fruitapi.data.model.FruitsItemModel
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun  FruitsListScreen(fruitsListViewModel: FruitsListViewModel = hiltViewModel()) {

    //FruitsListViewModel
    val fruitsList by fruitsListViewModel.fruitList.collectAsState() //mutable state flow

    //FruitsListFlowViewModel
    //val fruitsList by fruitsListFlowViewModel.fruitList.collectAsState(initial = emptyList()) //Flow

 Column(
     modifier = Modifier
         .fillMaxSize()
         .padding(16.dp),
     horizontalAlignment = Alignment.CenterHorizontally

 ) {

     var titleText by remember { mutableStateOf("All Fruits") }

     var textExample by rememberSaveable {
         mutableStateOf("Hello")
     }



    Text(
        text = titleText,
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier.padding(bottom = 15.dp)
    )
     Text(
         text = textExample,
         style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
         modifier = Modifier.padding(bottom = 15.dp)
     )
     HorizontalDivider(color = Color.Black, thickness = 2.dp)

     //List of Fruits
     LazyColumn  (
         modifier = Modifier
             .padding(16.dp)
             .height(900.dp)

     ){

      items(fruitsList) { fruitsItem->
          Card (
              modifier = Modifier
              .fillMaxSize()
                  .clickable {
                      textExample = "World"
                      titleText = "all vegetables"
                  }
              .padding(8.dp))

          {
              Column(

                  modifier = Modifier
                      .fillMaxWidth()
                      .padding(top = 16.dp),
                  horizontalAlignment =  Alignment.CenterHorizontally
              ) {
                  Text(
                      text = "Fruit Name: ${fruitsItem.name}",
                      modifier = Modifier.padding(bottom = 2.dp),
                      style = TextStyle(fontWeight = FontWeight.Bold)

                  )
                  Text(text = "Genus:${fruitsItem.genus} ", modifier = Modifier.padding(bottom = 2.dp))
                  Text(text = "Family: ${fruitsItem.family} ", modifier = Modifier.padding(bottom = 2.dp))
                  Text(text = "Order: ${fruitsItem.order}", modifier = Modifier.padding(bottom = 8.dp))
              }


          }

      }

     }


 }

}

@Composable
@Preview(showBackground = true)
fun PreviewFruitsListScreen(){
    //FruitsListScreen(fruitsListViewModel = MockFruitsListViewModel())
}


class MockFruitsListViewModel : ViewModel() {
    private val _fruitsList = MutableStateFlow(arrayListOf(
        FruitsItemModel("Ebenaceae","Diospyros",52,"Persimmon",null,"Rosales"),
        FruitsItemModel("Rosaceae","Fragaria",3,"Strawberry",null,"Rosales"),
        FruitsItemModel("Grossulariaceae","Ribes",69,"Gooseberry",null,"Saxifragales"),
        FruitsItemModel("Anacardiaceae","Mangifera",27,"Mango",null,"Sapindales")
    ))


    //        FruitsItemModel("Ebenaceae","Diospyros",52,"Persimmon",null,"Rosales")
    val fruitsList = _fruitsList


}