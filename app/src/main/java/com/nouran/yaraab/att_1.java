package com.nouran.yaraab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.app.DatePickerDialog;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import android.app.TimePickerDialog;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;


import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class att_1 extends AppCompatActivity implements View.OnClickListener {

    RadioGroup radioGroup;
    RadioButton radioButton;
    private TextView time_text;
    private EditText date_text;
    private Button ADD;
    private Button timee;

    DatabaseReference databaseReference;
 //   int year,month,dayOfMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_att_1);

        //Button datee = findViewById (R.id.date_btn);


        radioGroup = findViewById (R.id.radioGroup);


        time_text = findViewById (R.id.time_txt);
        date_text= findViewById (R.id.date_txt);

        ADD=findViewById (R.id.button_apply);
        ADD.setOnClickListener (this);

        timee = findViewById (R.id.time_btn);
        timee.setOnClickListener (this);

        databaseReference= FirebaseDatabase.getInstance ().getReference ("timing_of_session");

      /*datee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
       //         DialogFragment datePicker = new DatePickerFragment();
         //       datePicker.show(getSupportFragmentManager(), "date picker");
                showDatePickerDialog();

            }*/

         /*  private void showDatePickerDialog() {

               DatePickerDialog datePickerDialog = new DatePickerDialog(
                       getApplicationContext (), (DatePickerDialog.OnDateSetListener) getApplicationContext (),
                       Calendar.getInstance().get(Calendar.YEAR),
                       Calendar.getInstance().get(Calendar.MONTH),
                       Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
               datePickerDialog.show();

           }
       });*/


       timee.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                //  DialogFragment timePicker = new timePickerFragment ();
                //timePicker.show(getSupportFragmentManager(), "time picker");

                final Calendar c = Calendar.getInstance ();
                int hour = c.get (Calendar.HOUR_OF_DAY);
                int minute = c.get (Calendar.MINUTE);


                TimePickerDialog timePickerDialog = new TimePickerDialog (att_1.this, new TimePickerDialog.OnTimeSetListener () {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


                        ///////////////////////time elhyt3mlo upload
                        time_text.setText (hourOfDay + ":" + minute);
                    }
                }, hour, minute, false);
                timePickerDialog.show ();
            }
        });





      /*  Button buttonApply = findViewById (R.id.button_apply);
        buttonApply.setOnClickListener (new View.OnClickListener () {


            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId ();

                radioButton = findViewById (radioId);

                ////////hwa de elhyt3mlo upload n03 elmo7dra
                String kind_lec = radioButton.getText ().toString ();
            }
        });*/

    }

    public void checkButton(View view) {
        int radioId = radioGroup.getCheckedRadioButtonId ();

        radioButton = findViewById (radioId);

        Toast.makeText (this, "You Selected : " + radioButton.getText (), Toast.LENGTH_SHORT).show ();
    }



   /* @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = "month/day/year: " + month + "/" + dayOfMonth + "/" + year;
       date_text.setText(date);
    }*/

  /*   @Override
  public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        //textView(Date) da hwa elfeeh awel 7aga h3mlha upload 3la database
        TextView Date = findViewById(R.id.textView);
        Date.setText(currentDateString);
    }*/


  /*   @Override
   public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
      // hya (Time)de hyt3mlha upload
        TextView Time = findViewById(R.id.textView);
        Time.setText("Hour: " + hourOfDay + " Minute: " + minute);
    }*/





    @Override
    public void onClick(View view) {

        if(view==ADD){
            addData();

        }
    }





    private void addData() {

        String DATE = date_text.getText ().toString ().trim ();
        String TIME = time_text.getText ().toString ();

        int radioId = radioGroup.getCheckedRadioButtonId ();

        radioButton = findViewById (radioId);
        String LECTURE = radioButton.getText ().toString ();

        if(!TextUtils.isEmpty (DATE)){
            ////Store it to firebase
            //Create a new string with unique id for each session
            String ID =databaseReference.push ().getKey ();
            //create a new timing(session)through out an object from the object Timing
            Timing timing =new Timing (ID,DATE,TIME,LECTURE);
            //use the setvalue() method to store this student into firebase database-->we need to store each of the three parameters inside the unique id that already generated
            databaseReference.child (ID).setValue (timing);
            Toast.makeText (this,"Added succesfully",Toast.LENGTH_LONG).show ();


        }else{
            Toast.makeText (this,"You Should Enter Name",Toast.LENGTH_LONG).show ();
        }

    }
}

