package com.example.xupeiluo.space;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class DecisionPoint implements GameObject
{
    private Rect Decpoint;
    private int color;
    //private int x,y;

    public DecisionPoint(Rect Decpoint, int color)
    {
        this.Decpoint = Decpoint;
        this.color = color;
        //this.x = Decpoint.centerX();
        //this.y = Decpoint.centerY();
    }
    public Rect getDecpoint()
    {
        return Decpoint;
    }

    public void draw(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(Decpoint,paint);
        //canvas.drawBitmap(character,100,100,null);
    }

    @Override
    public void update()
    {

    }

    public void touchMove(int tx,int ty,CharacterSprite sprite){
        //Decpoint.set((tx+(sprite.getImage().getWidth()/2-50)),(ty+(sprite.getImage().getHeight()/2-50)),
          //      (ty+(sprite.getImage().getWidth()/2+50)),(ty+(sprite.getImage().getHeight()/2+50)));
        Decpoint.set((tx-50),(ty-50),(tx-50),(ty+50));
    }

    @Override
    public void touchMove(int tx, int ty)
    {

    }
}
