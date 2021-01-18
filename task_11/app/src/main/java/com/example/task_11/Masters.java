package com.example.task_11;

import com.example.task_11.models.MainListItem;

public class Masters {
    public static MainListItem[] masters = {
            new MainListItem(
                    R.drawable.cloudy,
                    "Климат",
                    new String[]{
                            "Кондиционер в спальне",
                            "Кондиционер в гостинной",
                            "Обогреватель в спальне",
                            "Увлажнитель воздуха в спальне",
                    }),
            new MainListItem(R.drawable.electric_kettle, "Оборудование",

                    new String[]{
                            "Чайник на кухне",
                            "Робот пылесос",
                            "Сушка для фруктов",
                            "Сушилка для вещей в ванной",
                    }),
            new MainListItem(R.drawable.plug, "Электрика",
                    new String[]{
                            "Люстра в гостинной",
                            "Люстра в корридоре",
                            "Люстра в ванной",
                            "Люстра в спальне",
                            "Розетки в спальне",
                            "Розетки в ванной",
                    }),
            new MainListItem(R.drawable.video, "Мультимедия",
                    new String[]{
                            "Телевизор в гостинной",
                            "Колонка в ванной",
                            "Телевизор в спальне",
                            "Колонка в спальне",
                    }),

    };
}
