package com.example.shoppinglist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class ShoppingItem(val id:Int, var name:String, var quantity:Int, val isEditing: Boolean=false)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListApp(){
    var shoppingItem by remember{ mutableStateOf(listOf<ShoppingItem>()) }
    var showDialog by remember{ mutableStateOf(false) }
    var itemName by remember{ mutableStateOf("") }
    var itemQuantity by remember{ mutableStateOf("") }

    Column(
        modifier= Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { showDialog=true },
            modifier= Modifier.align(Alignment.CenterHorizontally)){
            Text(text = "add item")
        }
        LazyColumn(modifier= Modifier
            .fillMaxSize()
            .padding(16.dp)){
            items(shoppingItem){

            }
        }
    }

    if (showDialog){
        AlertDialog(onDismissRequest = { showDialog=false },
            confirmButton = { Row(modifier = Modifier.fillMaxSize().padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Button(onClick = {
                    if (itemName.isNotBlank()) {
                        val newItem= ShoppingItem(
                            id=shoppingItem.size+1,
                            name=itemName,
                            quantity = itemQuantity.toInt()
                        )
                        shoppingItem=shoppingItem+newItem
                        itemName=""
                        showDialog=false
                    }
                    }) {
                    Text(text = "Add")
                }
                Button(onClick = { showDialog=false }) {
                    Text(text = "Cancel")
                }
            } },
            title={Text(text = "Add Shopping Item")},
            text = {
                Column {
                    OutlinedTextField(value = itemName,
                        onValueChange = {itemName=it},
                        Modifier.padding(8.dp),
                        singleLine = true,
                        placeholder = { Text(text = "Item Name")
                        }
                        )
                    OutlinedTextField(value = itemQuantity,
                        onValueChange = {itemQuantity=it},
                        Modifier.padding(8.dp),
                        singleLine=true,
                        placeholder = { Text(text = "Item Quantity")}
                        )
                }
        })
    }
}