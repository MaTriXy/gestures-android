/*
 * Copyright 2016-present The Material Motion Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.material.motion.gestures.testing;

import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;

import com.google.android.material.motion.gestures.GestureRecognizer;

/**
 * A no-op gesture recognizer for testing that exposes {@link #setState(int)}.
 */
public class SimulatedGestureRecognizer extends GestureRecognizer {

  private float untransformedCentroidX;
  private float untransformedCentroidY;

  public SimulatedGestureRecognizer(View element) {
    setElement(element);
  }

  @Override
  public void setState(@GestureRecognizerState int state) {
    super.setState(state);
  }

  public void setCentroid(float x, float y) {
    PointF centroid =
      calculateUntransformedCentroid(MotionEvent.obtain(0, 0, MotionEvent.ACTION_MOVE, x, y, 0));
    untransformedCentroidX = centroid.x;
    untransformedCentroidY = centroid.y;

    setState(CHANGED);
  }

  @Override
  protected boolean onTouch(MotionEvent event) {
    return false;
  }

  @Override
  public float getUntransformedCentroidX() {
    return untransformedCentroidX;
  }

  @Override
  public float getUntransformedCentroidY() {
    return untransformedCentroidY;
  }
}
