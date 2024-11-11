package com.principe.cuellar.poketinder
import SharedPreferencesRepository
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.principe.cuellar.poketinder.LoginActivity
import com.principe.cuellar.poketinder.databinding.ActivityRegisterBinding
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var sharedPreferencesRepository: SharedPreferencesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferencesRepository = SharedPreferencesRepository().also {
            it.setSharedPreference(this)
        }

        binding.btnRegister.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            val confirmPassword = binding.edtPassword2.text.toString()

            if (!isValidEmail(email)) {
                showToast("Ingrese un email válido")
            } else if (password.length < 8) {
                showToast("La contraseña debe tener al menos 8 caracteres")
            } else if (password != confirmPassword) {
                showToast("Las contraseñas no coinciden")
            } else {
                sharedPreferencesRepository.saveUserEmail(email)
                sharedPreferencesRepository.saveUserPassword(password)
                showToast("Registro exitoso")
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

        binding.btnBackClose.setOnClickListener { finish() }

        binding.btnLoginUser.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
        return pattern.matcher(email).matches()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
