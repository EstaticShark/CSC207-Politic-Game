package com.example.politicgame.Games.BabyGame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;

import com.example.politicgame.R;

class Kiss extends Event {
  /**
   * Creates this Kiss event.
   *
   * @param babyX the X coordinate of the baby
   * @param babyY the Y coordinate of the baby
   * @param babyWidth the width of the baby
   * @param babyHeight the height of the baby
   * @param res the resources to draw the baby
   */
  Kiss(int babyX, int babyY, int babyWidth, int babyHeight, Resources res, Canvas canvas) {
    super(babyX, babyY, babyWidth, babyHeight, res, canvas);
    setX();
    setY();
    setImg(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.kisslips), 50, 30, false));
    draw();
  }

  /**
   * Returns positive or negative change in happiness based on touch inputs.
   *
   * @param v the View being used
   * @param initialX the X coordinate of the initial touch
   * @param initialY the Y coordinate of the initial touch
   * @param finalX the X coordinate of where the touch ended
   * @param finalY the Y coordinate of where the touch ended
   * @return value to change baby happiness by
   */
  @Override
  int handleTouch(View v, float initialX, float initialY, float finalX, float finalY) {
    if (Math.abs(finalX - initialX) < 5 && Math.abs(finalY - initialY) < 5) {
      if (Math.abs(finalX - getX()) < 5 && Math.abs(finalY - getY()) < 5) {
        Log.d("Kiss", "Score increased");
        return 3;
      }
    }
    Log.d("Kiss", "Score decreased");
    return -5;
  }
}