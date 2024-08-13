package com.example.foodplanner.util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.foodplanner.R;

public class CustomAlertDialog {
    private AlertDialog dialog;
    private Context context;

    public CustomAlertDialog(Context context) {
        this.context = context;
    }

    public void showDialog() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.auth_alert, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogView);
        dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);
    }

    public void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
