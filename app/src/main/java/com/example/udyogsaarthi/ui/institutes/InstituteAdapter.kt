package com.example.udyogsaarthi.ui.institutes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.udyogsaarthi.R
import com.example.udyogsaarthi.databinding.ItemInstituteBinding
import com.example.udyogsaarthi.model.Institute
import com.example.udyogsaarthi.model.InstituteType

class InstituteAdapter(
    private val onItemClick: (Institute) -> Unit
) : ListAdapter<Institute, InstituteAdapter.InstituteViewHolder>(InstituteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstituteViewHolder {
        val binding = ItemInstituteBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return InstituteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InstituteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class InstituteViewHolder(
        private val binding: ItemInstituteBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(institute: Institute) {
            binding.tvInstituteName.text = institute.name
            binding.tvInstituteLocation.text = institute.location
            binding.tvInstituteType.text = institute.type.label

            applyTypeBadge(institute.type)

            binding.root.setOnClickListener { onItemClick(institute) }
        }

        private fun applyTypeBadge(type: InstituteType) {
            val (bgRes, textColor) = when (type) {
                InstituteType.ITI          -> Pair(R.drawable.bg_badge_iti, "#1565C0")
                InstituteType.POLYTECHNIC  -> Pair(R.drawable.bg_badge_polytechnic, "#6A1B9A")
                InstituteType.SKILL_CENTER -> Pair(R.drawable.bg_badge_skill_center, "#2E7D32")
            }
            binding.tvInstituteType.setBackgroundResource(bgRes)
            binding.tvInstituteType.setTextColor(
                android.graphics.Color.parseColor(textColor)
            )
        }
    }

    private class InstituteDiffCallback : DiffUtil.ItemCallback<Institute>() {
        override fun areItemsTheSame(old: Institute, new: Institute) = old.id == new.id
        override fun areContentsTheSame(old: Institute, new: Institute) = old == new
    }
}
