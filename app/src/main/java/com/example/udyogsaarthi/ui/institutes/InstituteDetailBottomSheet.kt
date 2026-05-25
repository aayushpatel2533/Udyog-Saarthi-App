package com.example.udyogsaarthi.ui.institutes

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.udyogsaarthi.R
import com.example.udyogsaarthi.databinding.BottomSheetInstituteDetailBinding
import com.example.udyogsaarthi.model.Institute
import com.example.udyogsaarthi.model.InstituteType
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip

class InstituteDetailBottomSheet : BottomSheetDialogFragment() {

    private var _binding: BottomSheetInstituteDetailBinding? = null
    private val binding get() = _binding!!

    // Institute passed in via companion factory
    private lateinit var institute: Institute

    companion object {
        private const val ARG_ID          = "arg_id"
        private const val ARG_NAME        = "arg_name"
        private const val ARG_TYPE        = "arg_type"
        private const val ARG_LOCATION    = "arg_location"
        private const val ARG_CONTACT     = "arg_contact"
        private const val ARG_COURSES     = "arg_courses"
        private const val ARG_AFFILIATION = "arg_affiliation"
        private const val ARG_TIMINGS     = "arg_timings"

        fun newInstance(inst: Institute): InstituteDetailBottomSheet {
            return InstituteDetailBottomSheet().apply {
                arguments = Bundle().apply {
                    putString(ARG_ID, inst.id)
                    putString(ARG_NAME, inst.name)
                    putString(ARG_TYPE, inst.type.name)
                    putString(ARG_LOCATION, inst.location)
                    putString(ARG_CONTACT, inst.contact)
                    putStringArrayList(ARG_COURSES, ArrayList(inst.courses))
                    putString(ARG_AFFILIATION, inst.affiliation)
                    putString(ARG_TIMINGS, inst.timings)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, com.google.android.material.R.style.ThemeOverlay_MaterialComponents_BottomSheetDialog)
        arguments?.let { args ->
            institute = Institute(
                id          = args.getString(ARG_ID, ""),
                name        = args.getString(ARG_NAME, ""),
                type        = InstituteType.valueOf(args.getString(ARG_TYPE, InstituteType.ITI.name)!!),
                location    = args.getString(ARG_LOCATION, ""),
                contact     = args.getString(ARG_CONTACT, ""),
                courses     = args.getStringArrayList(ARG_COURSES) ?: emptyList(),
                affiliation = args.getString(ARG_AFFILIATION, ""),
                timings     = args.getString(ARG_TIMINGS, "")
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetInstituteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateDetails()
        setupCallButton()
    }

    private fun populateDetails() {
        binding.tvDetailName.text = institute.name
        binding.tvDetailLocation.text = institute.location
        binding.tvDetailAffiliation.text = institute.affiliation.ifBlank { "—" }
        binding.tvDetailTimings.text = institute.timings.ifBlank { "—" }

        // Type badge
        binding.tvDetailType.text = institute.type.label
        val (bgRes, textColor) = when (institute.type) {
            InstituteType.ITI          -> Pair(R.drawable.bg_badge_iti, "#1565C0")
            InstituteType.POLYTECHNIC  -> Pair(R.drawable.bg_badge_polytechnic, "#6A1B9A")
            InstituteType.SKILL_CENTER -> Pair(R.drawable.bg_badge_skill_center, "#2E7D32")
        }
        binding.tvDetailType.setBackgroundResource(bgRes)
        binding.tvDetailType.setTextColor(android.graphics.Color.parseColor(textColor))

        // Courses chips
        binding.chipGroupCourses.removeAllViews()
        institute.courses.forEach { course ->
            val chip = Chip(requireContext()).apply {
                text = course
                isClickable = false
                isCheckable = false
                setChipBackgroundColorResource(
                    com.google.android.material.R.color.m3_chip_background_color
                )
            }
            binding.chipGroupCourses.addView(chip)
        }
    }

    private fun setupCallButton() {
        binding.btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${institute.contact}")
            }
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
