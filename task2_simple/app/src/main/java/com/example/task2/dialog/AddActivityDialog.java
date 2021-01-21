package com.example.task2.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import com.example.task2.R;
import com.example.task2.dto.PersonActivityDto;
import com.example.task2.service.PersonActivityServiceStub;

public class AddActivityDialog extends DialogFragment implements View.OnClickListener {

    private PersonActivityDto personActivityDto;

    private static final String LOG_TAG = "AddActivityDialog";
    private final Runnable dismissCallback;
    private View v;

    public AddActivityDialog(Runnable dismissCallback) {
        this.dismissCallback = dismissCallback;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.add_activity_dialog, container);
        v.findViewById(R.id.btnAdd).setOnClickListener(this);
        v.findViewById(R.id.btnCancel).setOnClickListener((dialog) -> onCancel(getDialog()));
        personActivityDto = new PersonActivityDto();
        return v;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.d(LOG_TAG, "onDismiss");
        dismiss();
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        Log.d(LOG_TAG, "onCancel");
        dismiss();
    }

    /**
     * ADD
     */
    @Override
    public void onClick(View v) {
        Log.d(LOG_TAG, "Add button");
        EditText count = this.v.findViewById(R.id.count_activity);
        personActivityDto.setCount(Integer.valueOf(count.getText().toString()));
        EditText desc = this.v.findViewById(R.id.desc_activity);
        personActivityDto.setDesc(desc.getText().toString());
        PersonActivityServiceStub.getInstance().addUserActivity(personActivityDto);
        Log.d(LOG_TAG, "Saved: " + personActivityDto);
        personActivityDto = new PersonActivityDto();
        this.dismissCallback.run();
        dismiss();
    }

}
