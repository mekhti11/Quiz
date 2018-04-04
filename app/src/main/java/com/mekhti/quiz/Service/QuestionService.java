package com.mekhti.quiz.Service;

import android.content.Context;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mekhti.quiz.Entity.Question;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class QuestionService  {
    private DatabaseReference db;
    private List<Question> qList;
    private List<String> categories;
    String TAG = "mTag";

    public QuestionService() {

        db = FirebaseDatabase.getInstance().getReference();
        qList = new ArrayList<>();

        categories  = new ArrayList<>();

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> ds = dataSnapshot.getChildren();
                for(DataSnapshot data : ds){
                    categories.add(data.getKey());
                    Log.d(TAG, "onDataChange: "+data.getKey());

                }
                Log.d(TAG, "getCategories: "+categories);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public List<Question> getQuestions(final String category){

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String text,correct_answer;
                List<String> answers = new ArrayList<>();

                Iterable<DataSnapshot> ds = dataSnapshot.child(category).child("result").getChildren();
                Iterable<DataSnapshot> ds2;

                for (DataSnapshot data : ds){
                    text = data.child("question").getValue().toString();
                    correct_answer = data.child("correct_answer").getValue().toString();

                    ds2 = data.child("answers").getChildren();

                    for(DataSnapshot d : ds2){
                       answers.add(d.getValue().toString());
                    }

                    qList.add(new Question(text,answers,correct_answer));


                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return qList;
    }

    public List<String> getCategories() {

        return categories;
    }
}
