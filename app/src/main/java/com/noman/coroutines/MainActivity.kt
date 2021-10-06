package com.noman.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    val BASE_URL = "https://jsonplaceholder.typicode.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)


        lifecycleScope.launch(Dispatchers.IO) {
            var response = api.getComments()
            if (response.isSuccessful) {
                ///Success Response
                Log.d(TAG, response.body().toString())

                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, response.body().toString(), Toast.LENGTH_LONG)
                        .show()
                }
            } else {
                ///Failure Response
                Log.d(TAG, response.errorBody().toString())
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, response.errorBody().toString(), Toast.LENGTH_LONG)
                        .show()
                }
            }
        }

    }
}