package com.example.lloyd.Ukite;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mainTextView;
    private Button mainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainButton = (Button) findViewById(R.id.mainButton);
        mainTextView = (TextView) findViewById(R.id.plaatsnamen);
        mainButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openChildActivity();
            }
        });

        String[] kiteSpots = {
                "Oesterdam",
                "Landgraaf",
                "Brouwersdam",
                "Oostende kust",
                "Nieuwpoort surfclub",
                "Zeebrugge kust",
                "Schelde",
                "Knokke kust",
                "Oostduinkerke kust",
                "Krabbendijk",
                "Surfers Paradise"
        };
/
        for (String spot : kiteSpots) {

            mainTextView.append(spot + "\n\n");
        }
    }

    public void openChildActivity(){
        Intent intent = new Intent(this, ChildActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemThatWasSelected = item.getItemId();
        if (menuItemThatWasSelected == R.id.action_search) {
            Context context = MainActivity.this;
            String message = "Search was clicked";
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}