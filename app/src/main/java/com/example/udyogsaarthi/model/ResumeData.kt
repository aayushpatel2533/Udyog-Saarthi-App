package com.example.udyogsaarthi.model

/**
 * Holds all data entered in the Resume Builder form.
 * Passed as a navigation argument to the preview screen.
 */
data class ResumeData(
    // Personal Info
    val name: String = "",
    val email: String = "",
    val phone: String = "",

    // Education
    val degree: String = "",
    val college: String = "",
    val graduationYear: String = "",

    // Skills — stored as a list of strings
    val skills: List<String> = emptyList(),

    // Experience (optional)
    val jobTitle: String = "",
    val company: String = "",
    val duration: String = "",
    val experienceDescription: String = ""
)
