package com.example.mixoperateimagedemo;

import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

class ViewOnTouchListener implements View.OnTouchListener {
    Point pushPoint;
    Point deletePoint;
    int lastImgLeft;
    int lastImgTop;
    FrameLayout.LayoutParams viewLP;
    FrameLayout.LayoutParams pushBtnLP;
    FrameLayout.LayoutParams deleteBtnLP;
    int lastPushBtnLeft;
    int lastPushBtnTop;
    
    int lastDeleteBtnLeft;
    int lastDeleteBtnTop;
    
    private View mPushView;
    private View mDeleteView;

    ViewOnTouchListener(View mPushView , View mDeleteView) {
        this.mPushView = mPushView;
        this.mDeleteView = mDeleteView;

    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                if (null == viewLP) {
                    viewLP = (FrameLayout.LayoutParams) view.getLayoutParams();
                }
                if (null == pushBtnLP) {
                    pushBtnLP = (FrameLayout.LayoutParams) mPushView.getLayoutParams();
                }
                
                if(null == deleteBtnLP){
                	deleteBtnLP = (FrameLayout.LayoutParams) mDeleteView.getLayoutParams();
                }
                
                pushPoint = getRawPoint(event);
                lastImgLeft = viewLP.leftMargin;
                lastImgTop = viewLP.topMargin;
                lastPushBtnLeft = pushBtnLP.leftMargin;
                lastPushBtnTop = pushBtnLP.topMargin;
                
                deletePoint = getRawPoint(event);
                lastDeleteBtnLeft = deleteBtnLP.leftMargin;
                lastDeleteBtnTop = deleteBtnLP.topMargin;
                break;
            case MotionEvent.ACTION_MOVE:
                Point newPoint = getRawPoint(event);
                float moveX = newPoint.x - pushPoint.x;
                float moveY = newPoint.y - pushPoint.y;

                viewLP.leftMargin = (int) (lastImgLeft + moveX);
                viewLP.topMargin = (int) (lastImgTop + moveY);
                view.setLayoutParams(viewLP);

                pushBtnLP.leftMargin = (int) (lastPushBtnLeft + moveX);
                pushBtnLP.topMargin = (int) (lastPushBtnTop + moveY);
                mPushView.setLayoutParams(pushBtnLP);
                
                deleteBtnLP.leftMargin = (int) (lastDeleteBtnLeft + moveX);
                deleteBtnLP.topMargin = (int) (lastDeleteBtnTop + moveY);
                mDeleteView.setLayoutParams(deleteBtnLP);

                break;

        }
        return false;
    }


    private Point getRawPoint(MotionEvent event) {
        return new Point((int) event.getRawX(), (int) event.getRawY());
    }
}
