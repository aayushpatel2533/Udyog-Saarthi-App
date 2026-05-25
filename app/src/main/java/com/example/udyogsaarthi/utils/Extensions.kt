package com.example.udyogsaarthi.utils

import android.content.Context
import android.widget.Toast

/**
 * Shows a short Toast message.
 */
fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
