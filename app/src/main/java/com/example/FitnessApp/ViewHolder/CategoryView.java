package com.example.FitnessApp.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.FitnessApp.ItemClickListener;
import com.example.FitnessApp.R;

public class CategoryView extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txtCategoryName, txtCategoryMeasure;

//https://www.youtube.com/watch?v=69C1ljfDvl0
    //used to create categories for db info
    private ItemClickListener itemClickListener;
    public CategoryView(View itemView) {
        super(itemView);

        txtCategoryName = (TextView) itemView.findViewById(R.id.category_name);
        txtCategoryMeasure = (TextView) itemView.findViewById(R.id.category_measure);

        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {

        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
