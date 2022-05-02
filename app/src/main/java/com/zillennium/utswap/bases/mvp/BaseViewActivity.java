package com.zillennium.utswap.bases.mvp;

import android.app.Activity;
import android.widget.Toast;

public abstract class BaseViewActivity extends Activity implements IBaseView{
    @Override
    public void showToastMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setProgressBar(boolean show) {
        if (show) {
            //progressBar.setVisibility(View.VISIBLE);
        } else {
            //progressBar.setVisibility(View.GONE);
        }
    }
}
