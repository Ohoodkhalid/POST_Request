package com.example.postrequest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {

    lateinit var id :EditText
    lateinit var name :EditText
    lateinit var location :EditText
    lateinit var update:Button
    lateinit var delete:Button

   var idOfUser = 0
    var idOfUser1 = 0
    var nameOfUser=""
    var locationForUser=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        id = findViewById(R.id.idEditText)
        name = findViewById(R.id.nameEditText)
        location = findViewById(R.id.locationEditT)
        update = findViewById(R.id.update)
        delete = findViewById(R.id.delet)
        update.setOnClickListener{
            idOfUser =  Integer.parseInt(id.text.toString())
            nameOfUser = name.text.toString()
            locationForUser = location.text.toString()
            updateUser()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        delete.setOnClickListener{
            idOfUser =  Integer.parseInt(id.text.toString())
            deleteUser()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }


    fun updateUser(){
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        apiInterface?.updateUser(idOfUser,UserDetails(nameOfUser,locationForUser,idOfUser))?.enqueue(object :
            Callback<UserDetails> {

            override fun onResponse(call: Call<UserDetails>, response: Response<UserDetails>) {
                Toast.makeText(this@MainActivity2, "update", Toast.LENGTH_LONG).show()


             //   Log.d(TAG, "onResponse: ${user.name}")


            }

            override fun onFailure(call: Call<UserDetails>, t: Throwable) {
                Toast.makeText(this@MainActivity2, "ERROR", Toast.LENGTH_LONG).show()
            }
        })

        }

    fun deleteUser(){
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        apiInterface?.deletUser(idOfUser)?.enqueue(object : Callback<Void> {

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Toast.makeText(this@MainActivity2, "deleted the user", Toast.LENGTH_LONG).show()





            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@MainActivity2, "ERROR", Toast.LENGTH_LONG).show()
            }
        })

    }
}