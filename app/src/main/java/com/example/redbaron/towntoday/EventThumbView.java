package com.example.redbaron.towntoday;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class EventThumbView extends View {

    private Bitmap imageBitmap;
    private Paint viewPaint;
    public String specs, price, eventName;

    public EventThumbView(Context context, String eventName) {
        super(context);
        setWillNotDraw(false);
        viewPaint = new Paint();
        this.eventName = eventName;
//        this.specs = specs;
//        this.price = price;
        this.imageBitmap = scaleDown(BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier(eventName, "drawable", context.getPackageName())), Constants.dpWidth * 5 / 4, false);

    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        viewPaint.setTextSize(30);
        viewPaint.setColor(Color.WHITE);
        viewPaint.setStyle(Paint.Style.STROKE);
        viewPaint.setStrokeWidth(3);
        canvas.drawBitmap(imageBitmap, Constants.dpWidth / 15, Constants.dpWidth / 15, viewPaint);
        viewPaint.setColor(Color.rgb(237, 197, 37));
        viewPaint.setStyle(Paint.Style.FILL);
        viewPaint.setTextSize(60);
        //canvas.drawText("Sample Event Title", Constants.dpWidth / 15, 50 + imageBitmap.getHeight() + Constants.dpWidth / 7, viewPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        setMeasuredDimension((int) Constants.dpWidth * 5 / 4, (int) Constants.dpWidth * 5 / 4 - 8);
    }

    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                   boolean filter) {

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, (int) maxImageSize,
                (int) maxImageSize, filter);
        return newBitmap;
    }
}
