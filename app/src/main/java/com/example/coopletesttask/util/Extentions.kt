package com.example.coopletesttask.util

import android.os.Build
import androidx.fragment.app.Fragment

inline fun <reified T> Fragment.getParcelableCompat(key: String) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.arguments?.getParcelable(key, T::class.java)
    } else {
        this.arguments?.getParcelable(key) as? T
    } ?: throw IllegalStateException("Parcelable shouldn't be null")
