package com.example.udyogsaarthi.data

import com.example.udyogsaarthi.model.User

object UserRepository {
    
    // In-memory storage for registered users
    private val users = mutableListOf<User>()
    
    init {
        // Add default user
        users.add(User(
            name = "Test User",
            email = "user@gmail.com",
            phone = "1234567890",
            password = "1234"
        ))
    }
    
    /**
     * Register a new user
     * @return true if registration successful, false if email already exists
     */
    fun registerUser(user: User): Boolean {
        // Check if email already exists
        if (users.any { it.email.equals(user.email, ignoreCase = true) }) {
            return false
        }
        users.add(user)
        return true
    }
    
    /**
     * Validate user credentials
     * @return User object if credentials are valid, null otherwise
     */
    fun validateCredentials(email: String, password: String): User? {
        return users.find { 
            it.email.equals(email, ignoreCase = true) && it.password == password 
        }
    }
    
    /**
     * Check if email already exists
     */
    fun isEmailRegistered(email: String): Boolean {
        return users.any { it.email.equals(email, ignoreCase = true) }
    }
    
    /**
     * Get all registered users (for debugging)
     */
    fun getAllUsers(): List<User> = users.toList()
}
