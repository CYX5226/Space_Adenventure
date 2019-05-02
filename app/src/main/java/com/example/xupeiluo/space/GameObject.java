package com.example.xupeiluo.space;

import android.graphics.Canvas;

public interface GameObject {
    public void draw(Canvas canvas);
    //public void update(Canvas canvas);
    public void update();
    public void touchMove(int tx, int ty);
    public void touchMove(int tx,int ty,CharacterSprite sprite);

}