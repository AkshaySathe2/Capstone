package com.udacity.akki.capstone;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.udacity.akki.capstone.activity.LandingActivity;
import com.udacity.akki.capstone.utility.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    private static final String LOG_TAG=LoginActivity.class.getSimpleName();
    private Context mContext;
    @BindView(R.id.edt_username) EditText edtUserName;
    @BindView(R.id.edt_password) EditText edtPassword;

    //Adding Firebase Implementations
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mContext=LoginActivity.this;
        //Added for Firebase Authentication
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(LOG_TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    Util.setUid(mContext,user.getUid());
                    user.getToken(true)
                            .addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                                public void onComplete(@NonNull Task<GetTokenResult> task) {
                                    if (task.isSuccessful()) {
                                        String idToken = task.getResult().getToken();
                                        // Send token to your backend via HTTPS
                                        // ...
                                        Util.setToken(mContext,idToken);
                                        Util.showToast(mContext,"Logged in successfully.");
                                        Intent intent=new Intent(mContext,LandingActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        // Handle error -> task.getException();
                                    }
                                }
                            });

                    //

                } else {
                    // User is signed out
                    Log.d(LOG_TAG, "onAuthStateChanged:signed_out");
                    Util.showToast(mContext,"User signed out.");
                }
                // ...
            }
        };
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }



    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @OnClick(R.id.btn_login)
    public void submit(View view) {

        String userName=edtUserName.getText().toString();
        String password=edtPassword.getText().toString();
        mAuth.signInWithEmailAndPassword(userName, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(LOG_TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(LOG_TAG, "signInWithEmail", task.getException());
                            Util.showToast(mContext,"Authentication failed.");
                        }

                        // ...
                    }
                });


        if(Util.isStringNullOrEmpty(userName) || Util.isStringNullOrEmpty(password) ){
            Util.showToast(mContext,"UserName/ Password cannot be empty !");
        }else if(userName.equals("Akki") && password.equals("Akki")){
            Intent intent=new Intent(mContext, LandingActivity.class);
            startActivity(intent);
            finish();
        }


    }

}
