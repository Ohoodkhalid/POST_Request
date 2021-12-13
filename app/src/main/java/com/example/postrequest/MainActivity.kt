package com.example.postrequest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.*


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


class MainActivity : AppCompatActivity() {
    var  userData = ArrayList<UserDetails>()
    lateinit var recView :RecyclerView
    lateinit var nameEditT : EditText
    lateinit var locationEditT : EditText
    lateinit var addButton : Button
    lateinit var updateOrDelete :Button
    lateinit var adapter:RecyclerViewAdapter
    var name = ""
    var location =""
    val TAG  = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getData()
        recView = findViewById(R.id.recView)
        adapter=RecyclerViewAdapter(userData)
        recView.adapter = adapter
        recView.layoutManager = LinearLayoutManager(applicationContext)



        nameEditT = findViewById(R.id.nameEditT)
        locationEditT = findViewById(R.id.locationEditT)
        addButton = findViewById(R.id.add)
        updateOrDelete = findViewById(R.id.updateOrDelete)


      addButton.setOnClickListener{
          name = nameEditT.text.toString()
          location = locationEditT.text.toString()
          addData()
      }

        updateOrDelete.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

    }

    fun addData() {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        var user = UserDetails(name,location,1)
        apiInterface?.addData(user)?.enqueue(object :Callback<UserDetails>{


            override fun onResponse(call: Call<UserDetails>, response: Response<UserDetails>) {
                Toast.makeText(this@MainActivity, "ADDED", Toast.LENGTH_LONG).show()


                Log.d(TAG, "onResponse: ${user.name}")


            }

            override fun onFailure(call: Call<UserDetails>, t: Throwable) {
                Toast.makeText(this@MainActivity, "ERROR", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun getData(){
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        apiInterface?.getUserData()?.enqueue(object : Callback<List<UserDetails>>  {

            override fun onResponse(call: Call<List<UserDetails>>, response: Response<List<UserDetails>>) {

              //  Log.d(TAG, "succ: ${RecipesDetails}")

                for(data in response.body()!!){
                    userData.add(data)

                }
                adapter.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<List<UserDetails>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "ERROR", Toast.LENGTH_LONG).show()
            }
        })
    }

}