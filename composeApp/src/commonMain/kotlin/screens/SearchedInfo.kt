package screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen

data class SearchedInfo(val id: String): Screen {



    @Composable
    override fun Content() {
        var text by remember { mutableStateOf("") }
        LazyColumn(modifier = Modifier.fillMaxSize()){
            item {
                Row(modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Center){

                    TextField(
                        value = text ,
                        onValueChange = {text = it},
                        label = {Text("Digite seu cep")}
                    )
                } }

        }
    }
}



