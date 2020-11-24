package com.example.realyenglsh;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapterForTranslateList extends RecyclerView.Adapter<MyAdapterForTranslateList.TranslateListHolder> {

    private List<MyListTranslating> listMyListTranslating;

    public MyAdapterForTranslateList(List<MyListTranslating> listMyListTranslating) {
        this.listMyListTranslating = listMyListTranslating;
    }

    @NonNull
    @Override
    public TranslateListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_questions_dialog, parent, false);
        return new TranslateListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TranslateListHolder holder, int position) {
        MyListTranslating myListTranslating = listMyListTranslating.get(position);
        holder.checkBoxList.setText(myListTranslating.getNameOfList());
        holder.checkBoxList.setChecked(myListTranslating.isChecked());
        holder.setTextColorOnCheckBox(myListTranslating.isChecked());

        holder.checkBoxList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListTranslating.setChecked(holder.checkBoxList.isChecked());
                holder.setTextColorOnCheckBox(holder.checkBoxList.isChecked());
                holder.setAnimation(R.anim.fadein_shortest);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMyListTranslating.size();
    }

    public class TranslateListHolder extends RecyclerView.ViewHolder {

        CheckBox checkBoxList;

        public TranslateListHolder(@NonNull View itemView) {
            super(itemView);
            checkBoxList = itemView.findViewById(R.id.checkBoxList);
        }

        public void setTextColorOnCheckBox(boolean checked) {
            if (checked) {
                checkBoxList.setTextColor(itemView.getResources().getColor(R.color.check_box_text_color_checked));
            } else {
                checkBoxList.setTextColor(itemView.getResources().getColor(R.color.check_box_text_color_unchecked));
            }
        }


        private void setAnimation(int idAnimationResource) {
            Animation animation = AnimationUtils.loadAnimation(checkBoxList.getContext(), idAnimationResource);
            checkBoxList.startAnimation(animation);
        }
    }
}
