package com.yonis.retrofit_restapi.service

import com.yonis.retrofit_restapi.modal.CyrptoModal
import retrofit2.Call
import retrofit2.http.GET

interface CyrptoAPI {
    //GET,POST,UPDATE,DELETE
    //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json
    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getData():Call<List<CyrptoModal>>

}