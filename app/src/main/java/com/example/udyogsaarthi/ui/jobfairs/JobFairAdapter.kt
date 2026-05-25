package com.example.udyogsaarthi.ui.jobfairs

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.udyogsaarthi.R
import com.example.udyogsaarthi.databinding.ItemJobFairBinding
import com.example.udyogsaarthi.model.JobFair

class JobFairAdapter(
    private val onRegisterClick: (JobFair) -> Unit
) : ListAdapter<JobFair, JobFairAdapter.JobFairViewHolder>(JobFairDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobFairViewHolder {
        val binding = ItemJobFairBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return JobFairViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobFairViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class JobFairViewHolder(
        private val binding: ItemJobFairBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(fair: JobFair) {
            binding.tvFairTitle.text = fair.title
            binding.tvOrganizer.text = fair.organizer
            binding.tvDate.text = fair.date
            binding.tvLocation.text = fair.location
            binding.tvDescription.text = fair.description
            binding.tvEntryFee.text = fair.entryFee

            // Target audience — hide row if blank
            if (fair.targetAudience.isBlank()) {
                binding.tvTargetAudience.visibility = View.GONE
            } else {
                binding.tvTargetAudience.visibility = View.VISIBLE
                binding.tvTargetAudience.text = fair.targetAudience
            }

            // Status badge: Upcoming vs Completed
            val now = System.currentTimeMillis()
            val isUpcoming = fair.dateMillis >= now
            applyStatusBadge(isUpcoming)

            // Disable register button for past events
            binding.btnRegister.isEnabled = isUpcoming
            binding.btnRegister.alpha = if (isUpcoming) 1f else 0.45f
            binding.btnRegister.text = if (isUpcoming) "Register Now" else "Event Ended"

            binding.btnRegister.setOnClickListener {
                if (isUpcoming) onRegisterClick(fair)
            }
        }

        private fun applyStatusBadge(isUpcoming: Boolean) {
            if (isUpcoming) {
                binding.tvStatus.text = "Upcoming"
                binding.tvStatus.setBackgroundResource(R.drawable.bg_status_upcoming)
                binding.tvStatus.setTextColor(Color.parseColor("#2E7D32"))
            } else {
                binding.tvStatus.text = "Completed"
                binding.tvStatus.setBackgroundResource(R.drawable.bg_status_completed)
                binding.tvStatus.setTextColor(Color.parseColor("#9E9E9E"))
            }
        }
    }

    private class JobFairDiffCallback : DiffUtil.ItemCallback<JobFair>() {
        override fun areItemsTheSame(old: JobFair, new: JobFair) = old.id == new.id
        override fun areContentsTheSame(old: JobFair, new: JobFair) = old == new
    }
}
