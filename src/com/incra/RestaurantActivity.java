package com.incra;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class RestaurantActivity extends Activity {

  /** Called when the activity is first created. */
  private static final int ACTIVITY_CREATE = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_restaurant);

    final Button button1 = (Button) findViewById(R.id.restaurant_back);
    button1.setOnClickListener(new Button.OnClickListener() {

      public void onClick(View v) {
        back();
      }
    });
  }

  protected void back() {
    Intent i = new Intent(this, MainActivity.class);
    startActivityForResult(i, ACTIVITY_CREATE);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.restaurant, menu);
    return true;
  }

}
