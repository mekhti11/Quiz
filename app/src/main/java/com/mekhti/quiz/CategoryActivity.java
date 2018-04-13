package com.mekhti.quiz;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mekhti.quiz.Adapter.CategoryAdapter;
import com.mekhti.quiz.Listener.Listener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class CategoryActivity extends AppCompatActivity {

    private List<String> categories;
    private RecyclerView rv;
    private CategoryAdapter adapter;
    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        categories = new ArrayList<>();
        db  = FirebaseDatabase.getInstance().getReference();

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> ds = dataSnapshot.getChildren();
                for(DataSnapshot data : ds){
                    categories.add(data.getKey());
                    Log.d(TAG, "onDataChange: "+data.getKey());

                }
                Log.d(TAG, "getCategories: "+categories);

                adapter = new CategoryAdapter(categories, new Listener() {
                    @Override
                    public void onClick(View view, int position) {

                        Intent i = new Intent(CategoryActivity.this, QuestionsActivity.class);
                        Bundle b = new Bundle();
                        b.putString("category", categories.get(position));
                        i.putExtras(b);
                        startActivity(i);
                    }
                });
                Log.d(TAG, "onCreate: " + categories.size());
                rv = findViewById(R.id.recyclerView);



                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                rv.setLayoutManager(layoutManager);
                rv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }

    private void setData() {


    }


}
