package com.example.xupeiluo.space;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.lang.reflect.Array;
import java.util.ArrayList;

//Example: Rectangles moving down the screen;
public class ObstacleManager
{
    //higher index = lower on screen = higher Y value;
    private ArrayList<ObstacleSprite> obstacles;
    private int playerGap;
    //private int obstacleGap;
    private long startTime;
    private int speed = 30;
    private long levelStart;
   // private Bitmap bmp;
    /*
    public void setBmp(Bitmap bmp)
    {
        this.bmp = bmp;
    }
    */

    public ObstacleManager(int playerGap, int obstacleGap)
    {
        this.playerGap = playerGap;

        obstacles = new ArrayList<>();

        //this.obstacleGap = obstacleGap;

        this.startTime = System.currentTimeMillis();

        this.levelStart = System.currentTimeMillis();

        populateObstacles();

    }
    public void reset()
    {
        for(int i = 0; i < obstacles.size(); i++)
        {
            obstacles.remove(i);
        }
        this.startTime = System.currentTimeMillis();

        this.levelStart = System.currentTimeMillis();

        this.speed = 30;

        //populateObstacles();
    }

    public void update()
    {

        int elapsedTime = (int) (System.currentTimeMillis() - startTime);
        //startTime = System.currentTimeMillis();
        //float speed = Constants.SCREEN_HEIGHT/15000.0f;

        for(ObstacleSprite ob: obstacles)
        {
            ob.incrementY(speed);
        }

            //if (obstacles.get(obstacles.size()-1).getBottomPosition() >= Constants.SCREEN_HEIGHT) {
        if (elapsedTime > 3000)
        {
            int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH - playerGap));
            obstacles.add(0, new ObstacleSprite(ObstacleSprite.getImage(), xStart));
            startTime = System.currentTimeMillis();
        }


        if (System.currentTimeMillis() -  levelStart > 10000 && speed <= 70)
        {
            speed = speed + 10;
            levelStart = System.currentTimeMillis();
            GamePanel.setLevelUp();
        }


        for (int i = 0; i < obstacles.size(); i++)
        {
            if(obstacles.get(i).getTop()>Constants.SCREEN_HEIGHT)
            {
                obstacles.remove(i);
            }
        }
    }

    public ArrayList<ObstacleSprite> getObstacles()
    {
        return obstacles;
    }

    public void populateObstacles()
    {

        int xStart = (int)(Math.random()*(Constants.SCREEN_WIDTH - playerGap));
        obstacles.add(0,new ObstacleSprite(ObstacleSprite.getImage(),xStart));

        //int currY = -5*Constants.SCREEN_HEIGHT/4;
        //int elapsedTime = (int) (System.currentTimeMillis() - startTime);
        //startTime = System.currentTimeMillis();
       /*
        while(elapsedTime > 2000)
        {
            xStart = (int)(Math.random()*(Constants.SCREEN_WIDTH - playerGap));
            obstacles.add(0,new ObstacleSprite(ObstacleSprite.getImage(),xStart));
            elapsedTime = (int) (System.currentTimeMillis() - startTime);
            startTime = System.currentTimeMillis();
            //currY += 1000;
            //obstacles.add(0,new ObstacleSprite(ObstacleSprite.getImage(),xStart));
        }
        */

    }

    public void draw(Canvas canvas)
    {
        for(ObstacleSprite ob : obstacles)
            ob.draw(canvas);
    }

    public boolean playerCollide(DecisionPoint decpoint,Rect collide)
    {
        for(ObstacleSprite ob : obstacles)
            if(ob.PlayerCollide(decpoint,collide))
            {
                return true;
            }

            return false;
    }

    public boolean bulletCollide(BulletManager manager, ArrayList<Rect> bulletDecision, Rect enemy)
    {
        for(ObstacleSprite ob : obstacles)
            if(ob.bulletCollide(manager,bulletDecision,enemy))
            {
                return true;
            }

        return false;
    }

    public void removeObstacle(ArrayList<ObstacleSprite> obstacles,int i)
    {
        obstacles.remove(i);
    }

//    public void speedUp()
//    {
//        speed +=10;
//        GamePanel.setRestartGame();
//    }
}
