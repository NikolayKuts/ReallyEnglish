package com.example.reallyenglsh.adapters;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reallyenglsh.MyListAdjective;
import com.example.realyenglsh.R;

import java.util.List;

public class MyAdapterListsAdjectives extends RecyclerView.Adapter<MyAdapterListsAdjectives.ListAdjectiveHolder> {

    private final List<MyListAdjective> listAdjective;

    public MyAdapterListsAdjectives(List<MyListAdjective> listAdjective) {
        this.listAdjective = listAdjective;
    }

    @NonNull
    @Override
    public ListAdjectiveHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dialog_adjective, parent, false);
        return new ListAdjectiveHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdjectiveHolder holder, int position) {
        MyListAdjective myListAdjective = listAdjective.get(position);
        holder.checkBox.setText(myListAdjective.getNameList());
        holder.checkBox.setChecked(myListAdjective.isChecked());
        holder.setColor(myListAdjective.isChecked());
    }

    @Override
    public int getItemCount() {
        return listAdjective.size();
    }

    public class ListAdjectiveHolder extends RecyclerView.ViewHolder {
        private final CheckBox checkBox;

        public ListAdjectiveHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBoxAdjective);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(itemView.getContext(), R.raw.button_click);
                    listAdjective.get(getAdapterPosition()).setChecked(checkBox.isChecked());
                    setColor(checkBox.isChecked());
                    mediaPlayer.start();
                }
            });
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        public void setColor(boolean checked) {
            if (checked) {
                checkBox.setTextColor(itemView.getResources().getColor(R.color.dialog_lists_adjectives_item_color_checked));
                checkBox.setBackground(itemView.getResources().getDrawable(R.drawable.item_adjective_checked));
            } else {
                checkBox.setTextColor(itemView.getResources().getColor(R.color.dialog_lists_adjectives_item_color_unchecked));
                checkBox.setBackground(itemView.getResources().getDrawable(R.drawable.item_adjective_unchecked));
            }
        }


    }
}
