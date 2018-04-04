package com.mekhti.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.mekhti.quiz.Entity.User;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private TextView register;
    private Button login;

    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        login = findViewById(R.id.loginButton);

        setData();
    }

    private void setData() {
        userList = new ArrayList<>();
        userList.add(new User("Mekhti Gumbatov","mekhti","1234","mekhti11@gmail.com"));
    }

    public void register(View view) {

        Intent i = new Intent(LoginActivity.this , RegisterActivity.class);
        startActivityForResult(i,0);

    }


    public void login(View view) {

        String uname;
        String pword;

        uname = username.getText().toString();
        pword = password.getText().toString();

        for(User u : userList){

            if(uname.equals(u.getUsername())){
                if(!u.canLogin() ){
                    Toast.makeText(this,"3 or more attempt . U can't login",LENGTH_LONG).show();
                }
                else if (pword.equals(u.getPassword())){
                    Bundle b = new Bundle();
                    b.putParcelable("user",u);
                    Intent i = new Intent(LoginActivity.this , ProfileActivity.class);
                    i.putExtras(b);
                    startActivity(i);
                }else{
                    Toast.makeText(this,"Wrong Password . "+u.getAttempt()+" Attempt",LENGTH_LONG).show();

                    u.setAttempt(u.getAttempt()+1);
                }
            }
        }




    }
}
