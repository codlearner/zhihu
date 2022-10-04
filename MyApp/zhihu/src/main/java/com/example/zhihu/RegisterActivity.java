package com.example.zhihu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity
{
    //views
    EditText mEmailEt, mPasswordEt;
    Button mRegisterBtn;
    TextView mHaveAccountTv;

    //progressbar to display while registering user
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Actionbar and its title
        ActionBar actionBar = getSupportActionBar();//提供一个返回键
        actionBar.setTitle("注册");//最上面那排字
        //enable back button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.custom_actionbar));

        //init
        mEmailEt = findViewById(R.id.emailEt);
        mPasswordEt = findViewById(R.id.passwordEt);
        mRegisterBtn = findViewById(R.id.registerBtn);
        mHaveAccountTv = findViewById(R.id.have_accountTv);

        progressDialog = new ProgressDialog(this);//等待时的显示
        progressDialog.setMessage("Registering User...");


        //set register button click
        mRegisterBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //input email, password
                String email = mEmailEt.getText().toString().trim();//将获取对象的值转为字符串类型并除去空格
                String password = mPasswordEt.getText().toString().trim();
                //validate
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())//验证邮箱是否有效
                {
                    //set error and focus to email edittext
                    mEmailEt.setError("Invalid Email");
                    mEmailEt.setFocusable(true);
                }
                else if(password.length()<8)
                {
                    //set error and focus to password edittext
                    mPasswordEt.setError("Password must contain at least 8 characters");
                    mPasswordEt.setFocusable(true);
                }
                else
                {
                    registerUser(email, password); //registers the user
                }
            }
        });
        //handle login textview click listener
        mHaveAccountTv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));//从RegisterActivity跳转至LoginActivity
                finish();
            }
        });
    }


    private void registerUser(String email, String password)
    {
        //email and password pattern is valid, show progress dialog and start registering user
        progressDialog.show();//显示

    }

    @Override
    public boolean onSupportNavigateUp()//不懂这是要干嘛的
    {
        onBackPressed(); //go to previous activity
        return super.onSupportNavigateUp();
    }
}