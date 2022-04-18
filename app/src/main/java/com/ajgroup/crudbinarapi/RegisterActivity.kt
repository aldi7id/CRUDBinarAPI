package com.ajgroup.crudbinarapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ajgroup.crudbinarapi.databinding.ActivityRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
    }

    private fun setupViews() {
        binding.apply {
            btnRegister.setOnClickListener {
                if (!etEmail.text.isNullOrEmpty()&& !etPassword.text.isNullOrEmpty()){
                    registerAction(etEmail.text.toString(), etPassword.text.toString(), etUsername.text.toString())
                } else {
                    Toast.makeText(this@RegisterActivity, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun registerAction(email: String, username: String, password: String) {
    val request = RegisterRequest(
        email = email,
        username = username,
        password = password
    )
        ApiClient.instace.postUser(request)
            .enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    val code = response.code()
                    if (code == 200){
                        binding.pbLoading.visibility = View.GONE
                        Toast.makeText(this@RegisterActivity, "Registrasi Berhasil", Toast.LENGTH_SHORT).show()
                        finish()
                    }else {
                        binding.pbLoading.visibility = View.GONE
                        Toast.makeText(this@RegisterActivity, "Email sudah tersedia", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                    binding.pbLoading.visibility = View.GONE
                }

            }


            )
    }
}