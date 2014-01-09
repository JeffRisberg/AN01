package com.incra;

import java.text.NumberFormat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

  public static final String tag = "AN01";

  /** Called when the activity is first created. */
  private static final int ACTIVITY_CREATE = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final Button button1 = (Button) findViewById(R.id.show_restaurant);
    button1.setOnClickListener(new Button.OnClickListener() {

      public void onClick(View v) {
        showRestaurant();
      }
    });

    final Button button2 = (Button) findViewById(R.id.calculate);
    final TextView mealPriceField = (TextView) findViewById(R.id.mealprice);
    final TextView answerField = (TextView) findViewById(R.id.answer);
    button2.setOnClickListener(new Button.OnClickListener() {

      public void onClick(View v) {
        try {
          // Perform action on click
          Log.i(MainActivity.tag, "onClick invoked.");

          // Grab the meal price from the UI
          String mealprice = mealPriceField.getText().toString();

          Log.i(MainActivity.tag, "mealprice is [" + mealprice + "]");
          String answer = "";

          // Check to see if the meal price includes a "$"
          if (mealprice.indexOf("$") == -1) {
            mealprice = "$" + mealprice;
          }

          float fmp = 0.0F;

          // Get currency formatter
          NumberFormat nf = java.text.NumberFormat.getCurrencyInstance();

          if (nf == null) {
            Log.i(MainActivity.tag, "punt - NumberFormat is null");
          }

          // Grab the input meal price
          fmp = nf.parse(mealprice).floatValue();

          // Let's give a nice tip -> 20%
          fmp *= 1.2;

          Log.i(MainActivity.tag, "Total Meal price is [" + fmp + "]");
          // Format our result
          answer = "Full Price, Including 20% Tip: " + nf.format(fmp);

          // Display the answer
          answerField.setText(answer);

          Log.i(MainActivity.tag, "onClick complete.");
        } catch (java.text.ParseException pe) {
          Log.i(MainActivity.tag, "Parse exception caught");
          answerField.setText("Failed to parse amount");
        } catch (Exception e) {
          Log.e(MainActivity.tag, "Failed to Calculate Tip:" + e.getMessage());
          e.printStackTrace();
          answerField.setText(e.getMessage());
        }
      }
    });
  }

  protected void showRestaurant() {
    Intent i = new Intent(this, RestaurantActivity.class);
    startActivityForResult(i, ACTIVITY_CREATE);
  }
}
