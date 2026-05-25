package com.example.udyogsaarthi.ui.institutes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.udyogsaarthi.data.InstituteRepository
import com.example.udyogsaarthi.model.Institute

class InstitutesViewModel : ViewModel() {

    private val repository = InstituteRepository()
    private val allInstitutes = repository.getInstitutes()

    private val _institutes = MutableLiveData<List<Institute>>(allInstitutes)
    val institutes: LiveData<List<Institute>> = _institutes

    fun search(query: String) {
        val trimmed = query.trim()
        _institutes.value = if (trimmed.isBlank()) {
            allInstitutes
        } else {
            allInstitutes.filter { inst ->
                inst.name.contains(trimmed, ignoreCase = true) ||
                        inst.location.contains(trimmed, ignoreCase = true) ||
                        inst.type.label.contains(trimmed, ignoreCase = true) ||
                        inst.courses.any { it.contains(trimmed, ignoreCase = true) }
            }
        }
    }
}
