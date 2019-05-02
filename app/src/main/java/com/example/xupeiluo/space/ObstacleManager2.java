package com.example.xupeiluo.space;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.lang.reflect.Array;
import java.util.ArrayList;

//Example: Rectangles moving down the screen;
public class ObstacleManager2
{
    //higher index = lower on screen = higher Y value;
    private ArrayList<ObstacleSprite2> obstacle2s;
    private int playerGap;
    //private int obstacleGap;
    private long startTime;
    private int speed = 15;
    private long levelStart;
    // private Bitmap bmp;
    /*
    public void setBmp(Bitmap bmp)
    {
        this.bmp = bmp;
    }
    */

    public ObstacleManager2(int playerGap, int obstacleGap)
    {
        this.playerGap = playerGap;

        obstacle2s = new ArrayList<>();

        //this.obstacleGap = obstacleGap;

        this.startTime = System.currentTimeMillis();

        this.levelStart = System.currentTimeMillis();

        //populateObstacles();

    }
    public void reset()
    {
        for(int i = 0; i < obstacle2s.size(); i++)
        {
            obstacle2s.remove(i);
        }
        this.startTime = System.currentTimeMillis();

        this.levelStart = System.currentTimeMillis();

        this.speed = 15;

        //populateObstacles();
    }

    public void update()
    {

        int elapsedTime = (int) (System.currentTimeMillis() - startTime);
        //startTime = System.currentTimeMillis();
        //float speed = Constants.SCREEN_HEIGHT/15000.0f;

        for(ObstacleSprite2 ob: obstacle2s)
        {
            ob.incrementY(speed);
        }

        //if (obstacles.get(obstacles.size()-1).getBottomPosition() >= Constants.SCREEN_HEIGHT) {
        if (elapsedTime > 5000)
        {
            int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH - playerGap));
            obstacle2s.add(0, new ObstacleSprite2(ObstacleSprite2.getImage(), xStart));
            startTime = System.currentTimeMillis();
        }


        if (System.currentTimeMillis() -  levelStart > 10000 && speed <= 40)
        {
            speed = speed + 10;
            levelStart = System.currentTimeMillis();
            GamePanel.setLevelUp();
        }


        for (int i = 0; i < obstacle2s.size(); i++)
        {
            if(obstacle2s.get(i).getTop()>Constants.SCREEN_HEIGHT)
            {
                obstacle2s.remove(i);
            }
        }
    }

    public ArrayList<ObstacleSprite2> getObstacle2s()
    {
        return obstacle2s;
    }

    public void populateObstacles()
    {

        int xStart = (int)(Math.random()*(Constants.SCREEN_WIDTH - playerGap));
        obstacle2s.add(0,new ObstacleSprite2(ObstacleSprite2.getImage(),xStart));

    }

    public void draw(Canvas canvas)
    {
        for(ObstacleSprite2 ob : obstacle2s)
            ob.draw(canvas);
    }

    public boolean playerCollide(DecisionPoint decpoint,Rect collide)
    {
        for(ObstacleSprite2 ob : obstacle2s)
            if(ob.PlayerCollide(decpoint,collide))
            {
                return true;
            }

        return false;
    }

    public boolean bulletCollide(BulletManager manager, ArrayList<Rect> bulletDecision, Rect enemy)
    {
        for(ObstacleSprite2 ob : obstacle2s)
            if(ob.bulletCollide(manager,bulletDecision,enemy))
            {
                return true;
            }

        return false;
    }

    public void removeObstacle(ArrayList<ObstacleSprite2> obstacle2s,int i)
    {
        obstacle2s.remove(i);
    }

//    public void speedUp()
//    {
//        speed +=10;
//        GamePanel.setRestartGame();
//    }
}
