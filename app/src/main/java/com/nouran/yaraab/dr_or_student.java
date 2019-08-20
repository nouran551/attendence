package com.nouran.yaraab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class dr_or_student extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    //int selected_item ;

    private TextView email;
    //private Button Ok;

    //private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_dr_or_student);

        spinner=findViewById (R.id.spinner_id);

      //  Ok=findViewById (R.id.buttonid);

        //to connect spinner to array
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource (this,R.array.spinner_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter (adapter);

        spinner.setOnItemSelectedListener (this);

       // firebaseAuth=FirebaseAuth.getInstance ();

       // FirebaseUser user = FirebaseAuth.C


        email=findViewById (R.id.uesr_mail_id);
        Intent intent =getIntent ();
        String user_welcom_email =intent.getStringExtra ("mail_intent");
        email.setText (user_welcom_email);




    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        String chosenValue = spinner.getSelectedItem().toString();

        if(chosenValue.equals("Student")){
            Intent intent = new Intent(this, student_one.class);
            startActivity(intent);
        }
            else if (chosenValue.equals("Dr.")){
                Intent intent = new Intent(this, profileActivity.class);
                startActivity(intent);
            }


        }




    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
