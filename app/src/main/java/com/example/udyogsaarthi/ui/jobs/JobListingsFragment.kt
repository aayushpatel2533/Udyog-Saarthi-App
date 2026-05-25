package com.example.udyogsaarthi.ui.jobs

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.udyogsaarthi.databinding.FragmentJobListingsBinding
import com.example.udyogsaarthi.utils.MarginDividerDecoration

class JobListingsFragment : Fragment() {

    private var _binding: FragmentJobListingsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: JobListingsViewModel by viewModels()
    private lateinit var jobAdapter: JobAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJobListingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSearch()
        observeJobs()
    }

    private fun setupRecyclerView() {
        jobAdapter = JobAdapter { job ->
            Toast.makeText(requireContext(), "Tapped: ${job.title}", Toast.LENGTH_SHORT).show()
        }
        binding.rvJobs.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = jobAdapter
            setHasFixedSize(false)
            isNestedScrollingEnabled = true
            addItemDecoration(MarginDividerDecoration(requireContext()))
        }
    }

    private fun setupSearch() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.search(s?.toString() ?: "")
            }
            override fun afterTextChanged(s: Editable?) = Unit
        })
    }

    private fun observeJobs() {
        viewModel.filteredJobs.observe(viewLifecycleOwner) { jobs ->
            jobAdapter.submitList(jobs)

            // Update result count label
            binding.tvResultCount.text = if (jobs.isEmpty()) "" else "${jobs.size} job${if (jobs.size == 1) "" else "s"} found"

            // Toggle empty state
            binding.rvJobs.visibility = if (jobs.isEmpty()) View.GONE else View.VISIBLE
            binding.layoutEmpty.visibility = if (jobs.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
