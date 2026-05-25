package com.example.udyogsaarthi.ui.jobs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.udyogsaarthi.data.JobRepository
import com.example.udyogsaarthi.model.Job

class JobListingsViewModel : ViewModel() {

    private val repository = JobRepository()

    // Full unfiltered list
    private val allJobs: List<Job> = repository.getJobs()

    private val _filteredJobs = MutableLiveData<List<Job>>(allJobs)
    val filteredJobs: LiveData<List<Job>> = _filteredJobs

    /**
     * Filters jobs by matching [query] against title or company name.
     * Passing an empty/blank query resets to the full list.
     */
    fun search(query: String) {
        val trimmed = query.trim()
        _filteredJobs.value = if (trimmed.isBlank()) {
            allJobs
        } else {
            allJobs.filter { job ->
                job.title.contains(trimmed, ignoreCase = true) ||
                        job.company.contains(trimmed, ignoreCase = true)
            }
        }
    }
}
