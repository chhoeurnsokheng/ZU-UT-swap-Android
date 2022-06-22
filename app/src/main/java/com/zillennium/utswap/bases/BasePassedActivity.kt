package com.zillennium.utswap.bases

import android.app.Activity
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.os.Build
import android.text.ClipboardManager
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast


object BasePassedActivity {
    fun onPassedBack(activity: Activity, button: ImageView) {
        button.setOnClickListener {
            val intent = Intent()
            activity.setResult(Activity.RESULT_OK, intent)
            activity.finish()
        }
    }

    fun onPassedCopy(
        context: Context,
        view: View?,
        text: String?
    ) {   // User-defined onClick Listener
        val sdk_Version = Build.VERSION.SDK_INT
        if (sdk_Version < Build.VERSION_CODES.HONEYCOMB) {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.text = text // Assuming that you are copying the text from a TextView
            //            Toast.makeText(context.getApplicationContext(), "Copied Code", Toast.LENGTH_SHORT).show();
        } else {
            val clipboard =
                context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
            val clip = ClipData.newPlainText("Text Label", text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(context.applicationContext, "Copied Code", Toast.LENGTH_SHORT).show()
        }
    }

    fun onPassedHideKeyboard(context: Context, viewGroup: ViewGroup) {
        val inputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(viewGroup.windowToken, 0)
    }
}