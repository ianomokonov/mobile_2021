package com.example.task_14;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.task_14.models.MainListItem;

import java.util.ArrayList;

public class MainListAdapter extends ArrayAdapter<MainListItem> {
    private final Activity context;
    private final ArrayList<MainListItem> names;

    public MainListAdapter(Activity context, ArrayList<MainListItem> names) {
        super(context, R.layout.main_list_item, names);
        this.context = context;
        this.names = names;
    }

    // Класс для сохранения во внешний класс и для ограничения доступа
    // из потомков класса
    static class ViewHolder {
        public TextView textView;
        public TextView ratingView;
        public TextView dateStartView;
        public TextView dateEndView;
        public TextView sumView;
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
            holder.ratingView = rowView.findViewById(R.id.rating);
            holder.dateStartView = rowView.findViewById(R.id.dateStart);
            holder.dateEndView = rowView.findViewById(R.id.dateEnd);
            holder.sumView = rowView.findViewById(R.id.sum);
            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }

        holder.textView.setText(names.get(position).Name);
        holder.ratingView.setText(names.get(position).City);
        holder.dateStartView.setText(names.get(position).DateStart);
        holder.dateEndView.setText(names.get(position).DateEnd);
        holder.sumView.setText(names.get(position).Sum.toString());

        return rowView;
    }
}
