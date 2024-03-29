package com.example.uju.coursetracker.presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import com.example.uju.coursetracker.R;

public class MessagesActivity
{
    public static void fatalError(final Activity owner, String message)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(owner).create();

        alertDialog.setTitle(owner.getString(R.string.fatalError));
        alertDialog.setMessage(message);
        alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
        {
            public void onCancel(DialogInterface dialog)
            {
                owner.finish();
            }
        });

        alertDialog.show();
    }

    public static void warning(Activity owner, String message)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(owner).create();

        alertDialog.setTitle(owner.getString(R.string.warning));
        alertDialog.setMessage(message);

        alertDialog.show();
    }
}
