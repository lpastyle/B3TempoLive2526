package com.example.b3tempolive2526;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.content.ContextCompat;

/**
 * TODO: document your custom view class.
 */
public class DayColorView extends View {
    private static final float CIRCLE_SCALE = 0.9f; // circle will occupy 90% of room's view

    private String captionText;
    private int captionTextColor = Color.BLACK;
    private float captionTextSize = 12f;
    private int dayCircleColor = Color.GRAY;

    private Context context;

    private TextPaint textPaint;
    private Paint circlePaint;

    public DayColorView(Context context) {
        super(context);
        init(context,null, 0);
    }

    public DayColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public DayColorView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        this.context = context;
        // Load XML attributes
        try (TypedArray a = getContext().obtainStyledAttributes(
                attrs,
                R.styleable.DayColorView,
                defStyle,
                0)) {

            captionText = a.getString(R.styleable.DayColorView_captionText);
            if (captionText == null) {
                captionText = context.getString(R.string.not_set);
            }
            captionTextColor = a.getColor(R.styleable.DayColorView_captionTextColor, captionTextColor);
            captionTextSize = a.getDimension(R.styleable.DayColorView_captionTextSize, getResources().getDimension(R.dimen.tempo_color_view_text_size));
            dayCircleColor = a.getColor(R.styleable.DayColorView_dayCircleColor, ContextCompat.getColor(context, R.color.tempo_undecided_day_bg));

            //a.recycle();
        }
        // Set up a default TextPaint object
        textPaint = new TextPaint();
        setTextPaintAndMeasurements();

        // set up a default paint object
        circlePaint = new Paint();
        setCirclePaint();

    }

    private void setTextPaintAndMeasurements() {
        // set up a default TextPaint object
        textPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(captionTextSize);
        textPaint.setColor(captionTextColor);
    }

    private void setCirclePaint() {
        // set up a paint object to draw circle
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setColor(dayCircleColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        // Draw circle
        float radius = Math.min(contentHeight, contentWidth) * 0.5f * CIRCLE_SCALE;
        canvas.drawCircle(
                paddingLeft + contentWidth * 0.5f,
                paddingTop + contentHeight * 0.5f,
                radius,
                circlePaint);

        // Draw the text.
        canvas.drawText(captionText,
                paddingLeft + contentWidth  * 0.5f,
                paddingTop + contentHeight  * 0.5f,
                textPaint);

    }

}