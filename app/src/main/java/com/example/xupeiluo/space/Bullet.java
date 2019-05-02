package com.example.xupeiluo.space;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.constraint.solver.widgets.Rectangle;

import static com.example.xupeiluo.space.MainThread.canvas;

public class Bullet implements GameObject {
    private static Bitmap image;
    private int x, y;
    //private int HP;

    //private int speed = 100;
    //private MotionEvent movement;

    public Bullet(Bitmap bmp, int xStart, int yStart) {
        image = bmp;
        x = xStart;
        y = yStart;

    }

    public void decrementY(float y) {
        this.y -= y;
    }

    public void decrementX(float x)
    {
        this.x -= x;
    }

    public void incrementX(float x)
    {
        this.x += x;
    }
/*
    public int getHP()
    {
        return HP;
    }
    */

    public static Bitmap getImage() {
        return image;
    }

    public int getBottom() {
        return y + this.image.getHeight();
    }

    public int getLeft() {
        return x;
    }

    public int getTop() {
        return y;
    }

    public int getRight()
    {
        return x + this.image.getWidth();
    }
    @Override
    public void draw (Canvas canvas)
    {
        canvas.drawBitmap(image, x, y, null);
    }

    @Override
    public void update ()
    {

    }
    public void touchMove (int tx, int ty)
    {

    }

    public void touchMove(int tx,int ty,CharacterSprite sprite)
    {

    }


}
