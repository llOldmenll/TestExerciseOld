package com.oldmen.attendancemanager.utils;


import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class GridDividerItemDecoration extends RecyclerView.ItemDecoration {

    private int offset;

    public GridDividerItemDecoration(int offset) {
        this.offset = offset;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        outRect.left = offset;
        outRect.right = offset;
        outRect.bottom = offset;
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = offset;
        }

    }
}
