package com.nouran.yaraab;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText emaill,pass;
    private Button log_in;
    private TextView sign_up;
    private ProgressDialog progressDialog;
    private CheckBox checkBox;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);
        emaill = findViewById (R.id.logemailid);
        pass = findViewById (R.id.logpassid);
        log_in = findViewById (R.id.logbuttonid);
        sign_up = findViewById (R.id.logtextviewid);
        checkBox=findViewById (R.id.show_password);

        progressDialog =new ProgressDialog (this);

        firebaseAuth=FirebaseAuth.getInstance ();

        if(firebaseAuth.getCurrentUser () !=null){

            //start profile activity
        }

        log_in.setOnClickListener (this);
        sign_up.setOnClickListener (this);

        checkBox.setOnCheckedChangeListener (new CompoundButton.OnCheckedChangeListener () {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    pass.setTransformationMethod (HideReturnsTransformationMethod.getInstance ());
                }else{
                    pass.setTransformationMethod (PasswordTransformationMethod.getInstance ());
                }
            }
        });
    }

    private void Userlogin() {
          //trim-->checks if the value exists then remove spaces and return the omitted string
        String email = emaill.getText ().toString ().trim ();
        String password= pass.getText ().toString ().trim ();

        if(TextUtils.isEmpty (email)){
           emaill.setError("Email required");
            emaill.requestFocus();
           // Toast.makeText (this,"please enter your email",Toast.LENGTH_LONG).show ();
            return; //the return will stop the fun from execution
        }
        if(TextUtils.isEmpty (password)){
            pass.setError("Password required");
            pass.requestFocus();
            // Toast.makeText (this,"please enter your password",Toast.LENGTH_LONG).show ();
            return;
        }
        progressDialog.setMessage ("Signing in please wait....");
        progressDialog.show ();

        firebaseAuth.signInWithEmailAndPassword (email,password).addOnCompleteListener (this, new OnCompleteListener<AuthResult> () {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss ();
                if(task.isSuccessful ()){
                    //start activity (app first page)
                    startActivity (new Intent (getApplicationContext (),profileActivity.class));

                }

            }
        });


    }



    @Override
    public void onClick(View view) {

        if (view == log_in) {
            Userlogin ();
        }
        if (view == sign_up) {
            finish ();
            startActivity (new Intent (this, MainActivity.class));
        }

    }


}

