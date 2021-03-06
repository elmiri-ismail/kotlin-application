package com.example.myapplication

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class usermanager : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usermanager)

        val titletext:TextView=findViewById(R.id.titletext)
        val btnuser:Button=findViewById(R.id.btnuser)
        val UsernamelIn:EditText=findViewById(R.id.UsernamelIn)
        val EmailIn:EditText=findViewById(R.id.EmailIn)
        val Etat:EditText=findViewById(R.id.etats)
        val back:ImageView=findViewById(R.id.back)
        var methd:String=intent.getStringExtra("Methode").toString()
        back.setOnClickListener{ finish() }
        titletext.text = methd
        btnuser.text = methd
        val nom=intent.getStringExtra("UserName")
        when(methd){
            "Add User" -> {
                btnuser.setCompoundDrawablesWithIntrinsicBounds(resources.getDrawable(R.drawable.addicon),null,null,null)
            }
            "Update User" -> {
                btnuser.setCompoundDrawablesWithIntrinsicBounds(resources.getDrawable(R.drawable.modificon),null,null,null)
            }
        }

        var v1=false
        var v2=false

        btnuser.setOnClickListener{
            val username=UsernamelIn.text.toString()
            val email=EmailIn.text.toString().trim()
            val etat=Etat.text.toString().trim()
            if(Etat.text.toString().trim()!=""){

                val url:String="http://172.16.1.107/API%20PHP/Operations/CRUD/Create.php"

                val params=HashMap<String,String>()
                params["username"]=username
                params["email"]=email
                params["etat"]=etat
                val jO= JSONObject(params as Map<*, *>)

                val rq: RequestQueue = Volley.newRequestQueue(this)

                val jor= JsonObjectRequest(Request.Method.POST,url,jO, Response.Listener { res->
                    try {
                        if(res.getString("success").equals("1")){
                            val builder= AlertDialog.Builder(this)
                            builder.setTitle("Message d'Information :")
                            builder.setMessage("SuccessFull Register .")
                            builder.setPositiveButton("Ok") { dialogInterface: DialogInterface, i: Int ->
                                startActivity(Intent(this, Listofusers::class.java))
                                intent.putExtra("UserName",nom)
                            }.create()
                            builder.show()

                        } else { alert("Message d'Erreur !",res.getString("message")) }

                    }catch (e:Exception){
                        alert("Message d'Erreur !",""+e.message)
                    }
                }, Response.ErrorListener { err->
                    alert("Message d'Erreur !",""+err.message)
                })
                rq.add(jor)
            }else{
                alert("Message d'Erreur !","Confirm Password is Incorrect !")
            }



        }

    }

    private fun alert(title: String, message: String) {
        val builder= AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Ok",{ dialogInterface: DialogInterface, i: Int -> }).create()
        builder.show()
    }
}