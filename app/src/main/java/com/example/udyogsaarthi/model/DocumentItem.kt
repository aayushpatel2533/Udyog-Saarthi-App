package com.example.udyogsaarthi.model

/**
 * Represents a single required document in the upload list.
 *
 * @param id          Stable unique identifier — used as the key in the ViewModel map.
 * @param name        Display name shown on the card (e.g. "Aadhar Card").
 * @param description Short hint shown below the name (e.g. accepted formats).
 * @param isRequired  Whether the document is mandatory.
 * @param uploadedFileName  Non-null once the user picks a file; null = Pending.
 * @param uploadedUri       Content URI string of the picked file.
 */
data class DocumentItem(
    val id: String,
    val name: String,
    val description: String,
    val isRequired: Boolean = true,
    val uploadedFileName: String? = null,
    val uploadedUri: String? = null
) {
    val isUploaded: Boolean get() = uploadedFileName != null
}
