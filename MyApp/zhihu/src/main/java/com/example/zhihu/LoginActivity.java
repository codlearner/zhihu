package com.example.zhihu;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity
{
    private static final int RC_SIGN_IN = 100;

    //views
    EditText mEmailEt, mPasswordEt;
    TextView dontHaveAccountTv, mRecoverPassTv;
    Button mLoginBtn;

    //progress dialog
    ProgressDialog pd;
    private DBOpenHelper mDBOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)//onsaveInsanceState方法是用来保存Activity的状态的。当一个Activity在生命周期结束前，会调用该方法保存状态。
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DBOpenHelper mDBOpenHelper = new DBOpenHelper(this);
        android.database.sqlite.SQLiteDatabase writableDatabase=mDBOpenHelper.getWritableDatabase();
        //Actionbar and its title
        ActionBar actionBar = getSupportActionBar();//提供一个返回健
        actionBar.setTitle("登录");//最上面的那排字
        //enable back button
        actionBar.setDisplayHomeAsUpEnabled(true);//给左上角图标的左边加上一个返回的图标,对应ActionBar.DISPLAY_HOME_AS_UP
        actionBar.setDisplayShowHomeEnabled(true);//使左上角图标是否显示，如果设成false，则没有程序图标，仅仅就个标题，否则，显示应用程序图标，对应id为Android.R.id.home，对应ActionBar.DISPLAY_SHOW_HOME
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.custom_actionbar));

        //initialize
        mEmailEt = findViewById(R.id.emailEt);
        mPasswordEt = findViewById(R.id.passwordEt);
        dontHaveAccountTv = findViewById(R.id.donthave_accountTv);
        mLoginBtn = findViewById(R.id.loginBtn);

        //login button click
        mLoginBtn.setOnClickListener(new View.OnClickListener()
        {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view)
            {
                //input data
                String email = mEmailEt.getText().toString().trim();//自己加上了trim(),去除空格
                String password = mPasswordEt.getText().toString().trim();
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())//验证邮箱是否有效
                {
                    //invalid email, display error
                    mEmailEt.setError("Invalid Email");
                    mEmailEt.setFocusable(true);//将控件设置成可获取焦点状态，默认是无法获取焦点的，只有设置成true，才能获取控件的点击事件
                }
                else
                {
                    //valid email
                    loginUser(email, password);
                }
            }
        });
        //don't have an account textview click
        dontHaveAccountTv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));//跳转LoginActivity至RegisterActivity
                finish();
            }
        });

        //initialize progress dialog
        pd = new ProgressDialog(this);
    }

    private void loginUser(String email, String password)
    {
        //show progress dialog
        pd.setMessage("Logging In...");
        pd.show();

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password))
        {

        }
        else
        {
            Toast.makeText(LoginActivity.this, "请输入你的用户名和密码",Toast.LENGTH_SHORT).show();
        }
//        mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
//                {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task)
//                    {
//                        if (task.isSuccessful())
//                        {
//                            //dismiss progress dialog
//                            pd.dismiss();
//                            // Sign in success, update UI with the signed-in user's information
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            //User is logged in, start LoginActivity
//                            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
//                            finish();
//                        }
//                        else
//                        {
//                            //dismiss progress dialog
//                            pd.dismiss();
//                            // If sign in fails, display a message to the user.
//                            Toast.makeText(LoginActivity.this, "Authentication failed",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }).addOnFailureListener(new OnFailureListener()
//                {
//                    @Override
//                    public void onFailure(@NonNull Exception e)
//                    {
//                        //dismiss progress dialog
//                        pd.dismiss();
//                        //error, get and show error message
//                        Toast.makeText(LoginActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed(); //go to previous activity
        return super.onSupportNavigateUp();
    }

}
