package io.github.jokedisplay.aj326;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayJoke extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_joke);
        ((TextView)findViewById(R.id.jokeText)).setText(getIntent().getStringExtra("joke"));
    }
}
