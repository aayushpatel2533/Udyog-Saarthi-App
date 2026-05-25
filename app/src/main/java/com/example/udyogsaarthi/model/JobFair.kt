package com.example.udyogsaarthi.model

/**
 * Represents an upcoming job fair / recruitment event.
 */
data class JobFair(
    val id: String,
    val title: String,
    val organizer: String,
    val date: String,           // Display string e.g. "15 Jun 2025"
    val dateMillis: Long,       // For sorting; past events shown as "Completed"
    val location: String,
    val registrationLink: String,
    val description: String = "",
    val targetAudience: String = "",
    val entryFee: String = "Free"
)
