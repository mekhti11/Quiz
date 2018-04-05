package com.mekhti.quiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mekhti.quiz.Entity.Question;

import java.util.List;

public class QuestionWChoices extends AppCompatActivity {

    private RadioGroup rg;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private TextView tv;
    private int result;
    private Question q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_wchoices);

        rg = findViewById(R.id.rg);
        rb1 = findViewById(R.id.radioButton1);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        rb4 = findViewById(R.id.radioButton4);
        tv = findViewById(R.id.q_text);

        setData();

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = group.findViewById(checkedId);
                String answer = rb.getText().toString();

                if(answer.equals(q.getAnswer()))
                    result = 1;
                else
                    result = 0;

            }
        });

    }

    private void setData() {
        Bundle b = getIntent().getExtras();
        q = b.getParcelable("question");
        List<String> str = q.getChoices();
        tv.setText(q.getText());
        rb1.setText(str.get(0));
        rb2.setText(str.get(1));
        rb3.setText(str.get(2));
        rb4.setText(str.get(3));



    }

    public void done(View view) {
        Intent i = new Intent();
        Bundle b = new Bundle();
        b.putInt("result",result);
        i.putExtras(b);
        setResult(Activity.RESULT_OK , i);
        finish();

    }
}
