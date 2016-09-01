package com.example.mypc.ezenglish.flagment;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.activity.HomeActivity;
import com.example.mypc.ezenglish.adapter.TopicAdapter;
import com.example.mypc.ezenglish.datafirebase.LessonFB;
import com.example.mypc.ezenglish.datafirebase.TopicFB;
import com.special.ResideMenu.ResideMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quylt on 8/26/2016.
 */
public class HomeFragment extends Fragment {

    private View parentView;
    private ResideMenu resideMenu;
    private RecyclerView recyclerView;
    private TopicAdapter adapter;
    private List<TopicFB> albumList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_home, container, false);
        setUpViews();
        return parentView;
    }

    private void setUpViews() {
        HomeActivity parentActivity = (HomeActivity) getActivity();
        resideMenu = parentActivity.getResideMenu();
        albumList = new ArrayList<>();
        adapter = new TopicAdapter(parentActivity, albumList);
        recyclerView = (RecyclerView) parentView.findViewById(R.id.recycler_topic);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        prepareAlbums();

    }


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.origional,
                R.drawable.real,
                R.drawable.flow,
                R.drawable.bussiniss,
                R.drawable.power};
        TopicFB a = new TopicFB(0, "Origional Effortless English", "" + covers[0], " Dành cho những người mới bắt đầu học tiếng anh ( Bắt đầu học)", new ArrayList<LessonFB>());
        albumList.add(a);
        a = new TopicFB(1, " Learn Real English", "" + covers[1], "Những đoạn hội thoại, giao tiếp về 1 số chủ đề trong cuộc sống thường ngày.", new ArrayList<LessonFB>());

        albumList.add(a);
        a = new TopicFB(2, " Flow English", "" + covers[2], "Những mẩu chuyện ngắn, có nhiều thành ngữ và cụm từ thông dụng", new ArrayList<LessonFB>());

        albumList.add(a);

        a = new TopicFB(3, " Business Effortless English", "" + covers[3], "Các bài học về thành công và làm giàu", new ArrayList<LessonFB>());

        albumList.add(a);

        a = new TopicFB(4, " Power English Now", "" + covers[4], "Cung cấp  phương pháp tạo động lực để thành công, hạnh phúc trong cuộc sống", new ArrayList<LessonFB>());

        albumList.add(a);


        adapter.notifyDataSetChanged();
    }

}
