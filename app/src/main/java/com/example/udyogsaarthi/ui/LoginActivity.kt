package com.example.udyogsaarthi.ui

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.udyogsaarthi.data.UserRepository
import com.example.udyogsaarthi.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnLogin.setOnClickListener {
            attemptLogin()
        }

        binding.tvRegister.setOnClickListener {
            navigateToRegister()
        }
    }

    private fun attemptLogin() {
        // Clear previous errors
        binding.tilEmail.error = null
        binding.tilPassword.error = null

        // Get input values
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        // Validate inputs
        var isValid = true

        // Email validation
        if (email.isEmpty()) {
            binding.tilEmail.error = "Email is required"
            isValid = false
        } else if (!isValidEmail(email)) {
            binding.tilEmail.error = "Please enter a valid email address"
            isValid = false
        }

        // Password validation
        if (password.isEmpty()) {
            binding.tilPassword.error = "Password is required"
            isValid = false
        }

        // If validation fails, return early
        if (!isValid) {
            return
        }

        // Validate credentials using UserRepository
        val user = UserRepository.validateCredentials(email, password)

        if (user != null) {
            // Successful login
            Toast.makeText(this, "Welcome back, ${user.name}!", Toast.LENGTH_SHORT).show()
            navigateToMainActivity()
        } else {
            // Invalid credentials
            binding.tilEmail.error = "Invalid email or password"
            binding.tilPassword.error = "Invalid email or password"
            Toast.makeText(this, "Invalid credentials. Please try again.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        // Clear the back stack so user can't go back to login screen
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun navigateToRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}
