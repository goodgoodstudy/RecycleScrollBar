package com.example.recycleviewscrollbar;


import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * @author monkey
 */
public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ScrollBar mScrollBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView =findViewById(R.id.recyclerView);
        mScrollBarLayout =findViewById(R.id.scrollbar_lay);

        ArrayList list = new ArrayList();
        for (int i=0;i<100;i++){
            list.add(i);
        }
        MyAdapter adapter = new MyAdapter(this,list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        mScrollBarLayout.setRecycleView(mRecyclerView);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState){
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE://滑动结束
                        Log.e("monkey","mScrollBarLayout INVISIBLE");
                        mScrollBarLayout.setVisibility(View.INVISIBLE);
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL://开始滑动
                        Log.e("monkey","mScrollBarLayout VISIBLE");
                        mScrollBarLayout.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(mScrollBarLayout!=null){
                    mScrollBarLayout.sroll();
                }
            }
        });

    }
}
