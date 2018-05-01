package com.example.lloyd.Ukite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.lloyd.Ukite.MainActivity.EXTRA_DIR;
import static com.example.lloyd.Ukite.MainActivity.EXTRA_LOC;
import static com.example.lloyd.Ukite.MainActivity.EXTRA_SPEED;
import static com.example.lloyd.Ukite.MainActivity.EXTRA_TEMP;
import static com.example.lloyd.Ukite.MainActivity.EXTRA_URL;
import static com.example.lloyd.Ukite.MainActivity.EXTRA_URL_SWELL;
import static com.example.lloyd.Ukite.MainActivity.EXTRA_URL_WIND;

public class ChildActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String imageUrlWind = intent.getStringExtra(EXTRA_URL_WIND);
        String imageUrlSwell = intent.getStringExtra(EXTRA_URL_SWELL);
        String location = intent.getStringExtra(EXTRA_LOC);
        int speed = intent.getIntExtra(EXTRA_SPEED, 0);
        int temp = intent.getIntExtra(EXTRA_TEMP, 0);
        String direction = intent.getStringExtra(EXTRA_DIR);

        ImageView imageWind = findViewById(R.id.image_wind_detail);
        ImageView imageSwell = findViewById(R.id.image_swell_detail);
        TextView textViewLocation = findViewById(R.id.text_view_location_detail);
        TextView textViewSpeed = findViewById(R.id.text_view_speed_detail);
        TextView textViewDirection = findViewById(R.id.text_view_direction_detail);
        TextView textViewTemp = findViewById(R.id.text_view_temp_detail);


        Picasso.with(this).load(imageUrlWind).fit().centerInside().into(imageWind);
        Picasso.with(this).load(imageUrlSwell).fit().centerInside().into(imageSwell);
        textViewLocation.setText(location);
        textViewSpeed.setText("Windspeed: " + speed);
        textViewDirection.setText("Winddirection: " + direction);
        textViewTemp.setText("Temperature: " + temp);
    }
}
