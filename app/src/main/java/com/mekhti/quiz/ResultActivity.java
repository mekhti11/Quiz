package com.mekhti.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tv = findViewById(R.id.result);
        Bundle b = getIntent().getExtras();
        int count = b.getInt("true_answers");

        String out = "Count of Correct Answers : "+count;

        tv.setText(out);
    }
}
