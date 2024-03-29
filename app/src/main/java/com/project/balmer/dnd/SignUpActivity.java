package com.project.balmer.dnd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.materialtextfield.MaterialTextField;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.project.balmer.dnd.Model.CustomerInfo;

public class SignUpActivity extends AppCompatActivity {

    public static FirebaseAuth firebaseAuth;
    public static FirebaseAuth.AuthStateListener stateListener;
    EditText firstName, secondName, phoneNo, confirmPassword, emailTxt, emailTxt1,  passwordTxt, passwordTxt1;
    MaterialTextField firstName1, secondName1, phoneNo1, confirmPassword1, emailTxt11, emailTxt111,  passwordTxt11, passwordTxt111;
    TextView orSignUpTxt, orSignInTxt;
    Button signInBtn, signUpBtn;
    ProgressBar spinner;
    private SignUpViewModel signUpViewModel;
    private int a, b, c, d, e,f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        stateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    Log.e("User already logged in", firebaseAuth.getCurrentUser().getEmail());
                    Intent signUpIntent = new Intent(getBaseContext(), FoodActivity.class);
                    startActivity(signUpIntent);
                    finish();
                }
            }
        };

        //Otherwise just continue loading the Sign up activity
        setContentView(R.layout.activity_sign_up);
        signUpViewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);
        signUpViewModel.init(getParent());

        firstName1 = findViewById(R.id.materialFirstNameText);
        secondName1 = findViewById(R.id.materialSecondNameText);
        phoneNo1 = findViewById(R.id.materialPhoneText);
        emailTxt11 = findViewById(R.id.materialEmailText);
        emailTxt111 = findViewById(R.id.materialEmailText1);
        passwordTxt11 = findViewById(R.id.materialPasswordText);
        passwordTxt111 = findViewById(R.id.materialPasswordText1);
        confirmPassword1 = findViewById(R.id.materialConfirmPasswordText1);
        signInBtn = findViewById(R.id.signInBtn);
        signUpBtn = findViewById(R.id.signUpBtn);
        orSignInTxt = findViewById(R.id.orSignInTxt);
        orSignUpTxt = findViewById(R.id.orSignUpTxt);

        //Used to set up the views for the custom login UI
        firstName = findViewById(R.id.firstName);
        secondName = findViewById(R.id.secondName);
        phoneNo = findViewById(R.id.phoneNo);
        confirmPassword = findViewById(R.id.confirmPassword);
        emailTxt = findViewById(R.id.emailTxt);
        emailTxt1 = findViewById(R.id.emailTxt1);
        passwordTxt = findViewById(R.id.passwordTxt);
        passwordTxt1 = findViewById(R.id.passwordTxt1);
        spinner = findViewById(R.id.spinner);

        spinner.setVisibility(View.GONE);

        appearSignInViews();

        orSignUpTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appearingSignUpViews();
                disappearSignInViews();
            }
        });

        orSignInTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disappearSignUpViews();
                appearSignInViews();
            }
        });
        //Called to set up the Button onClickListeners and What events will occur.
        proeedToSignIn();
    }

    private void disappearSignInViews() {
        emailTxt.setText("");
        passwordTxt.setText("");
        emailTxt11.setVisibility(View.INVISIBLE);
        passwordTxt11.setVisibility(View.INVISIBLE);
        signInBtn.setVisibility(View.INVISIBLE);
        orSignUpTxt.setVisibility(View.INVISIBLE);

    }

    private void disappearSignUpViews() {



        firstName.setText("");
        secondName.setText("");
        emailTxt1.setText("");
        phoneNo.setText("");
        passwordTxt1.setText("");
        confirmPassword.setText("");

        firstName1.setVisibility(View.INVISIBLE);
        secondName1.setVisibility(View.INVISIBLE);
        emailTxt111.setVisibility(View.INVISIBLE);
        phoneNo1.setVisibility(View.INVISIBLE);
        passwordTxt111.setVisibility(View.INVISIBLE);
        confirmPassword1.setVisibility(View.INVISIBLE);
        signUpBtn.setVisibility(View.INVISIBLE);
        orSignInTxt.setVisibility(View.INVISIBLE);

    }

    private void appearingSignUpViews(){
        firstName1.setVisibility(View.VISIBLE);
        secondName1.setVisibility(View.VISIBLE);
        emailTxt111.setVisibility(View.VISIBLE);
        phoneNo1.setVisibility(View.VISIBLE);
        passwordTxt111.setVisibility(View.VISIBLE);
        confirmPassword1.setVisibility(View.VISIBLE);
        signUpBtn.setVisibility(View.VISIBLE);
        orSignInTxt.setVisibility(View.VISIBLE);


        firstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                a = charSequence.length();
                secondName.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        b = charSequence.length();
                        emailTxt1.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                c = charSequence.length();
                                phoneNo.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                                    }

                                    @Override
                                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                        d = charSequence.length();
                                        passwordTxt1.addTextChangedListener(new TextWatcher() {
                                            @Override
                                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                                            }

                                            @Override
                                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                                e = charSequence.length();
                                                confirmPassword.addTextChangedListener(new TextWatcher() {
                                                    @Override
                                                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                                                    }

                                                    @Override
                                                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                                        f = charSequence.length();

                                                        if (a > 0 && b > 0 && c > 0 && e > 0 && f > 0){
                                                            signInBtn.setBackgroundColor(Color.rgb(161,18,56));
                                                        }else {
                                                            signInBtn.setBackgroundColor(android.R.drawable.btn_default);
                                                        }
                                                    }

                                                    @Override
                                                    public void afterTextChanged(Editable editable) {

                                                    }
                                                });
                                            }

                                            @Override
                                            public void afterTextChanged(Editable editable) {

                                            }
                                        });
                                    }

                                    @Override
                                    public void afterTextChanged(Editable editable) {

                                    }
                                });
                            }

                            @Override
                            public void afterTextChanged(Editable editable) {

                            }
                        });
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void appearSignInViews() {

        emailTxt11.setVisibility(View.VISIBLE);
        passwordTxt11.setVisibility(View.VISIBLE);
        signInBtn.setVisibility(View.VISIBLE);
        orSignUpTxt.setVisibility(View.VISIBLE);

        emailTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() < 0){
                    signInBtn.setBackgroundColor(android.R.drawable.btn_default);
                }else if (charSequence.length() > 0) {
                    passwordTxt.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            if (charSequence.length() > 0) {
                                signInBtn.setBackgroundColor(Color.rgb(161, 18, 56));
                            }else {
                                signInBtn.setBackgroundColor(android.R.drawable.btn_default);
                            }

                        }

                        @Override
                        public void afterTextChanged(Editable editable) {

                        }
                    });
                }else {
                    signInBtn.setBackgroundColor(android.R.drawable.btn_default);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void proeedToSignIn() {

        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();


        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailTxt.getText().toString().trim();
                String password = passwordTxt.getText().toString().trim();

                //Fields Validation
                if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)){
                    Toast.makeText(getBaseContext(), "Please fill in the fields", Toast.LENGTH_LONG).show();

                }else if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(getBaseContext(), "You have not filled all fields", Toast.LENGTH_LONG).show();
                }else if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                    if (activeNetworkInfo == null) {
                        Toast.makeText(getBaseContext(), "No Internet connection", Toast.LENGTH_SHORT).show();

                    } else {
                        spinner.setVisibility(View.VISIBLE);
                        firebaseAuth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {

                                            spinner.setVisibility(View.GONE);
                                            Log.e("SignIn success code", task.getResult().toString());
                                            Intent signInIntent = new Intent(getBaseContext(), FoodActivity.class);
                                            startActivity(signInIntent);


                                        } else {
                                            spinner.setVisibility(View.GONE);
                                            Log.e("SignIn failure code", task.getException().getMessage());
                                            String error = task.getException().getMessage();
                                            Toast.makeText(getBaseContext(), error, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                }
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fName = firstName.getText().toString().trim() + " ";
                String secName = secondName.getText().toString().trim();
                String phone = phoneNo.getText().toString().trim();
                String email = emailTxt1.getText().toString().trim();
                String passw = passwordTxt1.getText().toString().trim();
                String confPassword = confirmPassword.getText().toString().trim();


                //Validate first
                if (TextUtils.isEmpty(fName) && (TextUtils.isEmpty(secName) && (TextUtils.isEmpty(phone) &&
                        (TextUtils.isEmpty(email) && (TextUtils.isEmpty(passw) && (TextUtils.isEmpty(confPassword))))))){
                    Toast.makeText(getBaseContext(), "Please fill in the fields", Toast.LENGTH_SHORT).show();

                }else if (TextUtils.isEmpty(fName) || (TextUtils.isEmpty(secName) || (TextUtils.isEmpty(phone) ||
                        (TextUtils.isEmpty(email) || (TextUtils.isEmpty(passw) || (TextUtils.isEmpty(confPassword))))))) {
                    Toast.makeText(getBaseContext(), "Some fields have been left blank", Toast.LENGTH_SHORT).show();
                }
                else {
                    signUpBtn.setBackgroundColor(Color.rgb(161,18,56));
                    if (activeNetworkInfo == null) {
                        Toast.makeText(getBaseContext(), "No Internet connection", Toast.LENGTH_SHORT).show();
                    } else {
                        spinner.setVisibility(View.VISIBLE);
                        firebaseAuth.createUserWithEmailAndPassword(email, passw)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Log.e("Signup success code", task.getResult().toString());
                                            CustomerInfo customerInfo = new CustomerInfo(firebaseAuth.getUid(), fName.concat(secName), email, phone, null);
                                            signUpViewModel.createNewUserOnDb(customerInfo, new SignUpViewModel.FinishedTask() {
                                                @Override
                                                public void onSuccess(boolean done) {
                                                    spinner.setVisibility(View.GONE);
                                                    Intent signInIntent = new Intent(getBaseContext(), FoodActivity.class);
                                                    startActivity(signInIntent);
                                                }
                                            });
                                        } else {
                                            spinner.setVisibility(View.GONE);
                                            Log.e("Signup failure code", task.getException().getMessage());
                                            String error = task.getException().getMessage();
                                            Toast.makeText(getBaseContext(), error, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                }
            }
        });
    }
}

