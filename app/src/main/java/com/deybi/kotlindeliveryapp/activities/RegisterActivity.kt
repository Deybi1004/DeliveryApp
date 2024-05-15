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

class RegisterActivity : AppCompatActivity() {

    lateinit var imageViewGoToLogin: ImageView
    lateinit var editTextName: EditText
    lateinit var editTextLastName: EditText
    lateinit var editTextEmail: EditText
    lateinit var editTextPhone: EditText
    lateinit var editTextPassword: EditText
    lateinit var editTextRepeatPassword: EditText
    lateinit var buttonRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        imageViewGoToLogin = findViewById(R.id.imageview_go_to_login)
        editTextName = findViewById(R.id.edittext_name)
        editTextLastName = findViewById(R.id.edittext_last_name)
        editTextEmail = findViewById(R.id.edittext_email)
        editTextPhone = findViewById(R.id.edittext_phone)
        editTextPassword = findViewById(R.id.edittext_password)
        editTextRepeatPassword = findViewById(R.id.edittext_repeat_password)
        buttonRegister = findViewById(R.id.btn_register)

        imageViewGoToLogin.setOnClickListener {
            goToLogin()
        }
        buttonRegister.setOnClickListener {
            register()
        }
    }

    private fun goToLogin() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

    private fun register() {
        val name = editTextName.text.toString()
        val lastName = editTextLastName.text.toString()
        val email = editTextEmail.text.toString()
        val phone = editTextPhone.text.toString()
        val password = editTextPassword.text.toString()
        val repeatPassword = editTextRepeatPassword.text.toString()

        if (isValidateForm(
                name = name,
                lastName = lastName,
                email = email,
                phone = phone,
                password = password,
                repeatPassword = repeatPassword
            )
        ) {
            Toast.makeText(this,"El formulario es válido", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this,"El formulario NO es válido", Toast.LENGTH_SHORT).show()
        }

    }

    private fun String.isEmailValidForm(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this)
            .matches()
    }

    private fun isValidateForm(
        name: String,
        lastName: String,
        email: String,
        phone: String,
        password: String,
        repeatPassword: String
    ): Boolean {
        return isNameValid(name,lastName) &&
                isEmailValid(email) &&
                isPhoneValid(phone) &&
                isPasswordValid(password, repeatPassword)
    }

    private fun isNameValid(name: String, lastName: String): Boolean {
        return name.isNotBlank() && lastName.isNotBlank()
    }

    private fun isEmailValid(email: String): Boolean {
        return email.isNotBlank() && email.isEmailValidForm()
    }

    private fun isPhoneValid(phone: String): Boolean {
        return phone.isNotBlank()
    }

    private fun isPasswordValid(password: String, repeatPassword: String): Boolean {
        return password.isNotBlank() &&
                repeatPassword.isNotBlank() &&
                password == repeatPassword
    }

}