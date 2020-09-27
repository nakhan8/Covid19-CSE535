package com.example.Assign1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;


public class SymptomsRatingActivity extends AppCompatActivity {

    String TAG = "SymptomActivity";
    MainActivity mainActivity;

    Button btnUploadSymptoms;
    UserInfoDatabase userInfoDatabase;
    String respiratoryRate;
    String heartRate;
    String[] symptomsList = {"Nausea", "Headache", "Diarrhea", "Soar Throat", "Feeling tired", "Shortness of Breath", "Cough", "Loss of Smell or Taste", "Muscle Ache", "Fever"};
    String selectedSymptom = "";
    Spinner spinnerSymptoms;
    RatingBar ratings;
    Intent newIntent;
    int sympIndex = 0;

    int symptomRatings[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms_rating);
        Log.i(TAG, "on create function");

        userInfoDatabase = new UserInfoDatabase(getApplicationContext());

        newIntent = getIntent();
        if (newIntent.hasExtra("RESP_RATE_VALUE_MAIN")) {
            heartRate = newIntent.getStringExtra("RESP_RATE_VALUE_MAIN");
        }
        if (newIntent.hasExtra("HEART_RATE_VALUE_MAIN")) {
            respiratoryRate = newIntent.getStringExtra("HEART_RATE_VALUE_MAIN");
        }


        btnUploadSymptoms = (Button) findViewById(R.id.btnUploadSymptoms);

        spinnerSymptoms = (Spinner) findViewById(R.id.spinnerSymptoms);
        ratings = (RatingBar) findViewById(R.id.ratingBar);
        ratings.setStepSize((float) 1.0);
        ratings.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 1) {
                    float ratingVals = ratings.getRating();
//                    Log.i(TAG, ": ratingVals andar ratings" + ratingVals);
                    for (int i = 0; i < symptomRatings.length; i++) {
//                        Log.i(TAG, "ratings ka for loop : " + symptomRatings[i]);
                    }
                    symptomRatings[sympIndex] = (int) ratingVals;
//                    Log.i(TAG, "bar ke andar");
                    Toast.makeText(SymptomsRatingActivity.this, " " + ratingVals, Toast.LENGTH_LONG).show();

                }
                return false;
            }
        });


        spinnerSymptoms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

//                Log.i(TAG, "spinner ke andar");
                ratings.setRating(0);
                selectedSymptom = symptomsList[i];
                sympIndex = i;

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> spin_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, symptomsList);
        spinnerSymptoms.setAdapter(spin_adapter);

        btnUploadSymptoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "btn function");
                Log.i(TAG, "onClickSymptomACtivity: " + respiratoryRate + "      " + heartRate);
                userInfoDatabase.setHeartRateValue(heartRate);
                userInfoDatabase.setRespiratoryRateValue(respiratoryRate);
                userInfoDatabase.insertData(symptomRatings);

            }
        });


    }
}