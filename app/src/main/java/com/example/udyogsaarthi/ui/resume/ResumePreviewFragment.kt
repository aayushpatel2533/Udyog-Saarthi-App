package com.example.udyogsaarthi.ui.resume

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.udyogsaarthi.databinding.FragmentResumePreviewBinding
import com.google.android.material.chip.Chip

class ResumePreviewFragment : Fragment() {

    private var _binding: FragmentResumePreviewBinding? = null
    private val binding get() = _binding!!

    // Same ViewModel instance as the builder — no data passing needed
    private val viewModel: ResumeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResumePreviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populatePreview()
    }

    private fun populatePreview() {
        val data = viewModel.buildResumeData()

        // ── Personal Info ──────────────────────────────────────────────────────
        binding.tvPreviewName.text = data.name
        binding.tvPreviewEmail.text = data.email
        binding.tvPreviewPhone.text = data.phone

        // ── Education ─────────────────────────────────────────────────────────
        binding.tvPreviewDegree.text = data.degree
        binding.tvPreviewCollege.text = data.college
        binding.tvPreviewYear.text = if (data.graduationYear.isNotBlank())
            "Graduated: ${data.graduationYear}" else ""

        // ── Skills ────────────────────────────────────────────────────────────
        binding.chipGroupPreviewSkills.removeAllViews()
        if (data.skills.isEmpty()) {
            binding.tvNoSkills.visibility = View.VISIBLE
            binding.chipGroupPreviewSkills.visibility = View.GONE
        } else {
            binding.tvNoSkills.visibility = View.GONE
            binding.chipGroupPreviewSkills.visibility = View.VISIBLE
            data.skills.forEach { skill ->
                val chip = Chip(requireContext()).apply {
                    text = skill
                    isClickable = false
                    isCheckable = false
                    setChipBackgroundColorResource(com.google.android.material.R.color.m3_chip_background_color)
                }
                binding.chipGroupPreviewSkills.addView(chip)
            }
        }

        // ── Experience ────────────────────────────────────────────────────────
        val hasExperience = data.jobTitle.isNotBlank() || data.company.isNotBlank()
        if (hasExperience) {
            binding.layoutPreviewExperience.visibility = View.VISIBLE
            binding.tvPreviewJobTitle.text = data.jobTitle
            binding.tvPreviewExpCompany.text = data.company
            binding.tvPreviewDuration.text = data.duration
            binding.tvPreviewExpDescription.text = data.experienceDescription
        } else {
            binding.layoutPreviewExperience.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
