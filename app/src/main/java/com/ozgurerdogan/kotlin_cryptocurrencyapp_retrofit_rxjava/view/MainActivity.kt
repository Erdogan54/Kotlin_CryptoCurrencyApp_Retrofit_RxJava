package com.ozgurerdogan.kotlin_cryptocurrencyapp_retrofit_rxjava.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ozgurerdogan.kotlin_cryptocurrencyapp_retrofit_rxjava.R
import com.ozgurerdogan.kotlin_cryptocurrencyapp_retrofit_rxjava.adapter.CryptoAdapter
import com.ozgurerdogan.kotlin_cryptocurrencyapp_retrofit_rxjava.databinding.ActivityMainBinding
import com.ozgurerdogan.kotlin_cryptocurrencyapp_retrofit_rxjava.model.CryptoModel
import com.ozgurerdogan.kotlin_cryptocurrencyapp_retrofit_rxjava.service.CryptoAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val BASE_URL="https://api.nomics.com/v1/"
    private var cryptoModels:ArrayList<CryptoModel>?=null
    private var compositeDisposable= CompositeDisposable()

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
    }

    private fun loadData() {

        /*
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val service = retrofit.create(CryptoAPI::class.java)
        val call = service.getData()

        call.enqueue(object : Callback<List<CryptoModel>> {
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        cryptoModels= ArrayList(it)


                        for (model:CryptoModel in cryptoModels!!){
                            println(model.currency)
                            println(model.price)
                        }

                        binding.recyclerView.layoutManager=LinearLayoutManager(this@MainActivity)
                        binding.recyclerView.adapter=CryptoAdapter(cryptoModels!!)


                    }
                }
            }


            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })

         */

        val retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(CryptoAPI::class.java)


        compositeDisposable.add(retrofit.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse))

    }

    fun handleResponse(cryptoList: List<CryptoModel>){
        cryptoModels=ArrayList(cryptoList)

        cryptoModels?.let {
            binding.recyclerView.layoutManager=LinearLayoutManager(this@MainActivity)
            binding.recyclerView.adapter=CryptoAdapter(it)
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable.clear()
    }
}

