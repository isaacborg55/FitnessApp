package com.example.FitnessApp;

import android.view.View;

public interface ItemClickListener {

    void onClick(View view, int position, boolean isLongClick);
}
//used for CatergoryView
//https://developer.android.com/reference/android/widget/AdapterView.OnItemClickListener