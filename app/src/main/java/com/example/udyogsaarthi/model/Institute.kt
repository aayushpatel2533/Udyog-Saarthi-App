package com.example.udyogsaarthi.model

/**
 * Represents a vocational / skill training institute.
 */
data class Institute(
    val id: String,
    val name: String,
    val type: InstituteType,
    val location: String,
    val contact: String,
    val courses: List<String>,
    val affiliation: String = "",
    val timings: String = ""
)

enum class InstituteType(val label: String) {
    ITI("ITI"),
    POLYTECHNIC("Polytechnic"),
    SKILL_CENTER("Skill Center")
}
