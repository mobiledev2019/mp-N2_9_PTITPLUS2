package com.example.model;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.myappptitplus.R;

public class BanDo extends Fragment {
    ImageView imv_bando ;
    int mode = 0;
    int DRAG = 1;
    ScaleGestureDetector scaleGestureDetector;
    Activity mContext;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View myvView = inflater.inflate(R.layout.activity_map,container ,false);
        imv_bando = myvView.findViewById(R.id.imv_bando);
        return myvView;
    }

    class MyGesture extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        float scale = 1.0F , onScaleStart = 0 , onScaleEnd = 0;
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale *= detector.getScaleFactor();
            imv_bando.setScaleX(scale);
            imv_bando.setScaleY(scale);
            return super.onScale(detector);
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            onScaleStart = scale;
            return super.onScaleBegin(detector);
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            onScaleEnd = scale;
            super.onScaleEnd(detector);
        }
    }

    @Override
    public void onAttach(Context context) {
        mContext = (Activity)context;
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(1080,1920);
        layoutParams.leftMargin = 0;
        layoutParams.topMargin = 0;
        imv_bando.setLayoutParams(layoutParams);

        scaleGestureDetector = new ScaleGestureDetector(mContext,new MyGesture());
        imv_bando.setOnTouchListener(new View.OnTouchListener() {
            LinearLayout.LayoutParams params;
            Float dx = 0F , dy = 0F , x = 0F , y = 0F;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scaleGestureDetector.onTouchEvent(event);
                ImageView imageView = (ImageView) v;
                switch (event.getAction() & MotionEvent.ACTION_MASK){
                    case MotionEvent.ACTION_DOWN :
                        params = (LinearLayout.LayoutParams) imageView.getLayoutParams();
                        dx = event.getRawX();
                        dy = event.getRawY();
                        mode = DRAG;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if(mode == DRAG){
                            x = event.getRawX();
                            y = event.getRawY();

                            params.leftMargin = (int) (x -dx);
                            params.topMargin = (int) (y - dy);

                            params.rightMargin = 0;
                            params.bottomMargin = 0;

                            params.rightMargin = params.leftMargin + ( 5 * params.width);
                            params.bottomMargin = params.topMargin + (10 * params.height);

                            imageView.setLayoutParams(params);
                        }
                }
                return true;
            }
        });



        super.onViewCreated(view, savedInstanceState);
    }
}
