package com.example.udyogsaarthi.model

/**
 * Represents a job listing in the Udyog Saarthi app.
 */
data class Job(
    val id: String,
    val title: String,
    val company: String,
    val location: String,
    val type: JobType,
    val description: String,
    val salary: String = ""
)

enum class JobType(val label: String) {
    FULL_TIME("Full-time"),
    PART_TIME("Part-time"),
    CONTRACT("Contract"),
    INTERNSHIP("Internship")
}
