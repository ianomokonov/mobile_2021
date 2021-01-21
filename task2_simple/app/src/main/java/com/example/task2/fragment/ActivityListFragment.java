package com.example.task2.fragment;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import androidx.fragment.app.ListFragment;

import com.example.task2.R;
import com.example.task2.service.PersonActivityServiceStub;

import java.util.List;
import java.util.stream.Collectors;

public class ActivityListFragment extends ListFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(prepareAdapter());
        setEmptyText(getResources().getString(R.string.empty_text));
    }

    public void updateActivities() {
        setListAdapter(prepareAdapter());
    }

    private ListAdapter prepareAdapter() {
        return new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, preparePersonActivities());
    }

    private List<String> preparePersonActivities() {
        return PersonActivityServiceStub.getInstance().getPersonActivities().stream()
                .map(activity -> String.format("%s: %s", activity.getDesc(), activity.getCount()))
                .collect(Collectors.toList());
    }

}
