package com.example.task_12;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.task_12.models.MainListItem;
import com.example.task_12.models.UserFormValue;

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
        MainListItem master = Masters.masters.get(masterIndex);
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        AlertDialog dialog = builder
                .setTitle("Как с Вами связаться?")
                .setView(R.layout.user_form)
                .setPositiveButton("Отправить", null)
                .setNegativeButton("Отмена", null)
                .create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialogInterface) {

                Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                TextView name = dialog.findViewById(R.id.userName);
                TextView phone = dialog.findViewById(R.id.phone);
                TextView email = dialog.findViewById(R.id.email);
                if(userData != null){
                    name.setText(userData.name);
                    phone.setText(userData.phone);
                    email.setText(userData.email);
                }

                button.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {


                        if(name.getText().toString().equals("")){
                            Toast.makeText(getActivity(), "Укажите ФИО", Toast.LENGTH_SHORT).show();
                        } else if(phone.getText().toString().equals("") && email.getText().toString().equals("")){
                            Toast.makeText(getActivity(), "Укажите телефон и email", Toast.LENGTH_SHORT).show();
                        } else {
                            sendable.sendForm(new UserFormValue(name.getText().toString(), phone.getText().toString(), masterIndex, email.getText().toString()));
                            dialog.dismiss();
                        }
                    }
                });
            }
        });

        return dialog;
    }
}
