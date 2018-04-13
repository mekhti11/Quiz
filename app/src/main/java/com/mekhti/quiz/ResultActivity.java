package com.mekhti.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {


    private TextView true_answers;
    private TextView false_answers;
    private TextView not_ansered;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        true_answers = findViewById(R.id.true_answers);
        false_answers= findViewById(R.id.false_answers);
        not_ansered= findViewById(R.id.not_answered);

        Bundle b = getIntent().getExtras();

        int t_count = b.getInt("true_answers");
        int f_count = b.getInt("false_answers");
        int not_count = b.getInt("not_answered");



        true_answers.setText("Count of Correct Answers : "+t_count);
        false_answers.setText("Count of Wrong Answers : "+f_count);
        not_ansered.setText("Count of Not Answered : "+not_count);
    }
}
