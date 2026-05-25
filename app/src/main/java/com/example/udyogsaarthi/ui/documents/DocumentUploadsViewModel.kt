package com.example.udyogsaarthi.ui.documents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.udyogsaarthi.model.DocumentItem

class DocumentUploadsViewModel : ViewModel() {

    // Master list — order is fixed, only uploadedFileName / uploadedUri change
    private val _documents = MutableLiveData<List<DocumentItem>>(buildInitialList())
    val documents: LiveData<List<DocumentItem>> = _documents

    // Count helpers observed by the fragment for the summary banner
    val uploadedCount: Int get() = _documents.value?.count { it.isUploaded } ?: 0
    val totalCount: Int    get() = _documents.value?.size ?: 0

    /**
     * Called after the user picks a file for [documentId].
     * Creates a new list with the updated item so DiffUtil detects the change.
     */
    fun markUploaded(documentId: String, fileName: String, uriString: String) {
        val current = _documents.value ?: return
        _documents.value = current.map { doc ->
            if (doc.id == documentId) doc.copy(
                uploadedFileName = fileName,
                uploadedUri      = uriString
            ) else doc
        }
    }

    /**
     * Clears the upload for [documentId] (allows re-upload).
     */
    fun clearUpload(documentId: String) {
        val current = _documents.value ?: return
        _documents.value = current.map { doc ->
            if (doc.id == documentId) doc.copy(
                uploadedFileName = null,
                uploadedUri      = null
            ) else doc
        }
    }

    private fun buildInitialList(): List<DocumentItem> = listOf(
        DocumentItem(
            id          = "aadhar",
            name        = "Aadhar Card",
            description = "Front & back scan · PDF or Image",
            isRequired  = true
        ),
        DocumentItem(
            id          = "resume",
            name        = "Resume / CV",
            description = "Latest resume · PDF preferred",
            isRequired  = true
        ),
        DocumentItem(
            id          = "marksheet",
            name        = "Marksheet",
            description = "10th / 12th or latest marksheet · PDF or Image",
            isRequired  = true
        ),
        DocumentItem(
            id          = "certificate",
            name        = "Skill / Trade Certificate",
            description = "ITI / PMKVY / any skill certificate · PDF or Image",
            isRequired  = true
        ),
        DocumentItem(
            id          = "photo",
            name        = "Passport Photo",
            description = "Recent passport-size photo · JPG or PNG",
            isRequired  = false
        ),
        DocumentItem(
            id          = "caste",
            name        = "Caste Certificate",
            description = "If applicable · PDF or Image",
            isRequired  = false
        )
    )
}
