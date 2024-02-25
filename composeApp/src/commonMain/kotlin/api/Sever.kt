package api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import model.Adress

class Sever {

    suspend fun getInfoCep(cep: String): Flow<Adress>{
        val client = HttpClient{
            install(ContentNegotiation){
                json()
            }
            install(HttpCache)
        }

        val dados = client
            .get("https://viacep.com.br/ws/$cep/json/")
            .body<Adress>()
        return MutableStateFlow(dados)
    }
}