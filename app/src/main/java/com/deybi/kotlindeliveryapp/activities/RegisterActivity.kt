package com.deybi.kotlindeliveryapp.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.deybi.kotlindeliveryapp.R
import com.deybi.kotlindeliveryapp.activities.client.home.ClientHomeActivity
import com.deybi.kotlindeliveryapp.databinding.ActivityMainBinding
import com.deybi.kotlindeliveryapp.databinding.ActivityRegisterBinding
import com.deybi.kotlindeliveryapp.models.ResponseHttp
import com.deybi.kotlindeliveryapp.models.User
import com.deybi.kotlindeliveryapp.providers.UsersProvider
import com.deybi.kotlindeliveryapp.utils.SharedPref
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    var usersProvider = UsersProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.imageviewGoToLogin.setOnClickListener {
            goToLogin()
        }
        binding.btnRegister.setOnClickListener {
            register()
        }
    }

    private fun goToLogin() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

    private fun register() {
        val name = binding.edittextName.text.toString()
        val lastName = binding.edittextLastName.text.toString()
        val email = binding.edittextEmail.text.toString()
        val phone = binding.edittextPhone.text.toString()
        val password = binding.edittextPassword.text.toString()
        val repeatPassword = binding.edittextRepeatPassword.text.toString()

        if (isValidateForm(
                name = name,
                lastName = lastName,
                email = email,
                phone = phone,
                password = password,
                repeatPassword = repeatPassword
            )
        ) {
            val user = User(
                name = name,
                lastName = lastName,
                email = email,
                phone = phone,
                password = password
            )
            usersProvider.register(user)?.enqueue(object : Callback<ResponseHttp> {
                override fun onResponse(
                    call: Call<ResponseHttp>,
                    res: Response<ResponseHttp>
                ) {
                    if (res.body()?.isSuccess == true) {
                        saveUserInSession(res.body()?.data.toString())
                        goToClientHome()
                        Toast.makeText(
                            this@RegisterActivity,
                            res.body()?.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    } else {
                        Toast.makeText(
                            this@RegisterActivity,
                            res.body()?.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }


                }

                override fun onFailure(p0: Call<ResponseHttp>, p1: Throwable) {

                    Toast.makeText(this@RegisterActivity, "Se produjo un error", Toast.LENGTH_SHORT)
                        .show()
                    Log.d("RegisterActivity", "ERROR: ${p1.message}")
                }

            })
        } else {
            Toast.makeText(this, "El formulario NO es válido", Toast.LENGTH_SHORT).show()
        }

    }

    private fun String.isEmailValidForm(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this)
            .matches()
    }

    private fun saveUserInSession(data: String) {
        val sharedPref = SharedPref(this)
        val gson = Gson()
        val user = gson.fromJson(data, User::class.java)
        sharedPref.save("user", user)
    }

    private fun goToClientHome() {
        val i = Intent(this, ClientHomeActivity::class.java)
        startActivity(i)
    }

    private fun isValidateForm(
        name: String,
        lastName: String,
        email: String,
        phone: String,
        password: String,
        repeatPassword: String
    ): Boolean {
        return isNameValid(name, lastName) &&
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