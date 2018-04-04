package com.mekhti.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mekhti.quiz.Entity.User;

public class ProfileActivity extends AppCompatActivity {

    private TextView name;
    private TextView username;
    private TextView email;
    private Button edit;
    private Button beginTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        
        setData();
    }

    private void setData() {

        Bundle b = getIntent().getExtras();
        User user = b.getParcelable("user");

        name = findViewById(R.id.user_name);
        username = findViewById(R.id.user_username);
        email = findViewById(R.id.user_email);
        edit = findViewById(R.id.edit_button);
        beginTest = findViewById(R.id.user_begin_test);

        name.setText(user.getName());
        username.setText(user.getUsername());
        email.setText(user.getEmail());
    }

    public void BeginTest(View view) {

        Intent i = new Intent(ProfileActivity.this , CategoryActivity.class);
        startActivity(i);
    }

    public void edit(View view) {
    }
}
