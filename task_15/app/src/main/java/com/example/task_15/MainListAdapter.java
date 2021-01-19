package com.example.task_15;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.task_15.models.MainListItem;

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
        public TextView dateView;

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
            holder.dateView = rowView.findViewById(R.id.newsDate);
            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }

        if(names[position].Image != null){
            holder.textView.setText(names[position].Name);
            holder.imageView.setImageResource(names[position].Image);
            holder.dateView.setText(names[position].Date);
            holder.dateView.setVisibility(View.VISIBLE);
            holder.imageView.setVisibility(View.VISIBLE);
            rowView.setEnabled(true);
        } else {
            holder.textView.setText(names[position].Name);
            holder.dateView.setVisibility(View.GONE);
            holder.imageView.setVisibility(View.GONE);
            rowView.setEnabled(false);
        }


        return rowView;
    }
}
