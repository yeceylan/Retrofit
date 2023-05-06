package com.yonis.retrofit_restapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yonis.retrofit_restapi.R
import com.yonis.retrofit_restapi.modal.CyrptoModal
import com.yonis.retrofit_restapi.service.CyrptoAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val BaseURL="https://raw.githubusercontent.com/"
    private var CyrptoModals:ArrayList<CyrptoModal>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()
    }
    private fun loadData(){
        val retrofit =Retrofit.Builder().baseUrl(BaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            /*addConverterfactory for json file converting
              (modal must be the same names with json file)*/

        val service = retrofit.create(CyrptoAPI::class.java)
        val call = service.getData()

        call.enqueue(object : Callback<List<CyrptoModal>>{
            override fun onResponse(
                call: Call<List<CyrptoModal>>,
                response: Response<List<CyrptoModal>>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        CyrptoModals = ArrayList(it)

                        for(cyrptoModel : CyrptoModal in CyrptoModals!!){
                            println(cyrptoModel.currency)
                            println(cyrptoModel.price)
                        }
                     }
                }
            }

            override fun onFailure(call: Call<List<CyrptoModal>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}