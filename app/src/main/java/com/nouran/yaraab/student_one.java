package com.nouran.yaraab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class student_one extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference databaseReference;
    private EditText Name,ID;
    private Button upload_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_student_one);

        //intialization of the database refrence and 2 edit text and the button
       databaseReference = FirebaseDatabase.getInstance ().getReference ();
       Name=findViewById (R.id.name_id);
        ID=findViewById (R.id.id_id);
       upload_data=findViewById (R.id.upload_data_id);

       //attach onClick listner to btn save info(Upload data)
       upload_data.setOnClickListener (this);


    }

    @Override
    public void onClick(View view) {
        Toast.makeText (this,"student activity2",Toast.LENGTH_LONG).show ();

        finish ();
        startActivity (new Intent (this,student_two.class));

        //save_user_data();
    }

    //method to store data in firebase database
    private void save_user_data() {
    }

  // String Namee = Name.getText ().toString ().trim ();
   // String Idd =ID.getText ().toString ().trim ();

   // user_information user_information= new user_information (Namee,Idd);
  //  FirebaseUser user = Firebase.getC




}
