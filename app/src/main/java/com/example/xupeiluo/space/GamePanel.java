package com.example.xupeiluo.space;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
//import android.support.annotation.MainThread;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.constraint.solver.widgets.Rectangle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;

import static com.example.xupeiluo.space.MainThread.canvas;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;

    private CharacterSprite player;
    private ObstacleSprite obstacle;
    private ObstacleSprite2 obstacle2;
    private DecisionPoint decpoint;
    private ObstacleManager obstacleManager;
    private ObstacleManager2 obstacleManager2;
    private Bonus bonus;
    private BonusManager bonusManager;
    private Bullet bullet;
    private Bullet bulleti;
    private BulletManager bulletManager;
    private BulletManager bulletManager3;

    private int tx, ty;
    private int score;
    private boolean leveli = false;
    private boolean levelii = false;
    //private int xStart2;

    private boolean movingPlayer = false;
    private boolean gameOver = false;
    private static boolean LevelUp = false;
    private static long LevelUpTime;
    private static boolean paused;
    private Bitmap PauseButtom;
    private Bitmap ResumeButtom;
    private Bitmap RetryButtom;

    private long gameOverTime;
    private Rect r = new Rect(); //for writing text at the center
    private Rect collide = new Rect();
    private ArrayList<Rect> bulletDecision = new ArrayList<>();
    private int obi = 0; //obstacleIndex
    //private Rect obstacleDecision = new Rect();




    public GamePanel(Context context) {
        super(context);

        getHolder().addCallback(this);

        //initialize all the objects:
        thread = new MainThread(getHolder(),this);
        player = new CharacterSprite(BitmapFactory.decodeResource(getResources(), R.drawable.character));
        decpoint = new DecisionPoint(new Rect(((player.getRight()-player.getLeft())/2 - 50), ((player.getTop() - player.getBottom())/2 - 50),
                ((player.getRight()-player.getLeft())/2 + 50), ((player.getTop() - player.getBottom())/2 + 50)), Color.rgb(255, 0, 0));
        int xStart = (int)(Math.random()*(Constants.SCREEN_WIDTH));
        int xStart2 = (int)(Math.random()*(Constants.SCREEN_WIDTH-500));
        obstacle = new ObstacleSprite(BitmapFactory.decodeResource(getResources(), R.drawable.obstacle),xStart);
        obstacle2 = new ObstacleSprite2(BitmapFactory.decodeResource(getResources(), R.drawable.obstacleii),xStart2);
        obstacleManager2 = new ObstacleManager2(500,250);
        obstacleManager = new ObstacleManager(200,350);
        bullet = new Bullet(BitmapFactory.decodeResource(getResources(), R.drawable.bullet),100,100);
        bulleti = new Bullet(BitmapFactory.decodeResource(getResources(), R.drawable.bullet),100,100);
        bulletManager = new BulletManager(decpoint);
        bonus = new Bonus(BitmapFactory.decodeResource(getResources(), R.drawable.bonus),xStart);
        bonusManager = new BonusManager(250,250);
        PauseButtom = BitmapFactory.decodeResource(getResources(), R.drawable.pause_button);
        ResumeButtom = BitmapFactory.decodeResource(getResources(), R.drawable.resume);
        RetryButtom = BitmapFactory.decodeResource(getResources(), R.drawable.retry);
        setFocusable(true);
    }

    //start of game loop
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (true) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    //end of game loop


    public void reset()
    {
        movingPlayer = false;
        gameOver = false;
        leveli = false;
        levelii = false;
        obstacleManager.reset();
        obstacleManager2.reset();
        bonusManager.reset();
        bulletManager.reset();
        player = new CharacterSprite(BitmapFactory.decodeResource(getResources(), R.drawable.character));
        decpoint = new DecisionPoint(new Rect(((player.getRight()-player.getLeft())/2 - 50), ((player.getTop() - player.getBottom())/2 - 50),
                ((player.getRight()-player.getLeft())/2 + 50), ((player.getTop() - player.getBottom())/2 + 50)), Color.rgb(255, 0, 0));
        score = 0;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        PauseGame(event);
        ResumeGame(event);
        if(!paused){
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                tx = (int) event.getX();
                ty = (int) event.getY();

                if (!gameOver && tx >= player.getLeft() && tx <= player.getRight() && ty >= player.getTop() && ty <= player.getBottom()) {
                    movingPlayer = true;
                    break;
                }
                if (gameOver && System.currentTimeMillis() - gameOverTime >= 1500) {
                    reset();
                    gameOver = false;

                }
            case MotionEvent.ACTION_MOVE:
                if (movingPlayer && !gameOver) {
                    tx = (int) event.getX();
                    ty = (int) event.getY();
                    player.touchMove(tx, ty);
                    decpoint.touchMove(tx, ty, player);
                }
                break;
            case MotionEvent.ACTION_UP:
                movingPlayer = false;
                break;
            case MotionEvent.ACTION_CANCEL:

                break;
            case MotionEvent.ACTION_OUTSIDE:

                break;
                default:

        }

        }



        return true; //true meaning the motion event has been processed
    }


    public void update() {
        if (!gameOver && !paused) {
            player.update();
            obstacle.update();
            decpoint.update();
            obstacleManager.update();
            obstacleManager2.update();

            if(!levelii)
            {
                bonusManager.update();
            }
            if(!leveli && !levelii) {
                bulletManager.update1(decpoint);
            }
            if(leveli && !levelii)
            {
                bulletManager.update2(decpoint);

            }

            if(leveli && levelii)
            {
                bulletManager.update3(decpoint);
            }
            //bulletManageri.update(decpoint);

            if (obstacleManager.playerCollide(decpoint, collide) || obstacleManager2.playerCollide(decpoint, collide)) {
                gameOverTime = System.currentTimeMillis();
//                if (System.currentTimeMillis() - gameOverTime >= 1500)
//                {
//                    restartGame = true;
//                }
                gameOver = true;
            }

            if(obstacleManager.bulletCollide(bulletManager,bulletDecision,collide)) {
                obstacleManager.removeObstacle(obstacleManager.getObstacles(), obi);
                score++;
            }

//            if(obstacleManager.bulletCollide(bulletManager,bulletDecision,collide)) {
//                obstacleManager.removeObstacle(obstacleManager.getObstacles(), obi);
//                score++;
//            }

            if(obstacleManager2.bulletCollide(bulletManager,bulletDecision,collide)) {
                obstacleManager2.removeObstacle(obstacleManager2.getObstacle2s(), obi);
                score = score + 5;
            }

            if(!leveli && !levelii) {
                if (bonusManager.playerCollide(decpoint, collide)) {
                    bonusManager.removeBonus(bonusManager.getObstacles(), obi);
                    leveli = true;
                }
            }

            if(leveli && !levelii) {
                if (bonusManager.playerCollide(decpoint, collide)) {
                    bonusManager.removeBonus(bonusManager.getObstacles(), obi);
                    levelii = true;
                }
            }

        }

    }


    /*
    public void update(Canvas canvas)
    {
        player.update(this.playerPoint,canvas);
    }
    */

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawColor(Color.BLACK);
        bonusManager.draw(canvas);
        decpoint.draw(canvas);
        player.draw(canvas);
        canvas.drawBitmap(PauseButtom, (float)(Constants.SCREEN_WIDTH - PauseButtom.getWidth()),
                0,null);
        if(paused)
        {
            canvas.drawBitmap(ResumeButtom, (float) (Constants.SCREEN_WIDTH / 2 - ResumeButtom.getWidth() / 2),
                    (float) ((Constants.SCREEN_HEIGHT / 2) - (ResumeButtom.getHeight())), null);

            canvas.drawBitmap(RetryButtom, (float) (Constants.SCREEN_WIDTH / 2 - RetryButtom.getWidth() / 2),
                    (float) ((Constants.SCREEN_HEIGHT / 2) ), null);
        }

        Paint paint_collide =  new Paint();
        paint_collide.setARGB(0, 255, 255, 255);
        canvas.drawRect(collide,paint_collide);

        obstacleManager.draw(canvas);
        obstacleManager2.draw(canvas);

        bulletManager.draw(canvas);



        Paint paint1 = new Paint();
        paint1.setTextSize(80);
        paint1.setColor(Color.WHITE);
        String currentScore = String.valueOf(score);
        String scorePrint = "Score: " + currentScore;
        drawTopText(canvas, paint1, scorePrint);

        if(gameOver)
        {
            Paint paint = new Paint();
            paint.setTextSize(120);
            paint.setColor(Color.WHITE);
            String overText = "Game Over";
            drawCenterText(canvas, paint, overText);
        }

        if(LevelUp && System.currentTimeMillis() - LevelUpTime <=1000)
        {
            Paint paint = new Paint();
            paint.setTextSize(120);
            paint.setColor(Color.WHITE);
            drawCenterText(canvas, paint,"Speed up!");
        }



    }


    //Only used to print text in the center of the screen
    private void drawCenterText(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f + r.height() / 2f - r.bottom;
        canvas.drawText(text, x, y, paint);
    }

    private void drawTopText(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        paint.getTextBounds(text, 0, text.length(), r);
        float x = 90;
        float y = 150;
        canvas.drawText(text, x, y, paint);
    }


    public static void setLevelUp()
    {
        LevelUp = true;
        LevelUpTime = System.currentTimeMillis();
    }


    public void PauseGame(MotionEvent event)
    {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                tx = (int) event.getX();
                ty = (int) event.getY();
                if (tx >= Constants.SCREEN_WIDTH - PauseButtom.getWidth() && tx <= Constants.SCREEN_WIDTH
                &&ty >=0 && ty <= PauseButtom.getHeight())
                {
                    paused = true;
                }
                break;
        }
        //paused = false;
        //return false;
    }

    public void ResumeGame(MotionEvent event)
    {
        int action = event.getAction();
        if(paused) {
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    int posx = (int) event.getX();
                    int posy = (int) event.getY();

                    if (posy > 200 && posy < ((Constants.SCREEN_HEIGHT)/2))
                    {
                        paused = false;

                    }
                    if(posy > ((Constants.SCREEN_HEIGHT)/2))
                    {
                        reset();
                        paused = false;
                    }

                    break;
            }
        }//paused = true;
    }

    public static void setTruePaused()
    {
        paused = true;
    }

    public static void setFalsePaused()
    {
        paused = false;
    }





}
