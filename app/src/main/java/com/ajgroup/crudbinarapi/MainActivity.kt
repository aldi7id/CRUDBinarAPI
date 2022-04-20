package com.ajgroup.crudbinarapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ajgroup.crudbinarapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signup.setOnClickListener{
            startActivity(Intent(this@MainActivity,RegisterActivity::class.java))
        }
        setupViews()
    }

    private fun setupViews() {
        binding.apply { 
            btnLogin.setOnClickListener {
                if (!etEmail.text.isNullOrEmpty()&& !etPassword.text.isNullOrEmpty()){
                    loginAction(etEmail.text.toString(), etPassword.text.toString())
                } else {
                    Toast.makeText(this@MainActivity, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun loginAction(username: String, password: String) {
        val request = LoginRequest(
            email = username,
            password = password
        )
        ApiClient.instace.postLogin(request)
            .enqueue(object: Callback<LoginResponse>
            {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    val code = response.code()
                    if (code == 200) {
                        binding.pbLoading.visibility = View.GONE
                        Toast.makeText(this@MainActivity, "Login Berhasil", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@MainActivity,ProfileActivity::class.java))

                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            }
            )
    }
}
