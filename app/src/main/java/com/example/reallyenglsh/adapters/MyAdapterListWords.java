package com.example.reallyenglsh.adapters;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reallyenglsh.MyListWords;
import com.example.realyenglsh.R;

import java.util.List;

public class MyAdapterListWords extends RecyclerView.Adapter<MyAdapterListWords.MyHolder> {
    private final List<MyListWords> list;

    public MyAdapterListWords(List<MyListWords> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dialog_words, parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        MyListWords myList = list.get(position);
        holder.checkBox.setText(myList.getName());
        holder.checkBox.setChecked(myList.isChecked());
        holder.setColor(myList.isChecked());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private final CheckBox checkBox;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.get(getAdapterPosition()).setChecked(checkBox.isChecked());
                    setColor(checkBox.isChecked());
                }
            });
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        private void setColor(boolean checked) {
                Typeface typeface;
                if (checked) {
                    typeface = ResourcesCompat.getFont(checkBox.getContext(), R.font.archivo_black_regular);
                    checkBox.setTextColor(checkBox.getResources().getColor(R.color.dialog_lists_words_item_checked));
                    checkBox.setBackground(checkBox.getResources().getDrawable(R.drawable.item_adjective_checked));
                    checkBox.setTypeface(typeface);
                } else {
                    checkBox.setTextColor(checkBox.getResources().getColor(R.color.dialog_lists_words_item_unchecked));
                    checkBox.setBackground(checkBox.getResources().getDrawable(R.drawable.item_adjective_unchecked));
                    checkBox.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                }
        }

    }
}
