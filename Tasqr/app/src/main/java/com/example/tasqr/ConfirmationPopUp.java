package com.example.tasqr;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.tasqr.classes.Company;
import com.example.tasqr.classes.Project;
import com.example.tasqr.classes.User;

public class ConfirmationPopUp extends DialogFragment {

    private static final String TAG = "ConfirmationPopUp";
    private final String name;
    private final int position;
    private TextView text;
    private Bundle bundle;
    private ConfirmationListener listener;

    public ConfirmationPopUp(String name, int position){
        this.name = name;
        this.position = position;
    }

    public ConfirmationPopUp(String name) {
        this.name = name;
        this.position = -1;
    }

    /* Main on create method */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        /* Creating layout dependencies */
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.popup_confirmation, null);

        bundle = getArguments();

        text = view.findViewById(R.id.conftext);
        text.setText(bundle.getString("text"));

        /* Setting listeners */
        builder.setView(view).setTitle("CONFIRM ACTION")
                .setNegativeButton("IM NOT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("YES, I AM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.confirmation(position);
            }
        });

        return builder.create();
    }

    /* Method to get context for listener */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (ConfirmationListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement ConfirmationListener interface");
        }
    }

    public interface ConfirmationListener
    {
        void confirmation(int position);
    }
}
