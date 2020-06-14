package com.example.recycleviewscrollbar;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


/**
 * 自定义RecycleView侧边滑动条
 * @author Monkey
 */
public class ScrollBar extends FrameLayout {
	private static String TAG = "ScrollBar";
	private View mScrollBar;
	private View mScrollBarLay;
	private RecyclerView mRecycleView;

	public ScrollBar(@NonNull Context context) {
		super(context);
		init(context);
	}

	public ScrollBar(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public ScrollBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	private void init(final Context context) {
		mScrollBarLay = LayoutInflater.from(context).inflate(R.layout.scroll_view, null);
		mScrollBar = mScrollBarLay.findViewById(R.id.scrollbar);
		addView(mScrollBarLay);
	}

	public void setRecycleView(RecyclerView recycleView) {
		mRecycleView = recycleView;
	}

	public void sroll() {
		if(mRecycleView==null){
			return;
		}
		int range = 0;
		int temp = mRecycleView.computeVerticalScrollRange();//整体的高度，注意是整体，包括在显示区域之外的 会动态变化
		if (temp > range) {
			range = temp;
		}
		Log.i(TAG,"range "+range);
		int offset = mRecycleView.computeVerticalScrollOffset();//已经向下滚动的距离，为0时表示已处于顶部。
		Log.i(TAG,"offset "+offset);
		int extent = mRecycleView.computeVerticalScrollExtent();//RecycleView显示区域的高度。
		Log.i(TAG,"extent "+extent);
		float proportion = (float) (offset * 1.0 / (range - extent));//滑动比例
		Log.i(TAG,"proportion "+proportion);
		float transMaxRange = mScrollBarLay.getHeight() - mScrollBar.getHeight();//滚动条背景高度-滚动条高度
		Log.i(TAG,"transMaxRange "+transMaxRange);
		mScrollBar.setTranslationY(transMaxRange * proportion);//设置滚动条移动
		Log.i(TAG,"setTranslationY "+transMaxRange * proportion);
	}
}
