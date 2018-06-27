package com.disruption.gomental;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Declare and implement an intent to go to the InformativeActivity when the user press
    //the Begin Informative button
    public void beginInformative(View view) {
        Intent intent = new Intent(this, InformativeActivity.class);
        intent.resolveActivity(getPackageManager());
        startActivity(intent);
    }

    /*TODO: Comment out the xml files*/
    //Declare and implement an intent to go to the SurveyActivity when the user presses
    //the Begin Survey button
    public void beginSurvey(View view) {
        Intent intent = new Intent(this, SurveyActivity.class);
        intent.resolveActivity(getPackageManager());
        startActivity(intent);


    }
}
