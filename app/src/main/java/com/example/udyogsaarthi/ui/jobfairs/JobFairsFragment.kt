package com.example.udyogsaarthi.ui.jobfairs

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.udyogsaarthi.databinding.FragmentJobFairsBinding

class JobFairsFragment : Fragment() {

    private var _binding: FragmentJobFairsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: JobFairsViewModel by viewModels()
    private lateinit var adapter: JobFairAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJobFairsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeJobFairs()
    }

    private fun setupRecyclerView() {
        adapter = JobFairAdapter { fair ->
            openInBrowser(fair.registrationLink)
        }
        binding.rvJobFairs.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@JobFairsFragment.adapter
            setHasFixedSize(false)
        }
    }

    private fun observeJobFairs() {
        viewModel.jobFairs.observe(viewLifecycleOwner) { fairs ->
            adapter.submitList(fairs)

            val upcomingCount = fairs.count { it.dateMillis >= System.currentTimeMillis() }
            binding.tvFairCount.text = when {
                fairs.isEmpty()    -> "No events scheduled"
                upcomingCount == 0 -> "All events completed"
                upcomingCount == 1 -> "1 upcoming event"
                else               -> "$upcomingCount upcoming events"
            }

            if (fairs.isEmpty()) {
                binding.rvJobFairs.visibility  = View.GONE
                binding.layoutEmpty.visibility = View.VISIBLE
            } else {
                binding.rvJobFairs.visibility  = View.VISIBLE
                binding.layoutEmpty.visibility = View.GONE
            }
        }
    }

    /**
     * Opens [url] in the device's default browser.
     * Falls back gracefully if no browser is installed.
     */
    private fun openInBrowser(url: String) {
        val safeUrl = if (url.startsWith("http")) url else "https://$url"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(safeUrl))
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
