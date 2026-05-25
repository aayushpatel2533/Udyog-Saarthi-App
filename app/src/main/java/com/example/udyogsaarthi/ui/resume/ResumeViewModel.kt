package com.example.udyogsaarthi.ui.resume

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResumeViewModel : ViewModel() {

    // ── Personal Info ──────────────────────────────────────────────────────────
    private val _name = MutableLiveData("")
    val name: LiveData<String> = _name

    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email

    private val _phone = MutableLiveData("")
    val phone: LiveData<String> = _phone

    // ── Education ──────────────────────────────────────────────────────────────
    private val _degree = MutableLiveData("")
    val degree: LiveData<String> = _degree

    private val _college = MutableLiveData("")
    val college: LiveData<String> = _college

    private val _graduationYear = MutableLiveData("")
    val graduationYear: LiveData<String> = _graduationYear

    // ── Skills ─────────────────────────────────────────────────────────────────
    private val _skills = MutableLiveData<MutableList<String>>(mutableListOf())
    val skills: LiveData<MutableList<String>> = _skills

    // ── Experience (optional) ──────────────────────────────────────────────────
    private val _jobTitle = MutableLiveData("")
    val jobTitle: LiveData<String> = _jobTitle

    private val _company = MutableLiveData("")
    val company: LiveData<String> = _company

    private val _duration = MutableLiveData("")
    val duration: LiveData<String> = _duration

    private val _expDescription = MutableLiveData("")
    val expDescription: LiveData<String> = _expDescription

    // ── Setters ────────────────────────────────────────────────────────────────
    fun setName(v: String)             { _name.value = v }
    fun setEmail(v: String)            { _email.value = v }
    fun setPhone(v: String)            { _phone.value = v }
    fun setDegree(v: String)           { _degree.value = v }
    fun setCollege(v: String)          { _college.value = v }
    fun setGraduationYear(v: String)   { _graduationYear.value = v }
    fun setJobTitle(v: String)         { _jobTitle.value = v }
    fun setCompany(v: String)          { _company.value = v }
    fun setDuration(v: String)         { _duration.value = v }
    fun setExpDescription(v: String)   { _expDescription.value = v }

    // ── Skill management ───────────────────────────────────────────────────────
    fun addSkill(skill: String) {
        val trimmed = skill.trim()
        if (trimmed.isBlank()) return
        val current = _skills.value ?: mutableListOf()
        if (!current.contains(trimmed)) {
            current.add(trimmed)
            _skills.value = current          // trigger observers
        }
    }

    fun removeSkill(skill: String) {
        val current = _skills.value ?: return
        current.remove(skill)
        _skills.value = current
    }

    // ── Validation ─────────────────────────────────────────────────────────────
    /** Returns a non-null error message if required fields are missing, null if valid. */
    fun validate(): String? {
        if (_name.value.isNullOrBlank())    return "Please enter your name"
        if (_email.value.isNullOrBlank())   return "Please enter your email"
        if (_phone.value.isNullOrBlank())   return "Please enter your phone number"
        if (_degree.value.isNullOrBlank())  return "Please enter your degree"
        if (_college.value.isNullOrBlank()) return "Please enter your college/university"
        return null
    }

    // ── Snapshot for preview ───────────────────────────────────────────────────
    fun buildResumeData() = com.example.udyogsaarthi.model.ResumeData(
        name               = _name.value.orEmpty(),
        email              = _email.value.orEmpty(),
        phone              = _phone.value.orEmpty(),
        degree             = _degree.value.orEmpty(),
        college            = _college.value.orEmpty(),
        graduationYear     = _graduationYear.value.orEmpty(),
        skills             = _skills.value?.toList() ?: emptyList(),
        jobTitle           = _jobTitle.value.orEmpty(),
        company            = _company.value.orEmpty(),
        duration           = _duration.value.orEmpty(),
        experienceDescription = _expDescription.value.orEmpty()
    )
}
