package com.example.udyogsaarthi.ui.documents

import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.udyogsaarthi.databinding.FragmentDocumentUploadsBinding
import com.google.android.material.snackbar.Snackbar

class DocumentUploadsFragment : Fragment() {

    private var _binding: FragmentDocumentUploadsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DocumentUploadsViewModel by viewModels()
    private lateinit var adapter: DocumentAdapter

    // Tracks which document id is waiting for a file pick result
    private var pendingDocumentId: String? = null

    // ── File picker launcher ───────────────────────────────────────────────────
    // Accepts PDF and common image MIME types
    private val filePickerLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri == null) {
            // User cancelled — nothing to do
            pendingDocumentId = null
            return@registerForActivityResult
        }
        val docId = pendingDocumentId ?: return@registerForActivityResult
        pendingDocumentId = null

        val fileName = resolveFileName(uri) ?: uri.lastPathSegment ?: "document"
        viewModel.markUploaded(docId, fileName, uri.toString())

        Snackbar.make(
            binding.root,
            "\"$fileName\" uploaded successfully",
            Snackbar.LENGTH_SHORT
        ).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDocumentUploadsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeDocuments()
    }

    // ── RecyclerView ──────────────────────────────────────────────────────────
    private fun setupRecyclerView() {
        adapter = DocumentAdapter(
            onUploadClick = { documentId ->
                pendingDocumentId = documentId
                // Launch picker for PDF + images
                filePickerLauncher.launch("*/*")
            },
            onClearClick = { documentId ->
                viewModel.clearUpload(documentId)
            }
        )
        binding.rvDocuments.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@DocumentUploadsFragment.adapter
            setHasFixedSize(false)
        }
    }

    // ── Observe & update UI ───────────────────────────────────────────────────
    private fun observeDocuments() {
        viewModel.documents.observe(viewLifecycleOwner) { docs ->
            adapter.submitList(docs)
            updateProgressBanner()
        }
    }

    private fun updateProgressBanner() {
        val uploaded = viewModel.uploadedCount
        val total    = viewModel.totalCount
        val percent  = if (total == 0) 0 else (uploaded * 100) / total

        binding.tvUploadSummary.text = "$uploaded of $total documents uploaded"
        binding.tvProgressText.text  = "$percent%"

        // Animate both progress indicators
        binding.progressUploads.setProgressCompat(percent, true)
        binding.progressBarLinear.setProgressCompat(percent, true)
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    /**
     * Queries the content resolver for the display name of [uri].
     * Returns null if the name cannot be resolved.
     */
    private fun resolveFileName(uri: Uri): String? {
        return try {
            requireContext().contentResolver
                .query(uri, arrayOf(OpenableColumns.DISPLAY_NAME), null, null, null)
                ?.use { cursor ->
                    if (cursor.moveToFirst()) {
                        val idx = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                        if (idx >= 0) cursor.getString(idx) else null
                    } else null
                }
        } catch (e: Exception) {
            null
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
