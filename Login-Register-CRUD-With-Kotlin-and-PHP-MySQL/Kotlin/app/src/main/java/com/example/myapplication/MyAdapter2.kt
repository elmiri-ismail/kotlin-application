package com.example.myapplication

import android.content.*
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MyAdapter2 (var ctx:Context, var ressource:Int, var items:ArrayList<User>) : ArrayAdapter<User>(ctx,ressource,items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater:LayoutInflater= LayoutInflater.from(ctx)

        val view:View=layoutInflater.inflate(ressource,null)

        val txtemail:TextView=view.findViewById(R.id.textEmail)
        val txtusername:TextView=view.findViewById(R.id.textUserName)
        val txtvehicule:TextView=view.findViewById(R.id.vehicule)



        var user:User=items[position]

        txtemail.text=user.email
        txtusername.text=user.username
        txtvehicule.text=user.password





        return view
    }





}