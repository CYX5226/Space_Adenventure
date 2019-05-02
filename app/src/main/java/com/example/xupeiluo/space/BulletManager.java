package com.example.xupeiluo.space;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;

import java.util.ArrayList;


public class BulletManager
{
    //higher index = lower on screen = higher Y value;
    private static ArrayList<Bullet> bullets;
    private static ArrayList<Bullet> bulletis;
    private static ArrayList<Bullet> bulletiis;
    //private int playerGap;
    //private int obstacleGap;
    private static long startTime;
    private static int speed = 80;
    // private Bitmap bmp;
    /*
    public void setBmp(Bitmap bmp)
    {
        this.bmp = bmp;
    }
    */

    public BulletManager(DecisionPoint decisionPoint)
    {
        //this.playerGap = playerGap;

        bullets = new ArrayList<>();
        bulletis = new ArrayList<>();
        bulletiis = new ArrayList<>();

        //this.obstacleGap = obstacleGap;

        this.startTime = System.currentTimeMillis();

        populateBullets(decisionPoint);

    }

    public ArrayList<Bullet> getBullets()
    {
        return bullets;
    }

    public ArrayList<Bullet> getBulletis()
    {
        return bulletis;
    }

    public ArrayList<Bullet> getBulletiis()
    {
        return bulletiis;
    }

    public void reset()
    {
        int size = bullets.size();
        int sizei = bulletis.size();
        int sizeii = bulletiis.size();
        for(int i = 0; i < size; i++)
        {
            bullets.remove(0);
        }

        for(int i = 0; i < sizei; i++)
        {
            bulletis.remove(0);
        }

        for(int i = 0; i < sizeii; i++)
        {
            bulletiis.remove(0);
        }
        this.startTime = System.currentTimeMillis();
    }

    public static void update1(DecisionPoint decisionPoint)
    {

        int elapsedTime = (int) (System.currentTimeMillis() - startTime);
        //startTime = System.currentTimeMillis();
        //float speed = Constants.SCREEN_HEIGHT/15000.0f;

        for(Bullet ob: bullets)
        {
            ob.decrementY(speed);
        }

        //if (obstacles.get(obstacles.size()-1).getBottomPosition() >= Constants.SCREEN_HEIGHT) {
        if (elapsedTime > 200)
        {
            //int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH));
            int xStart = decisionPoint.getDecpoint().centerX();
            int yStart = (int) decisionPoint.getDecpoint().centerY();
            bullets.add(0, new Bullet(Bullet.getImage(), xStart,yStart));
            startTime = System.currentTimeMillis();
            //obstacles.remove(obstacles.size() - 1);
        }

        for (int i = 0; i < bullets.size(); i++)
        {
            if(bullets.get(i).getBottom()<0)
            {
                bullets.remove(i);
            }
        }

    }

    public static void update2(DecisionPoint decisionPoint)
    {

        int elapsedTime = (int) (System.currentTimeMillis() - startTime);
        //startTime = System.currentTimeMillis();
        //float speed = Constants.SCREEN_HEIGHT/15000.0f;

        for(Bullet ob: bullets)
        {
            ob.decrementY(speed);
            ob.incrementX(10);

        }
        for(Bullet ob: bulletis)
        {
            ob.decrementY(speed);
            ob.decrementX(10);
        }
//        for(Bullet ob: bulletiis)
//        {
//            ob.decrementY(speed);
//            ob.incrementX(10);
//        }



        //if (obstacles.get(obstacles.size()-1).getBottomPosition() >= Constants.SCREEN_HEIGHT) {
        if (elapsedTime > 200)
        {
            //int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH));
            int xStart = decisionPoint.getDecpoint().centerX();
            int yStart = (int) decisionPoint.getDecpoint().centerY();
            bullets.add(0, new Bullet(Bullet.getImage(), xStart,yStart));
            bulletis.add(0, new Bullet(Bullet.getImage(), xStart,yStart));
            //bulletiis.add(0, new Bullet(Bullet.getImage(), xStart,yStart));
            startTime = System.currentTimeMillis();
            //obstacles.remove(obstacles.size() - 1);
        }

        for (int i = 0; i < bullets.size(); i++)
        {
            if(bullets.get(i).getBottom()<0)
            {
                bullets.remove(i);
            }
        }

        for (int i = 0; i < bulletis.size(); i++)
        {
            if(bulletis.get(i).getBottom()<0)
            {
                bulletis.remove(i);
            }
        }


        //if (obstacles.get(obstacles.size() - 1).getBottomPosition() >= Constants.SCREEN_HEIGHT)
        //{
        //    obstacles.remove(obstacles.size() - 1);
        //}
    }

