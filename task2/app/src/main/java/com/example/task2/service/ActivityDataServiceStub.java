package com.example.task2.service;

import android.content.res.Resources;

import com.example.task2.R;
import com.example.task2.dto.ActivityData;
import com.example.task2.dto.ActivityType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ActivityDataServiceStub {

    private static ActivityDataServiceStub INSTANCE;

    private final Map<String, ActivityType> captionByActivityType;
    private final Map<ActivityType, String> activityTypeByCaption;


    public static void initialize(Resources resources) {
        if (INSTANCE != null) {
            return;
        }
        INSTANCE = new ActivityDataServiceStub(resources);
    }

    public static ActivityDataServiceStub getInstance() {
        return INSTANCE;
    }

    private ActivityDataServiceStub(Resources resources) {
        captionByActivityType = Arrays.stream(resources.getStringArray(R.array.activity_types))
                .map(s -> s.split("\\|"))
                .collect(Collectors.toMap(s -> s[1], s -> ActivityType.valueOf(s[0])));
        activityTypeByCaption = Arrays.stream(resources.getStringArray(R.array.activity_types))
                .map(s -> s.split("\\|"))
                .collect(Collectors.toMap(s -> ActivityType.valueOf(s[0]), s -> s[1]));
    }

    public List<String> getActivityTypesCaption() {
        return new ArrayList<>(captionByActivityType.keySet());
    }

    public String getActivityCaption(ActivityType activityType) {
        return activityTypeByCaption.get(activityType);
    }

    public ActivityData resolveActivityType(String item) {
        ActivityType activityType = captionByActivityType.get(item);
        return getAvailableActivityData().stream()
                .filter(el -> el.getActivityType() == activityType)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not found: " + activityType));
    }

    private List<ActivityData> getAvailableActivityData() {
        return Arrays.asList(
                new ActivityData(ActivityType.RUN, "мин.", BigDecimal.valueOf(1.5)),
                new ActivityData(ActivityType.SWIM, "мин.", BigDecimal.valueOf(1.5)),
                new ActivityData(ActivityType.PULL_UP, "раз(а)", BigDecimal.valueOf(1.5))
        );
    }

}
