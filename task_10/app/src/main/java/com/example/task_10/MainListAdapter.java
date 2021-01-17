package com.example.task_10;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.task_10.models.MainListItem;

public class MainListAdapter extends ArrayAdapter<MainListItem> {
    private final Activity context;
    private final MainListItem[] names;

    public MainListAdapter(Activity context, MainListItem[] names) {
        super(context, R.layout.main_list_item, names);
        this.context = context;
        this.names = names;
    }

    // Класс для сохранения во внешний класс и для ограничения доступа
    // из потомков класса
    static class ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public TextView ratingView;
        public TextView descriptionView;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // ViewHolder буферизирует оценку различных полей шаблона элемента

        ViewHolder holder;
        // Очищает сущетсвующий шаблон, если параметр задан
        // Работает только если базовый шаблон для всех классов один и тот же
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.main_list_item, null, true);
            holder = new ViewHolder();
            holder.textView = rowView.findViewById(R.id.label);
            holder.imageView = rowView.findViewById(R.id.icon);
            holder.ratingView = rowView.findViewById(R.id.rating);
            holder.descriptionView = rowView.findViewById(R.id.description);
            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }

        holder.textView.setText(names[position].Name);
        holder.imageView.setImageResource(names[position].Image);
        holder.ratingView.setText(names[position].Rating.toString());
        holder.descriptionView.setText(names[position].Description);

        return rowView;
    }
}
