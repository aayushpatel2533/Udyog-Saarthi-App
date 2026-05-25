package com.example.udyogsaarthi.model

/**
 * Data model representing a government scheme for entrepreneurs.
 */
data class Scheme(
    val id: String,
    val name: String,
    val description: String,
    val eligibility: String = "",
    val benefits: String = "",
    val applicationUrl: String = ""
)
