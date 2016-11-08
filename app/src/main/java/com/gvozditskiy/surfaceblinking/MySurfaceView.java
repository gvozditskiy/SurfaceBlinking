package com.gvozditskiy.surfaceblinking;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 08.11.2016.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    float[] gridLines;
    Canvas canvas;
    PointF oldPos = new PointF();
    SurfaceHolder holder;
    final int mStep = 15;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setColor(context.getResources().getColor(R.color.colorAccent));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF newPos = new PointF();
        float top;
        float left;
        float bottom;
        float right;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                oldPos.set(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                canvas = holder.lockCanvas();
                drawGrid(canvas);
                newPos.set(event.getX(), event.getY());
                top = Math.min(oldPos.y, newPos.y);
                left = Math.min(oldPos.x, newPos.x);
                bottom = Math.max(oldPos.y, newPos.y);
                right = Math.max(oldPos.x, newPos.x);
                int l = (int) left / mStep;
                l *= mStep;
                int r = (int) right / mStep;
                r *= mStep;
                int t = (int) top / mStep;
                t *= mStep;
                int b = (int) bottom / mStep;
                b *= mStep;
                paint.setStrokeWidth(6);
                canvas.drawRect(l, t, r, b, paint);
                holder.unlockCanvasAndPost(canvas);
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }

    private void drawGrid(Canvas canvas) {
        final Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GRAY);
        gridLines = getLines(mStep);
        canvas.drawColor(Color.WHITE);
        paint.setStrokeWidth(2);
        canvas.drawLines(gridLines, paint);
    }

    private float[] getLines(int step) {
        final List<Float> floatList = new ArrayList<>();
        int right = this.getRight();
        int bottom = this.getBottom();
        int i = 0;
        for (int x = 0; x < right; x += step) {
                floatList.add((float) x);
                floatList.add(0f);
                floatList.add((float) x);
                floatList.add((float) bottom);
            i++;

        }
        i = 0;
        for (int y = 0; y < bottom; y += step) {
                floatList.add(0f);
                floatList.add((float) y);
                floatList.add((float) right);
                floatList.add((float) y);
            i++;
        }

        float[] rez = new float[floatList.size()];
        for (int k = 0; k < floatList.size(); k++) {
            rez[k] = floatList.get(k);
        }
        return rez;
    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        holder = surfaceHolder;
        canvas = surfaceHolder.lockCanvas();
        drawGrid(canvas);
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        canvas = surfaceHolder.lockCanvas();
        drawGrid(canvas);
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
