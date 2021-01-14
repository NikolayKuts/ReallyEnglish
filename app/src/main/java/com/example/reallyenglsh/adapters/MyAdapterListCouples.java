package com.example.reallyenglsh.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reallyenglsh.IAdapterHelper;
import com.example.reallyenglsh.MyListCouples;
import com.example.realyenglsh.R;

import java.util.List;

public class MyAdapterListCouples extends RecyclerView.Adapter<MyAdapterListCouples.CouplesListHolder> {

    private final List<MyListCouples> listMyListCouples;

    public MyAdapterListCouples(List<MyListCouples> listMyListCouples) {
        this.listMyListCouples = listMyListCouples;
    }

    @NonNull
    @Override
    public CouplesListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_couples_dialog, parent, false);
        return new CouplesListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CouplesListHolder holder, int position) {
        IAdapterHelper myListCouples = (IAdapterHelper) listMyListCouples.get(position);
        holder.checkBoxList.setText(myListCouples.getNameList());
        holder.checkBoxList.setChecked(myListCouples.isChecked());
        holder.setTextColorOnCheckBox(myListCouples.isChecked());

        holder.checkBoxList.setOnClickListener(v -> {
            myListCouples.setChecked(holder.checkBoxList.isChecked());
            holder.setTextColorOnCheckBox(holder.checkBoxList.isChecked());
            holder.startAnimation();
        });
    }

    @Override
    public int getItemCount() {
        return listMyListCouples.size();
    }

    public static class CouplesListHolder extends RecyclerView.ViewHolder {
        private final CheckBox checkBoxList;

        private CouplesListHolder(@NonNull View itemView) {
            super(itemView);
            checkBoxList = itemView.findViewById(R.id.checkBoxList);
        }

        private void setTextColorOnCheckBox(boolean checked) {
            if (checked) {
                checkBoxList.setTextColor(itemView.getResources().getColor(R.color.dialog_lists_couples_checked));
            } else {
                checkBoxList.setTextColor(itemView.getResources().getColor(R.color.dialog_lists_couples_unchecked));
            }
        }

        private void startAnimation() {
            Animation animation = AnimationUtils.loadAnimation(checkBoxList.getContext(), R.anim.fadein_shortest);
            checkBoxList.startAnimation(animation);
        }
    }
}
