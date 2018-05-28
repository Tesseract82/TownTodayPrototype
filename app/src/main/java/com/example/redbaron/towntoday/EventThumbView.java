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
        this.imageBitmap = scaleDown(BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier(eventName, "drawable", context.getPackageName())), Constants.dpWidth * 4 / 4, false);

    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        viewPaint.setTextSize(30);
        viewPaint.setColor(Color.WHITE);
        viewPaint.setStyle(Paint.Style.STROKE);
        viewPaint.setStrokeWidth(3);
        canvas.drawRoundRect(Constants.dpPadding, Constants.dpPadding, Constants.dpWidth * 6 / 4 - 8 * Constants.dpPadding, Constants.dpWidth * 6 / 4 - 8 * Constants.dpPadding,
                Constants.dpWidth / 8, Constants.dpWidth / 8, viewPaint);
        canvas.drawBitmap(imageBitmap, Constants.dpWidth / 15, Constants.dpWidth / 15, viewPaint);
        viewPaint.setColor(Color.rgb(237, 197, 37));
        viewPaint.setStyle(Paint.Style.FILL);
        canvas.drawText("5/15/18", imageBitmap.getWidth() + Constants.dpWidth / 7, 30 + Constants.dpWidth / 15, viewPaint);
        canvas.drawText("3:40 PM", imageBitmap.getWidth() + Constants.dpWidth / 7, 30 + Constants.dpHeight / 5 + Constants.dpWidth / 15, viewPaint);
        viewPaint.setTextSize(40);
        canvas.drawText("$$", imageBitmap.getWidth() + Constants.dpWidth / 7, 30 + Constants.dpHeight * 2 / 5 + Constants.dpWidth / 15, viewPaint);
        viewPaint.setTextSize(60);
        canvas.drawText("Sample Event Title", Constants.dpWidth / 15, 50 + imageBitmap.getHeight() + Constants.dpWidth / 7, viewPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        setMeasuredDimension((int) Constants.dpWidth * 6 / 4, (int) Constants.dpWidth * 6 / 4 - 8);
    }

    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                   boolean filter) {
        float ratio = Math.min(
                (float) maxImageSize / realImage.getWidth(),
                (float) maxImageSize / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        return newBitmap;
    }


}
