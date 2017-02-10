package com.udacity.akki.capstone;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.udacity.akki.capstone.utility.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    private Context mContext;
    @BindView(R.id.edt_username) EditText edtUserName;
    @BindView(R.id.edt_password) EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mContext=LoginActivity.this;
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    @OnClick(R.id.btn_login)
    public void submit(View view) {

        String userName=edtUserName.getText().toString();
        String password=edtPassword.getText().toString();
        if(Util.isStringNullOrEmpty(userName) || Util.isStringNullOrEmpty(password) ){
            Util.showToast(mContext,"UserName/ Password cannot be empty !");
        }else{

        }


    }

}
