package com.example.udyogsaarthi.ui.institutes

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.udyogsaarthi.databinding.FragmentInstitutesBinding
import com.example.udyogsaarthi.model.InstituteType

class InstitutesFragment : Fragment() {

    private var _binding: FragmentInstitutesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: InstitutesViewModel by viewModels()
    private lateinit var adapter: InstituteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInstitutesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSearch()
        setupFilterChips()
        observeInstitutes()
    }

    private fun setupRecyclerView() {
        adapter = InstituteAdapter { institute ->
            // Open bottom sheet on card tap
            InstituteDetailBottomSheet
                .newInstance(institute)
                .show(childFragmentManager, "institute_detail")
        }

        binding.rvInstitutes.apply {
            // 2-column grid
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = this@InstitutesFragment.adapter
            setHasFixedSize(false)
        }
    }

    private fun setupSearch() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.search(s?.toString() ?: "")
                // Reset filter chips to "All" when user types
                binding.chipAll.isChecked = true
            }
            override fun afterTextChanged(s: Editable?) = Unit
        })
    }

    private fun setupFilterChips() {
        binding.chipAll.setOnClickListener {
            binding.etSearch.text?.clear()
            viewModel.search("")
        }
        binding.chipIti.setOnClickListener {
            binding.etSearch.text?.clear()
            filterByType(InstituteType.ITI)
        }
        binding.chipPolytechnic.setOnClickListener {
            binding.etSearch.text?.clear()
            filterByType(InstituteType.POLYTECHNIC)
        }
        binding.chipSkillCenter.setOnClickListener {
            binding.etSearch.text?.clear()
            filterByType(InstituteType.SKILL_CENTER)
        }
    }

    private fun filterByType(type: InstituteType) {
        viewModel.search(type.label)
    }

    private fun observeInstitutes() {
        viewModel.institutes.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)

            val count = list.size
            binding.tvResultCount.text = if (count == 0) "" else
                "$count institute${if (count == 1) "" else "s"} found"

            binding.rvInstitutes.visibility  = if (list.isEmpty()) View.GONE else View.VISIBLE
            binding.layoutEmpty.visibility   = if (list.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
