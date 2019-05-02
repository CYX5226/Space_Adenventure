package com.example.xupeiluo.space;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;

public class CharacterSprite implements GameObject
{
    private Bitmap image;
    private int x,y;
    //private MotionEvent movement;

    public CharacterSprite(Bitmap bmp)
    {
        image = bmp;
        x = (Constants.SCREEN_WIDTH /2) - (image.getWidth()/2);
        y = Constants.SCREEN_HEIGHT - image.getHeight();
    }
    public Bitmap getImage()
    {
        return image;
    }

    public int getLeft()
    {
        return x;
    }

    public int getTop()
    {
        return y;
    }

    public int getBottom()
    {
        return y+image.getHeight();
    }

    public int getRight()
    {
        return x + image.getWidth();
    }


    @Override
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(image,x,y,null);
    }

    @Override
    public void update()
    {

    }
    public void touchMove(int tx,int ty){
        x= (int) tx-image.getWidth()/2;
        y= (int) ty-image.getHeight()/2;

    }

    @Override
    public void touchMove(int tx, int ty, CharacterSprite sprite)
    {

    }

}
