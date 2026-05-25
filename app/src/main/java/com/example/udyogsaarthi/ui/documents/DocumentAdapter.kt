package com.example.udyogsaarthi.ui.documents

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.udyogsaarthi.R
import com.example.udyogsaarthi.databinding.ItemDocumentBinding
import com.example.udyogsaarthi.model.DocumentItem

class DocumentAdapter(
    /** Called when the user taps "Upload" — passes the document id back to the Fragment. */
    private val onUploadClick: (documentId: String) -> Unit,
    /** Called when the user taps the ✕ on an uploaded file row. */
    private val onClearClick: (documentId: String) -> Unit
) : ListAdapter<DocumentItem, DocumentAdapter.DocumentViewHolder>(DocumentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentViewHolder {
        val binding = ItemDocumentBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return DocumentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DocumentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DocumentViewHolder(
        private val binding: ItemDocumentBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(doc: DocumentItem) {
            binding.tvDocName.text = doc.name
            binding.tvDocDescription.text = doc.description

            // Optional badge
            binding.tvOptionalBadge.visibility =
                if (doc.isRequired) View.GONE else View.VISIBLE

            if (doc.isUploaded) bindUploaded(doc) else bindPending(doc)

            // Upload button always triggers the picker
            binding.btnUpload.setOnClickListener { onUploadClick(doc.id) }

            // Clear / re-upload
            binding.ivClearUpload.setOnClickListener { onClearClick(doc.id) }
        }

        private fun bindUploaded(doc: DocumentItem) {
            // Status badge — green "Uploaded"
            binding.llStatusBadge.setBackgroundResource(R.drawable.bg_status_uploaded)
            binding.ivStatusIcon.setImageResource(android.R.drawable.checkbox_on_background)
            binding.ivStatusIcon.setColorFilter(Color.parseColor("#2E7D32"))
            binding.tvStatus.text = "Uploaded"
            binding.tvStatus.setTextColor(Color.parseColor("#2E7D32"))

            // Icon circle — green
            binding.flDocIcon.setBackgroundResource(R.drawable.bg_doc_icon_uploaded)
            binding.ivDocIcon.setColorFilter(Color.parseColor("#2E7D32"))

            // File info row
            binding.llFileInfo.visibility = View.VISIBLE
            binding.tvFileName.text = doc.uploadedFileName

            // Change button label to "Replace"
            binding.btnUpload.text = "Replace File"
        }

        private fun bindPending(doc: DocumentItem) {
            // Status badge — amber "Pending"
            binding.llStatusBadge.setBackgroundResource(R.drawable.bg_status_pending)
            binding.ivStatusIcon.setImageResource(android.R.drawable.ic_menu_close_clear_cancel)
            binding.ivStatusIcon.setColorFilter(Color.parseColor("#F57F17"))
            binding.tvStatus.text = "Pending"
            binding.tvStatus.setTextColor(Color.parseColor("#F57F17"))

            // Icon circle — blue
            binding.flDocIcon.setBackgroundResource(R.drawable.bg_doc_icon_pending)
            binding.ivDocIcon.clearColorFilter()
            binding.ivDocIcon.setColorFilter(Color.parseColor("#1565C0"))

            // Hide file info row
            binding.llFileInfo.visibility = View.GONE

            binding.btnUpload.text = "Upload Document"
        }
    }

    private class DocumentDiffCallback : DiffUtil.ItemCallback<DocumentItem>() {
        override fun areItemsTheSame(old: DocumentItem, new: DocumentItem) = old.id == new.id
        override fun areContentsTheSame(old: DocumentItem, new: DocumentItem) = old == new
    }
}
