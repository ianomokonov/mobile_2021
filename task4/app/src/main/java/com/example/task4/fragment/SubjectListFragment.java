package com.example.task4.fragment;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import androidx.fragment.app.ListFragment;

import com.example.task4.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SubjectListFragment extends ListFragment {

    private static final List<String> SUBJECTS = new ArrayList<>();
    private static final List<String> TYPES = new ArrayList<>();
    private static final HashMap<Integer, String> TIME = new HashMap<>();

    static {
        SUBJECTS.addAll(Arrays.asList("Технологии обработки больших данных",
                "Технологии разработки приложений для мобильных устройств",
                "Программная инженерия",
                "Технологии автоматизации разработки информационных систем",
                "Системный анализ в профессиональной деятельности",
                "Разработка корпоративных приложений и облачные технологии"));
    }

    static {
        TYPES.addAll(Arrays.asList("Семинар", "Лекция"));
    }

    static {
        TIME.put(1, "08:30 - 10:00");
        TIME.put(2, "10:10 - 11:40");
        TIME.put(3, "11:50 - 13:20");
        TIME.put(4, "14:00 - 15:30");
        TIME.put(5, "15:40 - 17:10");
        TIME.put(6, "17:20 - 18:50");
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(prepareAdapter());
        setEmptyText(getResources().getString(R.string.empty_text));
    }

    private ListAdapter prepareAdapter() {
        return new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, prepareSchedule());
    }

    private List<String> prepareSchedule() {
        int nOfSubjects = new Random().nextInt(5) + 2;
        return IntStream.range(0, nOfSubjects)
                .mapToObj(this::genSubject)
                .collect(Collectors.toList());
    }

    private String genSubject(int ind) {
        String subject = getRandom(SUBJECTS);
        String type = getRandom(TYPES);
        return String.format(Locale.ENGLISH, "%d. %s\n%s.\n%s", ind + 1, TIME.get(ind+1), subject, type);
    }


    private String getRandom(List<String> data) {
        return data.get(new Random().nextInt(data.size()));
    }


}
