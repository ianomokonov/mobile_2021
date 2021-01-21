package com.example.task2.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.task2.R;
import com.example.task2.dto.ActivityData;
import com.example.task2.dto.PersonActivityDto;
import com.example.task2.service.ActivityDataServiceStub;
import com.example.task2.service.PersonActivityServiceStub;

import java.time.LocalDate;

public class AddActivityDialog extends DialogFragment implements View.OnClickListener {

    private PersonActivityDto personActivityDto;

    private static final String LOG_TAG = "AddActivityDialog";
    private View v;
    private Runnable dismissCallback;

    public AddActivityDialog(Runnable dismissCallback) {
        this.dismissCallback = dismissCallback;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.add_activity_dialog, container);
        v.findViewById(R.id.btnAdd).setOnClickListener(this);
        v.findViewById(R.id.btnCancel).setOnClickListener((dialog) -> onCancel(getDialog()));
        if (personActivityDto == null) {
            personActivityDto = new PersonActivityDto();
        }
        prepareActivitySpinner(v);
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
        DatePicker picker = this.v.findViewById(R.id.activity_date);
        LocalDate actualDate = LocalDate.of(picker.getYear(), picker.getMonth() + 1, picker.getDayOfMonth());
        personActivityDto.setDate(actualDate);
        EditText editText = this.v.findViewById(R.id.count_activity);
        personActivityDto.setCount(Integer.valueOf(editText.getText().toString()));
        PersonActivityServiceStub.getInstance().addUserActivity(personActivityDto);
        Log.d(LOG_TAG, "Saved: " + personActivityDto);
        personActivityDto = new PersonActivityDto();
        this.dismissCallback.run();
        dismiss();
    }

    private void prepareActivitySpinner(View v) {
        Spinner spinner = v.findViewById(R.id.activity_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(v.getContext(), android.R.layout.simple_spinner_item,
                ActivityDataServiceStub.getInstance().getActivityTypesCaption());
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);
                ActivityData activityData = ActivityDataServiceStub.getInstance().resolveActivityType(item);
                Log.d(LOG_TAG, "selected activity: " + activityData);
                personActivityDto.setActivityData(activityData);
                TextView unitsTextView = v.findViewById(R.id.units);
                unitsTextView.setText(activityData.getUnits());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


//    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
//
//        @Override
//        public Dialog onCreateDialog(Bundle savedInstanceState) {
//            // Use the current time as the default values for the picker
//            LocalDate now = LocalDate.now();
//
//            // Create a new instance of TimePickerDialog and return it
//            return new DatePickerDialog(getActivity(), this, hour, minute,
//                    DateFormat.is24HourFormat(getActivity()));
//        }
//
//
//        @Override
//        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//            LocalDate date = LocalDate.of(year, month, dayOfMonth);
//
//
//        }
//    }


}
