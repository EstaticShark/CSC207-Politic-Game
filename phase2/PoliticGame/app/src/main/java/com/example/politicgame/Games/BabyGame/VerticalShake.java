package com.example.politicgame.Games.BabyGame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;

import com.example.politicgame.R;

class VerticalShake extends Event {

  private Bitmap img;
  private int swipesLeft = 5;
  private boolean moveUp = true;

  /**
   * Creates this VerticalShake event.
   *
   * @param babyX the X coordinate of the baby
   * @param babyY the Y coordinate of the baby
   * @param babyWidth the width of the baby
   * @param babyHeight the height of the baby
   * @param res the resources to draw the baby
   */
  VerticalShake(int babyX, int babyY, int babyWidth, int babyHeight, Resources res) {
    super(babyX, babyY, babyWidth, babyHeight, res);
    setX((int) (Math.random() * (babyWidth / 3) + babyX));
    setY((int) (Math.random() * (babyHeight / 2) + babyY + (babyHeight / 2)));
    img = BitmapFactory.decodeResource(res, R.drawable.updownarrow);
    setImg(img);
  }

  /**
   * Returns positive or negative change in happiness based on touch inputs
   *
   * @param v the View being used
   * @param initialX the X coordinate of the initial touch
   * @param initialY the Y coordinate of the initial touch
   * @param movingX the updated X coordinate from finger movement
   * @param movingY the updated Y coordinate from finger movement
   * @param finalX the X coordinate of where the touch ended
   * @param finalY the Y coordinate of where the touch ended
   * @return value to change baby happiness by
   */
  @Override
  int handleTouch(
      View v,
      float initialX,
      float initialY,
      float movingX,
      float movingY,
      float finalX,
      float finalY) {
    if (swipesLeft > 0 && Math.abs(movingX - getX()) < 50) {
      if (initialY - movingY > 100 && !moveUp) {
        swipesLeft--;
        Log.d("VerticalShake", "Swiping Left, swipesLeft = " + swipesLeft);
        moveUp = true;
        img =
            Bitmap.createScaledBitmap(
                img, (int) (imgWidth() * 1.1), (int) (imgWidth() * 1.1), false);
        setImg(img);
        if (swipesLeft == 0) setInteraction(true);
        return 3;
      } else if (movingY - initialY > 100 && moveUp) {
        swipesLeft--;
        Log.d("VerticalShake", "Swiping Right, swipesLeft = " + swipesLeft);
        img =
            Bitmap.createScaledBitmap(
                img, (int) (imgWidth() * 1.1), (int) (imgWidth() * 1.1), false);
        setImg(img);
        moveUp = false;
        if (swipesLeft == 0) setInteraction(true);
        return 3;
      }
    }
    return 0;
  }
}
