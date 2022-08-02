package com.example.acronym.others

import android.view.View

/**
 * Kotlin extension to show view
 */
fun View.show() {
    this.visibility = View.VISIBLE
}

/**
 * Kotlin extension to hide view
 */
fun View.hide() {
    this.visibility = View.GONE
}