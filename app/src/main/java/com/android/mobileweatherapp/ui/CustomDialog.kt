package com.android.mobileweatherapp.ui

import android.app.Dialog
import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import androidx.core.content.res.ResourcesCompat
import com.android.mobileweatherapp.R
import com.android.mobileweatherapp.databinding.ProgressDialogViewBinding

class CustomProgressDialog {

    lateinit var dialog: CustomDialog

    fun show(context: Context): Dialog {
        return show(context, null)
    }

    fun show(context: Context, title: CharSequence?): Dialog {
        val layoutInflater = LayoutInflater.from(context)
        val binding = ProgressDialogViewBinding.inflate(layoutInflater)
        if (title != null) {
            binding.cpTitle.text = title
        }

        // Card Color
        binding.cpCardview.setCardBackgroundColor(Color.parseColor("#70000000"))

        // Progress Bar Color
        setColorFilter(binding.cpPbar.indeterminateDrawable, ResourcesCompat.getColor(context.resources, R.color.teal_200, null))

        // Text Color
        binding.cpTitle.setTextColor(Color.WHITE)

        dialog = CustomDialog(context)
        dialog.setContentView(binding.root)
        dialog.show()
        return dialog
    }

    private fun setColorFilter(drawable: Drawable, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            drawable.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
        } else {
            @Suppress("DEPRECATION")
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }
    }

    class CustomDialog(context: Context) : Dialog(context, R.style.CustomDialogTheme) {
        init {
            // Set Semi-Transparent Color for Dialog Background
            window?.decorView?.rootView?.setBackgroundColor(Color.parseColor("#99000000"))
            window?.decorView?.setOnApplyWindowInsetsListener { _, insets ->
                insets.consumeSystemWindowInsets()
            }
        }
    }
}