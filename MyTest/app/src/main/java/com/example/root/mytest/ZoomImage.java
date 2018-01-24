//package com.example.root.mytest;
//
//import android.content.Context;
//import android.graphics.Matrix;
//import android.graphics.PointF;
//import android.net.Uri;
//import android.support.annotation.Nullable;
//import android.util.AttributeSet;
//import android.util.FloatMath;
//import android.view.GestureDetector;
//import android.view.MotionEvent;
//import android.view.View;
//import android.widget.ImageView;
//
///**
// * Created by root on 24/1/18.
// */
//
//public class ZoomImage extends android.support.v7.widget.AppCompatImageView  {
//
//    Matrix matrix = new Matrix();
//    Matrix savedMatrix = new Matrix();
//
//    // We can be in one of these 3 states
//    static final int NONE = 0;
//    static final int DRAG = 1;
//    static final int ZOOM = 2;
//    int mode = NONE;
//
//    // Remember some things for zooming
//    PointF start = new PointF();
//    PointF mid = new PointF();
//    float oldDist = 1f;
//    String savedItemClicked;
//
//    public ZoomImage(Context context) {
//        super(context);
//    }
//
//    public ZoomImage(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public ZoomImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//    @Override
//    public void setImageURI(@Nullable Uri uri) {
//        super.setImageURI(uri);
//    }
//
//
//   @Override
//    public boolean onTouchEvent( MotionEvent event) {
//
//
//
//
//        DumpEvent(event);
//
//        switch (event.getAction()&MotionEvent.ACTION_MASK) {
//            case MotionEvent.ACTION_DOWN:
//                savedMatrix.set(matrix);
//                start.set(event.getX(), event.getY());
//                mode = DRAG;
//                break;
//
//            case MotionEvent.ACTION_UP:
//                break;
//            case MotionEvent.ACTION_POINTER_UP:
//                mode = NONE;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                if (mode == DRAG) {
//                    // ...
//                    matrix.set(savedMatrix);
//                    matrix.postTranslate(event.getX() - start.x, event.getY()
//                            - start.y);
//                } else if (mode == ZOOM) {
//                    float newDist = spacing(event);
//
//                    if (newDist > 10f) {
//                        matrix.set(savedMatrix);
//                        float scale = newDist / oldDist;
//                        matrix.postScale(scale, scale, mid.x, mid.y);
//                    }
//                }
//                break;
//        }
//
//        return true;
//    }
//
//    public void DumpEvent(MotionEvent event)
//    {
//        String name[]={"DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE",
//                "POINTER_DOWN", "POINTER_UP"};
//
//        StringBuilder builder=new StringBuilder();
//        int action=event.getAction();
//        int actionCode=action&MotionEvent.ACTION_MASK;
//        builder.append("event_ACTION_").append(name[actionCode]);
//
//        if (actionCode==MotionEvent.ACTION_POINTER_DOWN||actionCode==MotionEvent.ACTION_POINTER_UP)
//        {
//            builder.append("(pid ").append(action>> MotionEvent.ACTION_POINTER_INDEX_SHIFT);
//            builder.append(")");
//
//        }
//
//        builder.append("[");
//
//        for (int i=0;i<event.getPointerCount();i++)
//        {
//            builder.append("#").append(i);
//            builder.append("(pid ").append(event.getPointerId(i));
//            builder.append(")=").append((int) event.getX(i));
//            builder.append(",").append((int) event.getY(i));
//            if (i + 1 < event.getPointerCount())
//                builder.append(";");
//        }
//
//        builder.append("]");
//    }
//
//    /** Determine the space between the first two fingers */
//    private float spacing(MotionEvent event) {
//        float x = event.getX(0) - event.getX(1);
//        float y = event.getY(0) - event.getY(1);
//        return (float) Math.sqrt(x * x + y * y);
//    }
//
//    /** Calculate the mid point of the first two fingers */
//    private void midPoint(PointF point, MotionEvent event) {
//        float x = event.getX(0) + event.getX(1);
//        float y = event.getY(0) + event.getY(1);
//        point.set(x / 2, y / 2);
//    }
//
//
//}
