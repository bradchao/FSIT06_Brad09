package tw.org.iii.appps.brad09;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MyView extends View {
    private Resources res;
    private float viewW, viewH;
    private boolean isInit;
    private Bitmap ballBmp;
    private float ballW, ballH, ballX, ballY, dx, dy;
    private Matrix matrix;
    private Timer timer;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        res = context.getResources();
        matrix = new Matrix();
        timer = new Timer();
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
        dx = dy = 8;

        setBackgroundResource(R.drawable.bg);

        timer.schedule(new refreshView(), 0, 17);
        timer.schedule(new BallTask(), 1*1000, 30);

        isInit = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInit) init();

        canvas.drawBitmap(ballBmp, ballX , ballY, null);

    }

    private class BallTask extends TimerTask {
        @Override
        public void run() {
            if (ballX < 0 || ballX + ballW > viewW){
                dx *= -1;
            }
            if (ballY < 0 || ballY + ballH > viewH){
                dy *= -1;
            }


            ballX += dx;
            ballY += dy;
        }
    }

    private class refreshView extends TimerTask {
        @Override
        public void run() {
            postInvalidate();
        }
    }

}
