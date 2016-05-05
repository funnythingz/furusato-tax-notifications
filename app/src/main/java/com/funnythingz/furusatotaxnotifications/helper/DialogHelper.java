package com.funnythingz.furusatotaxnotifications.helper;

import android.app.ProgressDialog;
import android.content.Context;

public class DialogHelper {

    public static ProgressDialog progressDialog(Context context, String title, boolean cancelable) {

        ProgressDialog progressDialog = new ProgressDialog(context);

        if (title != null) {
            progressDialog.setMessage(title);
        }

        progressDialog.setCancelable(cancelable);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        return progressDialog;
    }
}