    public static void update3(DecisionPoint decisionPoint)
    {

        int elapsedTime = (int) (System.currentTimeMillis() - startTime);
        //startTime = System.currentTimeMillis();
        //float speed = Constants.SCREEN_HEIGHT/15000.0f;

        for(Bullet ob: bullets)
        {
            ob.decrementY(speed);

        }
        for(Bullet ob: bulletis)
        {
            ob.decrementY(speed);
            ob.decrementX(20);
        }
        for(Bullet ob: bulletiis)
        {
            ob.decrementY(speed);
            ob.incrementX(20);
        }



        //if (obstacles.get(obstacles.size()-1).getBottomPosition() >= Constants.SCREEN_HEIGHT) {
        if (elapsedTime > 200)
        {
            //int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH));
            int xStart = decisionPoint.getDecpoint().centerX();
            int yStart = (int) decisionPoint.getDecpoint().centerY();
            bullets.add(0, new Bullet(Bullet.getImage(), xStart,yStart));
            bulletis.add(0, new Bullet(Bullet.getImage(), xStart,yStart));
            bulletiis.add(0, new Bullet(Bullet.getImage(), xStart,yStart));
            startTime = System.currentTimeMillis();
            //obstacles.remove(obstacles.size() - 1);
        }

        for (int i = 0; i < bullets.size(); i++)
        {
            if(bullets.get(i).getBottom()<0)
            {
                bullets.remove(i);
            }
        }

        for (int i = 0; i < bulletis.size(); i++)
        {
            if(bulletis.get(i).getBottom()<0)
            {
                bulletis.remove(i);
            }
        }

        for (int i = 0; i < bulletiis.size(); i++)
        {
            if(bulletiis.get(i).getBottom()<0)
            {
                bulletiis.remove(i);
            }
        }
    }



    public void populateBullets(DecisionPoint decisionPoint)
    {
        //int currY = -5*Constants.SCREEN_HEIGHT/4;
        //int elapsedTime = (int) (System.currentTimeMillis() - startTime);
        //startTime = System.currentTimeMillis();
        int xStart = decisionPoint.getDecpoint().centerX();
        int yStart = decisionPoint.getDecpoint().centerY();
        bullets.add(0,new Bullet(Bullet.getImage(),xStart,yStart));
        bulletis.add(0, new Bullet(Bullet.getImage(), xStart,yStart));
        bulletiis.add(0, new Bullet(Bullet.getImage(), xStart,yStart));
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
//
//    public void populateBulletis(DecisionPoint decisionPoint)
//    {
//        //int currY = -5*Constants.SCREEN_HEIGHT/4;
//        //int elapsedTime = (int) (System.currentTimeMillis() - startTime);
//        //startTime = System.currentTimeMillis();
//        int xStart = decisionPoint.getDecpoint().centerX();
//        int yStart = decisionPoint.getDecpoint().centerY();
//
//        bulletis.add(0,new Bullet(Bullet.getImage(),xStart,yStart));
//       /*
//        while(elapsedTime > 2000)
//        {
//            xStart = (int)(Math.random()*(Constants.SCREEN_WIDTH - playerGap));
//            obstacles.add(0,new ObstacleSprite(ObstacleSprite.getImage(),xStart));
//            elapsedTime = (int) (System.currentTimeMillis() - startTime);
//            startTime = System.currentTimeMillis();
//            //currY += 1000;
//            //obstacles.add(0,new ObstacleSprite(ObstacleSprite.getImage(),xStart));
//        }
//        */
//    }



    public void draw(Canvas canvas)
    {
        for(Bullet ob : bullets)
            ob.draw(canvas);
        for(Bullet ob : bulletis)
            ob.draw(canvas);
        for(Bullet ob : bulletiis)
            ob.draw(canvas);
    }
/*
    public boolean obstacleCollide(DecisionPoint decpoint,Rect collide)
    {
        for(Bullet ob : bullets)
            if(ob.PlayerCollide(decpoint,collide))
            {return true;}

        return false;
    }
    */
}

