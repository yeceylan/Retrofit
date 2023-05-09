package com.yonis.retrofit_restapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yonis.retrofit_restapi.R
import com.yonis.retrofit_restapi.adapter.RecyclerViewAdapter
import com.yonis.retrofit_restapi.modal.CyrptoModal
import com.yonis.retrofit_restapi.service.CyrptoAPI
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(),RecyclerViewAdapter.Listener {
    private val BaseURL="https://raw.githubusercontent.com/"
    private var CyrptoModals:ArrayList<CyrptoModal>?=null
    private var recyclerViewAdapter : RecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManager :RecyclerView.LayoutManager=LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager

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
                        CyrptoModals?.let {
                            recyclerViewAdapter=RecyclerViewAdapter(it,this@MainActivity)
                            recyclerView.adapter=recyclerViewAdapter
                        }

                     }
                }
            }

            override fun onFailure(call: Call<List<CyrptoModal>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    override fun onItemClick(cryptoModel: CyrptoModal) {
        Toast.makeText(this,"Clicked:${cryptoModel.currency}",Toast.LENGTH_LONG).show()
    }
}