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

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText email;
    private EditText password;
    private Button btnsignup;
    private TextView textsignin;
    private ProgressDialog progressDialog;
    private CheckBox checkBox;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        email =findViewById (R.id.emailid);
        password =findViewById (R.id.passid);
        btnsignup =findViewById (R.id.buttonid);
        textsignin =findViewById (R.id.textviewid);
        checkBox=findViewById (R.id.show_password);

        btnsignup.setOnClickListener (this);
        textsignin.setOnClickListener (this);

        //intialization of progress Dialog
        progressDialog = new ProgressDialog (this);

        //intialization for firebase object
        firebaseAuth=FirebaseAuth.getInstance ();   //will use this object to register


        checkBox.setOnCheckedChangeListener (new CompoundButton.OnCheckedChangeListener () {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    password.setTransformationMethod (HideReturnsTransformationMethod.getInstance ());
                }else{
                    password.setTransformationMethod (PasswordTransformationMethod.getInstance ());
                }
            }
        });

    }

    @Override
    public void onClick(View view) {

        if(view==btnsignup){
           //what will happen if the user clicked on btn register (sign up)
            signinuser();
        }
        if(view==textsignin){
              //what will happen if the user clicked on (already have an account)
            startActivity (new Intent (this,loginActivity.class));
            Toast.makeText (this,"log in page",Toast.LENGTH_LONG).show ();
        }

    }

    private void signinuser() {
        //get email and password from the user
        String emailuser = email.getText ().toString ().trim ();
        String passuster = password.getText ().toString ().trim ();

        Intent intent=new Intent (this,dr_or_student.class);
        intent.putExtra ("mail_intent",emailuser);
        startActivity (intent);

        //check if email is empty or not using method (( Test Utils))))
       /* if(TextUtils.isEmpty (emailuser)){
            Toast.makeText (this,"please enter your email",Toast.LENGTH_LONG).show ();
            return; //the return will stop the fun from execution
        }
        if(TextUtils.isEmpty (passuster)){
            Toast.makeText (this,"please enter your password",Toast.LENGTH_LONG).show ();
            return;
        }*/

        // to send mail throw intent


        if(TextUtils.isEmpty (emailuser)){
            email.setError("Email required");
            email.requestFocus();
            // Toast.makeText (this,"please enter your email",Toast.LENGTH_LONG).show ();
            return; //the return will stop the fun from execution
        }
        if(TextUtils.isEmpty (passuster)){
            password.setError("Password required");
            password.requestFocus();
            // Toast.makeText (this,"please enter your password",Toast.LENGTH_LONG).show ();
            return;
        }
        // if the email and password are not empty please wait until registration
         progressDialog.setMessage ("Signing up please wait....");
         progressDialog.show ();


         //creating a new user
        firebaseAuth.createUserWithEmailAndPassword (emailuser,passuster).addOnCompleteListener (this, new OnCompleteListener<AuthResult> () {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //if object task
                if(task.isSuccessful()){
                    //display some message here
                    Toast.makeText(MainActivity.this,"Successfully registered",Toast.LENGTH_LONG).show();

                    finish ();

                    Intent intent=new Intent (getApplicationContext (),dr_or_student.class);
                 //   intent.putExtra ("mail_intent",emailuser);
                    startActivity (intent);
                   //  startActivity (new Intent (getApplicationContext (),dr_or_student.class));
                }else{
                    //display some message here
                    Toast.makeText(MainActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
                }
            });
    }


}
