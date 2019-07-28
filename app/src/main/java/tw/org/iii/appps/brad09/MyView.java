package tw.org.iii.appps.brad09;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {
    private Resources res;
    private float viewW, viewH;
    private boolean isInit;
    private Bitmap ballBmp;
    private float ballW, ballH, ballX, ballY;
    private Matrix matrix;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        res = context.getResources();
        matrix = new Matrix();
    }

    private void init(){
        viewW = getWidth(); viewH = getHeight();
        ballW = viewW / 12f; ballH = ballW;

        ballBmp = BitmapFactory.decodeResource(res, R.drawable.ball);
        matrix.reset();
        matrix.postScale(ballW/ballBmp.getWidth(), ballH/ballBmp.getHeight());
        ballBmp = Bitmap.createBitmap(ballBmp, 0, 0,
            ballBmp.getWidth(), ballBmp.getHeight(), matrix, false);

        ballX = ballY = 10;


        isInit = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInit) init();

        canvas.drawBitmap(ballBmp, ballX , ballY, null);

    }
}
