package com.example.udyogsaarthi.ui.resume

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.udyogsaarthi.R
import com.example.udyogsaarthi.databinding.FragmentResumeBuilderBinding
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar

class ResumeBuilderFragment : Fragment() {

    private var _binding: FragmentResumeBuilderBinding? = null
    private val binding get() = _binding!!

    // activityViewModels so the same instance is shared with the preview fragment
    private val viewModel: ResumeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResumeBuilderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        restoreFieldsFromViewModel()
        setupTextWatchers()
        setupSkillInput()
        observeSkills()
        setupPreviewButton()
    }

    // ── Restore saved state into fields after rotation ─────────────────────────
    private fun restoreFieldsFromViewModel() {
        binding.etName.setText(viewModel.name.value)
        binding.etEmail.setText(viewModel.email.value)
        binding.etPhone.setText(viewModel.phone.value)
        binding.etDegree.setText(viewModel.degree.value)
        binding.etCollege.setText(viewModel.college.value)
        binding.etYear.setText(viewModel.graduationYear.value)
        binding.etJobTitle.setText(viewModel.jobTitle.value)
        binding.etExpCompany.setText(viewModel.company.value)
        binding.etDuration.setText(viewModel.duration.value)
        binding.etExpDescription.setText(viewModel.expDescription.value)
    }

    // ── Push field changes into ViewModel ──────────────────────────────────────
    private fun setupTextWatchers() {
        binding.etName.doAfterTextChanged            { viewModel.setName(it.toString()) }
        binding.etEmail.doAfterTextChanged           { viewModel.setEmail(it.toString()) }
        binding.etPhone.doAfterTextChanged           { viewModel.setPhone(it.toString()) }
        binding.etDegree.doAfterTextChanged          { viewModel.setDegree(it.toString()) }
        binding.etCollege.doAfterTextChanged         { viewModel.setCollege(it.toString()) }
        binding.etYear.doAfterTextChanged            { viewModel.setGraduationYear(it.toString()) }
        binding.etJobTitle.doAfterTextChanged        { viewModel.setJobTitle(it.toString()) }
        binding.etExpCompany.doAfterTextChanged      { viewModel.setCompany(it.toString()) }
        binding.etDuration.doAfterTextChanged        { viewModel.setDuration(it.toString()) }
        binding.etExpDescription.doAfterTextChanged  { viewModel.setExpDescription(it.toString()) }
    }

    // ── Skill chip add/remove ──────────────────────────────────────────────────
    private fun setupSkillInput() {
        // Add on button click
        binding.btnAddSkill.setOnClickListener { addSkillFromInput() }

        // Add on keyboard "Done"
        binding.etSkill.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                addSkillFromInput()
                true
            } else false
        }
    }

    private fun addSkillFromInput() {
        val skill = binding.etSkill.text?.toString()?.trim() ?: return
        if (skill.isBlank()) {
            binding.tilSkill.error = "Enter a skill first"
            return
        }
        binding.tilSkill.error = null
        viewModel.addSkill(skill)
        binding.etSkill.text?.clear()
    }

    // ── Observe skills list and rebuild ChipGroup ──────────────────────────────
    private fun observeSkills() {
        viewModel.skills.observe(viewLifecycleOwner) { skills ->
            binding.chipGroupSkills.removeAllViews()
            skills.forEach { skill -> addChipToGroup(skill) }
        }
    }

    private fun addChipToGroup(skill: String) {
        val chip = Chip(requireContext()).apply {
            text = skill
            isCloseIconVisible = true
            isClickable = false
            setChipBackgroundColorResource(com.google.android.material.R.color.m3_chip_background_color)
            setOnCloseIconClickListener { viewModel.removeSkill(skill) }
        }
        binding.chipGroupSkills.addView(chip)
    }

    // ── Preview button ─────────────────────────────────────────────────────────
    private fun setupPreviewButton() {
        binding.btnPreviewResume.setOnClickListener {
            val error = viewModel.validate()
            if (error != null) {
                Snackbar.make(binding.root, error, Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            findNavController().navigate(R.id.action_resume_builder_to_preview)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
