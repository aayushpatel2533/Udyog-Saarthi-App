package com.example.udyogsaarthi.ui.jobfairs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.udyogsaarthi.data.JobFairRepository
import com.example.udyogsaarthi.model.JobFair

class JobFairsViewModel : ViewModel() {

    private val repository = JobFairRepository()

    private val _jobFairs = MutableLiveData<List<JobFair>>(repository.getJobFairs())
    val jobFairs: LiveData<List<JobFair>> = _jobFairs

    /** True when the list is empty (drives empty-state visibility). */
    val isEmpty: Boolean get() = _jobFairs.value.isNullOrEmpty()
}
