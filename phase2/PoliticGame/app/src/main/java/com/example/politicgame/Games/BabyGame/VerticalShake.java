package com.example.politicgame.Games.BabyGame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;

import com.example.politicgame.R;

class VerticalShake extends Event {
  private int swipesLeft = 3;
  private boolean moveUp = true;

  /**
   * Creates this VerticalShake event.
   *
   * @param baby The Baby this event is acting upon
   * @param res the resources to draw the baby
   */
  VerticalShake(Baby baby, Resources res) {
    super(baby, res);
    setX((int) ((0.5 - Math.random()) * getBabyWidth() / 2) + getBabyX() + getBabyWidth() / 2);
    setY((int) (Math.random() * getBabyHeight() / 4) + getBabyY() + (getBabyHeight() / 2));
    Bitmap img = BitmapFactory.decodeResource(res, R.drawable.updownarrow);
    img =
        Bitmap.createScaledBitmap(
            img, (int) (getBabyWidth() / 1.8), (int) (getBabyWidth() / 1.8), false);
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
   * @param finalX not used
   * @param finalY not used
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
        setImg(
            Bitmap.createScaledBitmap(
                getImg(), (int) (imgWidth() * 1.1), (int) (imgHeight() * 1.1), false));
        if (swipesLeft == 0) setInteraction(true);
        return 5;
      } else if (movingY - initialY > 100 && moveUp) {
        swipesLeft--;
        Log.d("VerticalShake", "Swiping Right, swipesLeft = " + swipesLeft);
        setImg(
            Bitmap.createScaledBitmap(
                getImg(), (int) (imgWidth() * 1.1), (int) (imgHeight() * 1.1), false));
        moveUp = false;
        if (swipesLeft == 0) setInteraction(true);
        return 5;
      }
    }
    return 0;
  }
}
