package com.example.udyogsaarthi.ui.jobs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.udyogsaarthi.R
import com.example.udyogsaarthi.databinding.ItemJobBinding
import com.example.udyogsaarthi.model.Job
import com.example.udyogsaarthi.model.JobType

class JobAdapter(
    private val onItemClick: (Job) -> Unit
) : ListAdapter<Job, JobAdapter.JobViewHolder>(JobDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val binding = ItemJobBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class JobViewHolder(private val binding: ItemJobBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(job: Job) {
            binding.tvJobTitle.text = job.title
            binding.tvCompany.text = job.company
            binding.tvLocation.text = job.location
            binding.tvDescription.text = job.description
            binding.tvSalary.text = job.salary

            // Apply job type badge style
            applyJobTypeBadge(job.type)

            binding.root.setOnClickListener { onItemClick(job) }
        }

        private fun applyJobTypeBadge(type: JobType) {
            binding.tvJobType.text = type.label
            val context = binding.root.context

            val (bgRes, textColorHex) = when (type) {
                JobType.FULL_TIME  -> Pair(R.drawable.bg_badge_full_time, "#2E7D32")
                JobType.PART_TIME  -> Pair(R.drawable.bg_badge_part_time, "#F57F17")
                JobType.CONTRACT   -> Pair(R.drawable.bg_badge_contract, "#C62828")
                JobType.INTERNSHIP -> Pair(R.drawable.bg_badge_internship, "#4527A0")
            }

            binding.tvJobType.setBackgroundResource(bgRes)
            binding.tvJobType.setTextColor(android.graphics.Color.parseColor(textColorHex))
        }
    }

    private class JobDiffCallback : DiffUtil.ItemCallback<Job>() {
        override fun areItemsTheSame(oldItem: Job, newItem: Job) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Job, newItem: Job) = oldItem == newItem
    }
}
