package com.mekhti.quiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mekhti.quiz.Entity.User;

public class EditActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private EditText name;
    private EditText username;
    private Button save;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        setData();
    }

    private void setData() {

        Bundle b = getIntent().getExtras();
        user = b.getParcelable("user");

        email = findViewById(R.id.edit_email);
        name = findViewById(R.id.edit_name);
        password = findViewById(R.id.edit_password);
        username = findViewById(R.id.edit_username);
        save = findViewById(R.id.edit_save);

        email.setText(user.getEmail());
        name.setText(user.getName());
        password.setText(user.getPassword());
        username.setText(user.getUsername());

    }

    public void save(View view) {

        String s_name , s_username , s_password , s_email;

        s_email = email.getText().toString();
        s_name  = name.getText().toString();
        s_password = password.getText().toString();
        s_username = username.getText().toString();

        User u = new User(s_name,s_username,s_password,s_email);
        Bundle  bundle = new Bundle();
        bundle.putParcelable("edited_user",u);
        Intent i = new Intent();
        i.putExtras(bundle);
        setResult(Activity.RESULT_OK , i);
        finish();

    }
}
