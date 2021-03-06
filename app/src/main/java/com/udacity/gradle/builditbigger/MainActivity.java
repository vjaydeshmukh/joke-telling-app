package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.julia.android.jokesviewer.JokesViewerActivity;

import static com.julia.android.jokesviewer.JokesViewerActivity.EXTRA_JOKE;


public class MainActivity extends AppCompatActivity {

    private ProgressBar mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpinner = (ProgressBar) findViewById(R.id.progressBar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {

        mSpinner.setVisibility(View.VISIBLE);

        new FetchJokeTask(new FetchJokeTask.Listener() {

            @Override
            public void onJokeLoaded(String joke) {
                // Pass the joke from the Java Library to the Android Library
                Intent sendIntent = new Intent(getApplicationContext(), JokesViewerActivity.class);
                sendIntent.putExtra(EXTRA_JOKE, joke);
                startActivity(sendIntent);
                mSpinner.setVisibility(View.INVISIBLE);
            }

        }).execute();

    }

}
