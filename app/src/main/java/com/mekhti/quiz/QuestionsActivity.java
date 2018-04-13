package com.mekhti.quiz;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mekhti.quiz.Adapter.QuestionsAdapter;
import com.mekhti.quiz.Entity.Question;
import com.mekhti.quiz.Listener.Listener;

import java.util.ArrayList;
import java.util.List;

public class QuestionsActivity extends AppCompatActivity {

    private static final String TAG = "TAG" ;
    private QuestionsAdapter adapter;
    private List<Question> questionList;
    private RecyclerView rv;
    private DatabaseReference db;
    private int[] results;
    private int j;
    private View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);



        Bundle b = getIntent().getExtras();
        final String category = b.getString("category");
        db = FirebaseDatabase.getInstance().getReference();
        questionList = new ArrayList<>();

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String text,correct_answer;
                List<String> answers ;

                Iterable<DataSnapshot> ds = dataSnapshot.child(category).child("results").getChildren();
                Iterable<DataSnapshot> ds2;

                for (DataSnapshot data : ds){
                    answers = new ArrayList<>() ;
                    text = data.child("question").getValue().toString();
                    correct_answer = data.child("correct_answer").getValue().toString();

                    ds2 = data.child("answers").getChildren();

                    for(DataSnapshot d : ds2){
                        answers.add(d.getValue().toString());
                    }
                    Log.d(TAG, "onDataChange: text : " +text);
                    Log.d(TAG, "onDataChange: answers : " +answers);
                    Log.d(TAG, "onDataChange: correct : " +correct_answer);
                    questionList.add(new Question(text,answers,correct_answer));


                }

                Log.d(TAG, "onDataChange: "+questionList);

                adapter = new QuestionsAdapter(questionList, new Listener() {
                    @Override
                    public void onClick(View view, int position) {
                        v = view;
                        Intent i = new Intent(QuestionsActivity.this , QuestionWChoices.class);
                        Bundle b = new Bundle();
                        b.putParcelable("question",questionList.get(position));
                        j = position;
                        i.putExtras(b);
                        startActivityForResult(i,1);
                    }
                });

                rv = findViewById(R.id.questions_rv);
                results = new int[questionList.size()];

                for(int k=0 ; k<results.length ; k++)
                    results[k] = -1;

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                rv.setLayoutManager(layoutManager);
                rv.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == Activity.RESULT_OK) {

            CardView cv = v.findViewById(R.id.questions_cv);
            Bundle b = data.getExtras();
            results[j] = b.getInt("result");

            if (results[j] == 1)
                v.setBackgroundColor(Color.GREEN);
            else if (results[j] == 0)
                v.setBackgroundColor(Color.RED);

            cv.setClickable(false);
        }

    }

    public void showResult(View view) {
        int true_answers = 0;
        int false_answers = 0;
        int not_answered;
        for(int a : results) {
            if (a == 1)
                true_answers++;
            else if(a == 0)
                false_answers++;
        }

        not_answered = results.length - true_answers - false_answers;

        Intent i = new Intent(QuestionsActivity.this , ResultActivity.class);
        Bundle b = new Bundle();

        b.putInt("true_answers",true_answers);
        b.putInt("false_answers",false_answers);
        b.putInt("not_answered",not_answered);

        i.putExtras(b);
        startActivity(i);
    }
}
