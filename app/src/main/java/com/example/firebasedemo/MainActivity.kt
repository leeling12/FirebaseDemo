package com.example.firebasedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnInsert: Button = findViewById(R.id.btnInsert)
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Student")

        btnInsert.setOnClickListener(){

//            myRef.setValue("Hello, World!")
            myRef.child("WA001").child("Name").setValue("Ali")
            myRef.child("WA001").child("Programme").setValue("RSF2")
            Toast.makeText(this, "Insert", Toast.LENGTH_SHORT).show();
        }

        var getData = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var sb = StringBuilder();

                for(c in snapshot.children){
                    var name = c.child("Name").getValue()
                    sb.append("${name}\n")
                }

                val txtStudent: TextView = findViewById(R.id.txtStudent)
                txtStudent.setText(sb)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        }

        val btnGet: Button = findViewById(R.id.btnGet)

        btnGet.setOnClickListener(){
            myRef.addValueEventListener(getData)
            myRef.addListenerForSingleValueEvent(getData)
        }
    }


}