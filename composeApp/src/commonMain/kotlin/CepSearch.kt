import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import api.Sever
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import model.Adress
import screens.SearchedInfo
import kotlinx.coroutines.coroutineScope as coroutineScope1


data class CepSearch(val id: String) : Screen {

    @Composable
    override fun Content() {
        val server = Sever()
        var cep by remember { mutableStateOf("") }
        var endereco by remember { mutableStateOf(Adress()) }
        var ifNull by remember { mutableStateOf("") }


        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = Color.Blue
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Consulta de cep",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }
                }
            }
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier.padding(10.dp).clip(RoundedCornerShape(12.dp)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    TextField(
                        value = cep,
                        onValueChange = { cep = it },
                        label = { Text("Digite seu cep") },
                        modifier = Modifier.width(200.dp).height(55.dp)
                    )


                    Button(
                        onClick = {
                            CoroutineScope(Dispatchers.Main).launch {
                                try {
                                    server.getInfoCep(cep).collect { address ->
                                        ifNull = ""
                                        endereco = address


                                    }
                                }catch (e : Exception){
                                    ifNull = "CEP incorreto ou não encontrado"
                                }

                            }
                        },
                        modifier = Modifier.clip(RoundedCornerShape(10.dp)).padding(16.dp)
                            .height(55.dp).width(120.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            backgroundColor = Color.Blue,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            "Buscar",
                            color = Color.White,
                            fontSize = 20.sp,
                        )
                    }
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    LazyColumn() {
                        item {
                            Text(
                                ifNull,
                                modifier = Modifier.padding(16.dp),
                                color = Color.Red
                            )
                            TextField(
                                value = endereco.logradouro ?: "",
                                onValueChange = {},
                                label = { Text("logradouro") },
                                modifier = Modifier.fillMaxWidth().height(60.dp)
                                    .padding(16.dp, 5.dp)
                            )

                            TextField(
                                value = endereco.complemento ?: "",
                                onValueChange = {},
                                label = { Text("Complemento") },
                                modifier = Modifier.fillMaxWidth().height(60.dp)
                                    .padding(16.dp, 5.dp)
                            )

                            TextField(
                                value = endereco.bairro ?: "",
                                onValueChange = {},
                                label = { Text("Bairro") },
                                modifier = Modifier.fillMaxWidth().height(60.dp)
                                    .padding(16.dp, 5.dp)
                            )

                            TextField(
                                value = endereco.localidade ?: "",
                                onValueChange = {},
                                label = { Text("Localidade") },
                                modifier = Modifier.fillMaxWidth().height(60.dp)
                                    .padding(16.dp, 5.dp)
                            )

                            TextField(
                                value = endereco.uf ?: "",
                                onValueChange = {},
                                label = { Text("UF") },
                                modifier = Modifier.fillMaxWidth().height(60.dp)
                                    .padding(16.dp, 5.dp)
                            )

                            TextField(
                                value = endereco.ddd ?: "",
                                onValueChange = {},
                                label = { Text("ddd") },
                                modifier = Modifier.fillMaxWidth().height(60.dp)
                                    .padding(16.dp, 5.dp)
                            )

                        }
                    }


                }


            }
        }

    }
}