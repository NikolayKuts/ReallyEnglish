package com.example.realyenglsh;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<MyListOfVerbs> list;

    public MyAdapter(Context context, List<MyListOfVerbs> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyListOfVerbs myListOfVerbs = (MyListOfVerbs) getItem(position);
        ViewHolder holder;

        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_item_list_view, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.checkBox.setText(myListOfVerbs.getNameOfList());
        if (myListOfVerbs.isChecked()) {
            holder.checkBox.setChecked(true);
            holder.checkBox.setTextColor(convertView.getResources().getColor(R.color.dialog_check_box_checked_color));
        }
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.checkBox.isChecked()) {
                    holder.checkBox.setTextColor(v.getResources().getColor(R.color.dialog_check_box_checked_color));
                } else {
                    holder.checkBox.setTextColor(v.getResources().getColor(R.color.check_box_unchecked_color));
                }
                if (myListOfVerbs.isChecked()) {
                    myListOfVerbs.setChecked(false);
                } else {
                    myListOfVerbs.setChecked(true);
                }
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        private final CheckBox checkBox;

        public ViewHolder(View view) {
            checkBox = view.findViewById(R.id.checkBoxVerbsList);
        }

    }
}
