package com.example.realyenglsh;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapterAdjectiveList extends RecyclerView.Adapter<MyAdapterAdjectiveList.AdjectiveListHolder> {

    private List<MyListAdjective> listAdjective;

    public MyAdapterAdjectiveList(List<MyListAdjective> listAdjective) {
        this.listAdjective = listAdjective;
    }

    @NonNull
    @Override
    public AdjectiveListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dialog_adjective, parent, false);
        return new AdjectiveListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdjectiveListHolder holder, int position) {
        MyListAdjective myListAdjective = listAdjective.get(position);
        holder.checkBox.setText(myListAdjective.getNameList());
        holder.checkBox.setChecked(myListAdjective.isChecked());
        holder.setColor(myListAdjective.isChecked());

    }

    @Override
    public int getItemCount() {
        return listAdjective.size();
    }

    public class AdjectiveListHolder extends RecyclerView.ViewHolder {

        private CheckBox checkBox;

        public AdjectiveListHolder(@NonNull View itemView) {
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
                checkBox.setTextColor(itemView.getResources().getColor(R.color.item_adjective_text_color_checked));
                checkBox.setBackground(itemView.getResources().getDrawable(R.drawable.item_adjective_orange));
            } else {
                checkBox.setTextColor(itemView.getResources().getColor(R.color.item_adjective_text_color_unchecked));
                checkBox.setBackground(itemView.getResources().getDrawable(R.drawable.item_adjective));
            }
        }


    }
}
