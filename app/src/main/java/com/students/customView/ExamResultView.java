package com.students.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.students.R;

/**
 * Purpose of this custom view is to show an exam result of a student.
 * It's show a student's rating like indicator. The color of indicator
 * depend of student's rating.
 */
public class ExamResultView extends View {

    private final int VERY_GOOD_RESULT_COLOR = Color.parseColor("#FF64DD17");
    private final int GOOD_RESULT_COLOR = Color.parseColor("#FF81C784");
    private final int NORMAL_RESULT_COLOR = Color.parseColor("#FFFFEB3B");
    private final int BAD_RESULT_COLOR = Color.parseColor("#FFFFD54F");
    private final int VERY_BAD_RESULT = Color.parseColor("#FFEF5350");
    private final int BACKGROUND_COLOR = Color.parseColor("#FFE0E0E0");
    private final int NO_DATA_COLOR = Color.parseColor("#D6D6D7");

    int contentWidth;
    int contentHeight;
    private Paint paint = new Paint();
    private Integer resultPart;

    private TypedArray attr;

    public ExamResultView(Context context) {
        super(context);
        init(null, 0);
    }

    public ExamResultView(Context context, int result) {
        super(context);
        init(null, 0);
        resultPart = result;
    }

    public ExamResultView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ExamResultView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        attr = getContext().obtainStyledAttributes(
                attrs, R.styleable.ExamResultView, defStyle, 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int desiredWidth = 80;
        int desiredHeight = 30;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = Math.min(desiredWidth, widthSize);
        } else {
            width = desiredWidth;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height = Math.min(desiredHeight, heightSize);
        } else {
            height = desiredHeight;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        contentWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        contentHeight = getHeight() - getPaddingTop() - getPaddingBottom();

        if (resultPart != null) {
            drawBackgroundRec(canvas);
            drawResultRec(canvas, resultPart);
        } else if(attr.getInt(R.styleable.ExamResultView_rating, 0) > 0){
            drawBackgroundRec(canvas);
            drawResultRec(canvas, attr.getInt(R.styleable.ExamResultView_rating, 0));
        }else {
            drawNoDataRec(canvas);
        }
    }

    private void drawNoDataRec(Canvas canvas) {
        paint.setColor(NO_DATA_COLOR);
        Rect womanRec = new Rect(getPaddingLeft(), getPaddingTop(),
                contentWidth, contentHeight);
        canvas.drawRect(womanRec, paint);
    }

    private void drawResultRec(Canvas canvas, int rating) {
        if(resultPart >= 90){
            paint.setColor(VERY_GOOD_RESULT_COLOR);
        } else if(resultPart >= 75){
            paint.setColor(GOOD_RESULT_COLOR);
        }else if(resultPart >= 50){
            paint.setColor(NORMAL_RESULT_COLOR);
        }else if(resultPart >= 25){
            paint.setColor(BAD_RESULT_COLOR);
        }else if(resultPart <25){
            paint.setColor(VERY_BAD_RESULT);
        }

        Rect resultRec = new Rect(getPaddingLeft(), getPaddingTop(),
                contentWidth / 100 * rating, contentHeight);
        canvas.drawRect(resultRec, paint);
    }

    private void drawBackgroundRec(Canvas canvas) {
        paint.setColor(BACKGROUND_COLOR);
        Rect backgroundRec = new Rect(getPaddingLeft(), getPaddingTop(),
                contentWidth, contentHeight);

        canvas.drawRect(backgroundRec, paint);
    }

    public void setResultPart(Integer resultPart) {
        this.resultPart = resultPart;
        invalidate();
    }
}
