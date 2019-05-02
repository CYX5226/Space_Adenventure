package com.example.xupeiluo.space;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.lang.reflect.Array;
import java.util.ArrayList;

//Example: Rectangles moving down the screen;
public class BonusManager
{
    //higher index = lower on screen = higher Y value;
    private ArrayList<Bonus> bonuses;
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

    public BonusManager(int playerGap, int obstacleGap)
    {
        this.playerGap = playerGap;

        bonuses = new ArrayList<>();

        //this.obstacleGap = obstacleGap;

        this.startTime = System.currentTimeMillis();

        this.levelStart = System.currentTimeMillis();

       // populateObstacles();

    }
    public void reset()
    {
        for(int i = 0; i < bonuses.size(); i++)
        {
            bonuses.remove(i);
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

        for(Bonus ob: bonuses)
        {
            ob.incrementY(speed);
        }

        //if (obstacles.get(obstacles.size()-1).getBottomPosition() >= Constants.SCREEN_HEIGHT) {
        if (elapsedTime > 20000)
        {
            int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH - playerGap));
            bonuses.add(0, new Bonus(Bonus.getImage(), xStart));
            startTime = System.currentTimeMillis();
        }


        if (System.currentTimeMillis() -  levelStart > 10000 && speed <= 50)
        {
            speed = speed + 10;
            levelStart = System.currentTimeMillis();
            GamePanel.setLevelUp();
        }


        for (int i = 0; i < bonuses.size(); i++)
        {
            if(bonuses.get(i).getTop()>Constants.SCREEN_HEIGHT)
            {
                bonuses.remove(i);
            }
        }
    }

    public ArrayList<Bonus> getObstacles()
    {
        return bonuses;
    }

    public void populateObstacles()
    {

        int xStart = (int)(Math.random()*(Constants.SCREEN_WIDTH - playerGap));
        bonuses.add(0,new Bonus(Bonus.getImage(),xStart));

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
        for(Bonus ob : bonuses)
            ob.draw(canvas);
    }

    public boolean playerCollide(DecisionPoint decpoint,Rect collide)
    {
        for(Bonus ob : bonuses)
            if(ob.PlayerCollide(decpoint,collide))
            {
                return true;
            }

        return false;
    }

//    public boolean bulletCollide(BulletManager manager, ArrayList<Rect> bulletDecision, Rect enemy)
//    {
//        for(Bonus ob : bonuses)
//            if(ob.bulletCollide(manager,bulletDecision,enemy))
//            {
//                return true;
//            }
//
//        return false;
//    }

    public void removeBonus(ArrayList<Bonus> bonuses,int i)
    {
        bonuses.remove(i);
    }

//    public void speedUp()
//    {
//        speed +=10;
//        GamePanel.setRestartGame();
//    }
}
