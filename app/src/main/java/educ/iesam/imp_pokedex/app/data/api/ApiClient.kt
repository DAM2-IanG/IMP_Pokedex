package educ.iesam.imp_pokedex.app.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class ApiClient {

    private val URL_BASE = "https://pokeapi.co/api/v2/"

    private val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)

}