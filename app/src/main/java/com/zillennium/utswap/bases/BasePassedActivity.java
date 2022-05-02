package com.zillennium.utswap.bases;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;


public class BasePassedActivity {
    public static void onPassedBack(Activity activity, ImageView button){
        button.setOnClickListener(view -> {
            Intent intent = new Intent();
            activity.setResult(activity.RESULT_OK, intent);
            activity.finish();
        });
    }

    public static void onPassedCopy(Context context,View view, String text) {   // User-defined onClick Listener
        int sdk_Version = android.os.Build.VERSION.SDK_INT;
        if(sdk_Version < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);   // Assuming that you are copying the text from a TextView
//            Toast.makeText(context.getApplicationContext(), "Copied Code", Toast.LENGTH_SHORT).show();
        }
        else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Text Label", text);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(context.getApplicationContext(), "Copied Code", Toast.LENGTH_SHORT).show();
        }
    }

    public static void onPassedHideKeyboard(Context context, ViewGroup viewGroup){
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(viewGroup.getWindowToken(), 0);
    }
}
