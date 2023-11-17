package com.simtech.app1.apputils;

import static com.simtech.app1.LoginActivity.UPDATE_APK_REQUESTCODE;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.simtech.app1.BuildConfig;
import com.simtech.app1.LoginActivity;
import com.simtech.app1.R;
import com.simtech.app1.pojo.RVChildItem;
import com.simtech.app1.pojo.layout.ObservationPojo;

import java.util.List;

public class UIUtils {

    private static ProgressDialog sProgressDialog;

    public static void customToastMsg(Context context, String message) {
        try {
            if (!TextUtils.isEmpty(message)) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(R.layout.custom_toast_red, null);
                View view = inflater.inflate(R.layout.custom_toast, null);
                TextView tv = (TextView) view.findViewById(R.id.custom_text);
                tv.setText(message);
                Toast toast = new Toast(context);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(view);
                toast.show();
            }
        } catch (Exception e) {
            appendLog(android.util.Log.getStackTraceString(e));
            e.printStackTrace();
        }
    }

    public static void appendLog(String text) {
        text = CommonServices.getDateTime() + " " + BuildConfig.VERSION_CODE + " " + text;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            LogCreation.createLogFileBelowAndroid10(text);
        } else {
            LogCreation.createLogFileAndroid10AndAbove(text);
        }
    }

    public static void displayAlertDialog(Context activity, String msg, String title, boolean shouldCancellable, DialogInterface.OnClickListener okClickListener
            , DialogInterface.OnClickListener cancelClickListener, DialogInterface.OnDismissListener dismissListener) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);

        SpannableString spannableString = new SpannableString(msg);
//        Typeface typefaceSpan = Typeface.createFromAsset(activity.getAssets(), "fonts/sf_pro_display_regular");
//        spannableString.setSpan(typefaceSpan, 0, msg.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        alertDialogBuilder.setMessage(msg);


        if (!TextUtils.isEmpty(title)) {
            SpannableString spannabletitle = new SpannableString(title);
//            Typeface typefaceSpan1 = Typeface.createFromAsset(activity.getAssets(), "fonts/sf_pro_display_bold");
//            spannabletitle.setSpan(typefaceSpan1, 0, title.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            alertDialogBuilder.setTitle(spannabletitle);
        }


        if (okClickListener != null) {
            alertDialogBuilder.setPositiveButton(activity.getString(R.string.ok), okClickListener);
        }

        if (cancelClickListener != null) {
            alertDialogBuilder.setNegativeButton(activity.getString(R.string.cancel), cancelClickListener);
        }

        if (dismissListener != null) {
            alertDialogBuilder.setOnDismissListener(dismissListener);
        }


        try {
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.setCancelable(shouldCancellable);
            alertDialog.setCanceledOnTouchOutside(shouldCancellable);
            alertDialog.show();
        } catch (Exception e) {
            appendLog(android.util.Log.getStackTraceString(e));
            e.printStackTrace();
        }
    }

    public static void hideProgressDialog() {
        if (sProgressDialog != null && sProgressDialog.isShowing()) {
            try {
                sProgressDialog.dismiss();
            } catch (IllegalArgumentException e) {
                appendLog(android.util.Log.getStackTraceString(e));
                e.printStackTrace();
            }
            sProgressDialog = null;
        }
    }

    public static void showDialogWithL1_4(Context context, String parentItem, List<ObservationPojo> childItemList, int currPos) {
        // Inflate the custom dialog layout
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_45_dap_l1_4, null);

        String[] labels = {"R1", "R2", "R3"};
        String label = labels[currPos % labels.length];

        // Initialize the AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogView);
        builder.setTitle(parentItem + " - " + childItemList.get(currPos).getVarieties().get(currPos).getVarietycode() + " --> " + label);
        builder.setCancelable(false);

        // Find the EditText fields in the custom layout
        EditText etL1 = dialogView.findViewById(R.id.editTextL1);
        EditText etL2 = dialogView.findViewById(R.id.editTextL2);
        EditText etL3 = dialogView.findViewById(R.id.editTextL3);
        EditText etL4 = dialogView.findViewById(R.id.editTextL4);
        // Access the other EditText fields (editText3, editText4, and editText5) as needed

        builder.setPositiveButton("Save", (dialog, which) -> {
            // Handle OK button click
            String textL1 = etL1.getText().toString();
            String textL2 = etL2.getText().toString();
            String textL3 = etL3.getText().toString();
            String textL4 = etL4.getText().toString();
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> {
            // Handle Cancel button click
            dialog.dismiss();
        });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
