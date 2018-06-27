package com.disruption.gomental;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/*All the logic concerning the informative activity goes here*/
public class InformativeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informative);
    }

    //Declare and implement an intent to go to the SurveyActivity
    //should the user press the Begin Survey button.
    public void beginSurvey(View view) {
        Intent intent = new Intent(this, SurveyActivity.class);
        intent.resolveActivity(getPackageManager());
        startActivity(intent);
    }

    //Should the user click this view, they should be taken to the UN website for more information.
    public void learnMoreAboutMentalHealth(View view) {
        Intent learnMoreIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.un.org/development/desa/disabilities/issues/mental-health-and-development.html"));
        //Make sure that the device has an app that can perform this action. If not, pop a toast
        //to tell the user to get a mobile browser
        if (learnMoreIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(learnMoreIntent);
        } else {
            Toast.makeText(this, R.string.please_install_web_browser, Toast.LENGTH_SHORT).show();
        }
    }
}
