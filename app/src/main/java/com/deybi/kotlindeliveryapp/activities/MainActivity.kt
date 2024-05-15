package com.deybi.kotlindeliveryapp.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.deybi.kotlindeliveryapp.R

class MainActivity : AppCompatActivity() {
    lateinit var imageViewGoToRegister: ImageView
    lateinit var editTextEmail : EditText
    lateinit var editTextPassword : EditText
    lateinit var buttonLogin : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageViewGoToRegister = findViewById(R.id.imageview_go_to_register)
        editTextEmail = findViewById(R.id.edittext_email)
        editTextPassword = findViewById(R.id.edittext_password)
        buttonLogin = findViewById(R.id.btn_login)

        imageViewGoToRegister.setOnClickListener {
            goToRegister()
        }
        buttonLogin.setOnClickListener {
            login()
        }
    }

    private fun login(){
        val email = editTextEmail.text.toString()
        val password = editTextPassword.text.toString()
        if (isValidateForm(email,password)){
            Toast.makeText(this,"El formulario es válido", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this,"El formulario NO es válido", Toast.LENGTH_SHORT).show()
        }


    }

    private fun String.isEmailValidForm():Boolean{
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    private fun isValidateForm(email: String, password: String) : Boolean{
        return email.isNotBlank() && password.isNotBlank() && email.isEmailValidForm()
    }

    private fun goToRegister() {
        val i = Intent(this, RegisterActivity::class.java)
        startActivity(i)
    }
}