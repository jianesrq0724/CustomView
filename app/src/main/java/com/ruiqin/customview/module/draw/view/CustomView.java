package com.ruiqin.customview.module.draw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by ruiqin.shen
 * 类说明：
 */

public class CustomView extends View {

    public CustomView(Context context) {
        super(context);
    }

    Paint mPaint = new Paint();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(300, 300, 200, mPaint);
    }
}
