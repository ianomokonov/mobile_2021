package com.example.task_11;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.task_11.models.MainListItem;
import com.example.task_11.models.UserFormValue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class CustomDialogFragment extends DialogFragment {
    private Sendable sendable;
    private int masterIndex;
    private UserFormValue userData;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        sendable = (Sendable) context;
        try {
            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    getActivity().openFileInput("user_data")));
            String str = br.readLine();
            if(str != null){
                String[] data = str.split(", ");
                userData = new UserFormValue(data[0], data[2], 0, data[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        masterIndex = getArguments().getInt("masterIndex");
        int deviceIndex = getArguments().getInt("deviceIndex");
        MainListItem master = Masters.masters[masterIndex];
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        AlertDialog dialog = builder
                .setTitle(master.Devices[deviceIndex])
                .setView(R.layout.user_form)
                .setNegativeButton("Отмена", null)
                .create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialogInterface) {

                Button buttonOn = ((AlertDialog) dialog).findViewById(R.id.turnOn);
                Button buttonOff = ((AlertDialog) dialog).findViewById(R.id.turnOff);

                buttonOn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sendable.sendForm(true);
                        dialog.dismiss();
                    }
                });

                buttonOff.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sendable.sendForm(false);
                        dialog.dismiss();
                    }
                });
            }
        });

        return dialog;
    }
}
