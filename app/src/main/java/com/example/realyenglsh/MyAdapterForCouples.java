package com.example.realyenglsh;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapterForCouples extends RecyclerView.Adapter<MyAdapterForCouples.CouplesListHolder> {

    private List<MyListCouples> listMyListCouples;

    public MyAdapterForCouples(List<MyListCouples> listMyListCouples) {
        this.listMyListCouples = listMyListCouples;
    }

    @NonNull
    @Override
    public CouplesListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_couples_dialog, parent, false);
        return new CouplesListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CouplesListHolder holder, int position) {
        MyListCouples myListCouples = listMyListCouples.get(position);
        holder.checkBoxList.setText(myListCouples.getNameOfList());
        holder.checkBoxList.setChecked(myListCouples.isChecked());
        holder.setTextColorOnCheckBox(myListCouples.isChecked());

        holder.checkBoxList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListCouples.setChecked(holder.checkBoxList.isChecked());
                holder.setTextColorOnCheckBox(holder.checkBoxList.isChecked());
                holder.setAnimation(R.anim.fadein_shortest);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMyListCouples.size();
    }

    public class CouplesListHolder extends RecyclerView.ViewHolder {

        CheckBox checkBoxList;

        public CouplesListHolder(@NonNull View itemView) {
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
