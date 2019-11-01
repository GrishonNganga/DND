package com.project.balmer.dnd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.project.balmer.dnd.FoodActivity;
import com.project.balmer.dnd.R;

public class SignUpActivity extends AppCompatActivity {

    EditText phoneNo;
    Button signIn, signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        phoneNo = findViewById(R.id.phoneNoTxt);
        signIn = findViewById(R.id.signInBtn);
        signUp = findViewById(R.id.signUpBtn);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = new Intent(getBaseContext(), FoodActivity.class);
                startActivity(signInIntent);
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUpIntent = new Intent(getBaseContext(), FoodActivity.class);
                startActivity(signUpIntent);
            }
        });
    }
}
