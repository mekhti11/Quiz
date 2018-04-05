package com.mekhti.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mekhti.quiz.Adapter.QuestionsAdapter;
import com.mekhti.quiz.Entity.Question;
import com.mekhti.quiz.Listener.Listener;
import com.mekhti.quiz.Service.QuestionService;

import java.util.ArrayList;
import java.util.List;

public class QuestionsActivity extends AppCompatActivity {

    private QuestionsAdapter adapter;
    private List<Question> questionList;
    private RecyclerView rv;
    private int[] results;
    private int j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        Bundle b = getIntent().getExtras();
        String category = b.getString("category");
        //questionList = new QuestionService().getQuestions(category);
        setData();
        results = new int[questionList.size()];

        rv = findViewById(R.id.questions_rv);

        adapter = new QuestionsAdapter(questionList, new Listener() {
            @Override
            public void onClick(View view, int position) {
                Intent i = new Intent(QuestionsActivity.this , QuestionWChoices.class);
                Bundle b = new Bundle();
                b.putParcelable("question",questionList.get(position));
                j = position;
                i.putExtras(b);
                startActivityForResult(i,1);
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }

    private void setData() {
        questionList = new ArrayList<>();
        List<String> answers = new ArrayList<>();
        //{"Apollo","Zeus","Poseidon","Hades"};
        answers.add("Apollo");
        answers.add("Zeus");
        answers.add("Poseidon");
        answers.add("Hades");


        Question q = new Question("Who was the King of Gods in Ancient Greek mythology?",answers,"Zeus");

        questionList.add(q);
        questionList.add(q);
        questionList.add(q);
        questionList.add(q);
        questionList.add(q);
        questionList.add(q);
        questionList.add(q);
        questionList.add(q);
        questionList.add(q);
        questionList.add(q);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bundle b = data.getExtras();
        results[j] = b.getInt("result");
    }

    public void showResult(View view) {
        int true_answers = 0;
        for(int a : results)
            if (a == 1)
                true_answers++;

        Intent i = new Intent(QuestionsActivity.this , ResultActivity.class);
        Bundle b = new Bundle();
        b.putInt("true_answers",true_answers);
        i.putExtras(b);
        startActivity(i);
    }
}
