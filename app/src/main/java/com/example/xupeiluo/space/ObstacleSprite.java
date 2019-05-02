package com.example.xupeiluo.space;

import android.graphics.Bitmap;
import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
//import android.support.constraint.solver.widgets.Rectangle;
//import android.graphics.Rect;

public class ObstacleSprite implements GameObject
{
        private static Bitmap image;
        private int x, y;
        //private int speed = 100;
        //private MotionEvent movement;
        private int HP;

        public ObstacleSprite(Bitmap bmp,int xStart)
        {
            image = bmp;
            x = xStart;
            y = 0;
            HP = 3;
        }
        public void incrementY(float y)
        {
            this.y += y;
        }

        public int getTop()
        {
            return y;
        }

        public static Bitmap getImage()
        {
            return image;
        }

        public void decrementHP()
        {
            this.HP--;
        }

        public int getHP()
        {
            return this.HP;
        }


        @Override
        public void draw (Canvas canvas)
        {
            canvas.drawBitmap(image, x, y, null);
        }

        @Override
        public void update ()
        {
            //y+=5;
        }
        public void touchMove (int tx, int ty)
        {

        }
        //public boolean playerCollide(DecisionPoint decpoint)
       //{

       //}
       public void touchMove(int tx,int ty,CharacterSprite sprite)
       {

       }
    public boolean PlayerCollide(DecisionPoint decpoint,Rect enemy)
    {
        enemy.set(x,y,x+image.getWidth(),y+image.getHeight());
        return (decpoint.getDecpoint().intersect(enemy));
        //return true;
    }

    public boolean bulletCollide(BulletManager manager, ArrayList<Rect> bulletDecision, Rect enemy)
    {
        enemy.set(x, y, x + image.getWidth(), y + image.getHeight());
        //ArrayList<Rect> bulletDetect = new ArrayList<>();
        if(manager.getBullets().size() == 0){
            return false;
        }
        else {
            int i;

            for (i = 0; i < manager.getBullets().size(); i++) {

                Rect detect = new Rect();

                detect.set(manager.getBullets().get(i).getLeft(), manager.getBullets().get(i).getTop(),
                        manager.getBullets().get(i).getRight(), manager.getBullets().get(i).getBottom());

                //bulletDetect.add(i,detect);

                bulletDecision.add(i, detect);

            }
            int j;
            for (j = 0; j < i; j++) {
                if (bulletDecision.get(j).intersect(enemy)) {
                    decrementHP();
                    manager.getBullets().remove(j);
                }
                if (this.HP == 0) {
                    return true;
                }
            }
            //i = 0;
            for (i = 0; i < manager.getBulletis().size(); i++) {

                Rect detect = new Rect();
                //Rect detect2 = new Rect();

                detect.set(manager.getBulletis().get(i).getLeft(), manager.getBulletis().get(i).getTop(),
                        manager.getBulletis().get(i).getRight(), manager.getBulletis().get(i).getBottom());

//                detect2.set(manager.getBulletis().get(i).getLeft(), manager.getBulletis().get(i).getTop(),
//                        manager.getBulletis().get(i).getRight(), manager.getBulletis().get(i).getBottom());

                //bulletDetect.add(i,detect);

                bulletDecision.add(i, detect);
                //bulletDecision.add(i, detect2);

            }

            for (j = 0; j < i; j++) {
                if (bulletDecision.get(j).intersect(enemy)) {
                    decrementHP();
                    manager.getBulletis().remove(j);
                    //manager.getBulletis().remove(j);
                }
                if (this.HP == 0) {
                    return true;
                }
            }
        }
        return false;
    }

        /*
        if (bulletDecision.intersect(enemy))
        {
            this.HP--;
        }
        */
        //return (bulletDecision.intersect(enemy));

        //return true;
        //set(left, top, right, bottom)
}




