package com.nouran.yaraab;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;

import java.util.List;


public class student_two extends AppCompatActivity implements View.OnClickListener {


    private Button start_scanning,Log_out;
    private final int Barcode_Recognition_req_code =200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_student_two);

    start_scanning =findViewById (R.id.start_scanning_id);
    Log_out=findViewById (R.id.Log_out_Id);

    start_scanning.setOnClickListener (this);
    Log_out.setOnClickListener (this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult (requestCode, resultCode, data);

        if(requestCode==Barcode_Recognition_req_code){
            if(resultCode==RESULT_OK){

                Bitmap bitmap=(Bitmap)data.getExtras ().get("Data");
                barcode_Recognition(bitmap);

            }
        }
    }

    private void barcode_Recognition(Bitmap bitmap) {

        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);
        FirebaseVisionBarcodeDetector detector = FirebaseVision.getInstance()
                .getVisionBarcodeDetector();
        Task<List<FirebaseVisionBarcode>> result = detector.detectInImage(image)
                .addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionBarcode>> () {
                    @Override
                    public void onSuccess(List<FirebaseVisionBarcode> barcodes) {
                        // Task completed successfully
                        // ...
                        for (FirebaseVisionBarcode barcode: barcodes) {
                            Rect bounds = barcode.getBoundingBox();
                            Point[] corners = barcode.getCornerPoints();

                            String rawValue = barcode.getRawValue();

                            int valueType = barcode.getValueType();
                            Toast.makeText (getApplicationContext (),rawValue,Toast.LENGTH_LONG).show ();


                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener () {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                       Toast.makeText (getApplicationContext ()," ",Toast.LENGTH_LONG).show ();
                        // Task failed with an exception
                        // ...
                    }
                });


    }


    @Override
    public void onClick(View view) {
        if(view==start_scanning){
            //start scanning
            Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult (intent,Barcode_Recognition_req_code);




        }if(view==Log_out){
            Toast.makeText (this,"you are logged out",Toast.LENGTH_LONG).show ();
            finish ();
            startActivity (new Intent (this,loginActivity.class));
        }
    }





}
