package com.example.udyogsaarthi.ui

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.udyogsaarthi.data.UserRepository
import com.example.udyogsaarthi.databinding.ActivityRegisterBinding
import com.example.udyogsaarthi.model.User

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnRegister.setOnClickListener {
            attemptRegistration()
        }

        binding.tvLogin.setOnClickListener {
            navigateToLogin()
        }
    }

    private fun attemptRegistration() {
        // Clear previous errors
        binding.tilName.error = null
        binding.tilEmail.error = null
        binding.tilPhone.error = null
        binding.tilPassword.error = null
        binding.tilConfirmPassword.error = null

        // Get input values
        val name = binding.etName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()
        val password = binding.etPassword.text.toString()
        val confirmPassword = binding.etConfirmPassword.text.toString()

        // Validate inputs
        var isValid = true

        // Name validation
        if (name.isEmpty()) {
            binding.tilName.error = "Full name is required"
            isValid = false
        } else if (name.length < 3) {
            binding.tilName.error = "Name must be at least 3 characters"
            isValid = false
        }

        // Email validation
        if (email.isEmpty()) {
            binding.tilEmail.error = "Email is required"
            isValid = false
        } else if (!isValidEmail(email)) {
            binding.tilEmail.error = "Please enter a valid email address"
            isValid = false
        } else if (UserRepository.isEmailRegistered(email)) {
            binding.tilEmail.error = "Email already registered"
            isValid = false
        }

        // Phone validation
        if (phone.isEmpty()) {
            binding.tilPhone.error = "Phone number is required"
            isValid = false
        } else if (phone.length < 10) {
            binding.tilPhone.error = "Please enter a valid phone number"
            isValid = false
        }

        // Password validation
        if (password.isEmpty()) {
            binding.tilPassword.error = "Password is required"
            isValid = false
        } else if (password.length < 4) {
            binding.tilPassword.error = "Password must be at least 4 characters"
            isValid = false
        }

        // Confirm password validation
        if (confirmPassword.isEmpty()) {
            binding.tilConfirmPassword.error = "Please confirm your password"
            isValid = false
        } else if (password != confirmPassword) {
            binding.tilConfirmPassword.error = "Passwords do not match"
            isValid = false
        }

        // If validation fails, return early
        if (!isValid) {
            return
        }

        // Create user object
        val user = User(
            name = name,
            email = email,
            phone = phone,
            password = password
        )

        // Register user
        val registrationSuccess = UserRepository.registerUser(user)

        if (registrationSuccess) {
            Toast.makeText(
                this,
                "Registration successful! Please login.",
                Toast.LENGTH_LONG
            ).show()
            navigateToLogin()
        } else {
            // This shouldn't happen as we already checked, but just in case
            binding.tilEmail.error = "Email already registered"
            Toast.makeText(
                this,
                "Registration failed. Email already exists.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        navigateToLogin()
    }
}
