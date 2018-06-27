package com.disruption.gomental;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/*This is the class responsible for handling all the logic related to the survey itself like calculating
 * the score of the user.*/
public class SurveyActivity extends AppCompatActivity {

    //Variable for tracking the survey score of the user. It is incremented if the correct
    //answers are selected and reset to 0 when the retake survey button is pressed
    int userSurveyScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        //Hide the retake survey button when this activity is created just to be sure
        //that the edges of the button is not visible when the user opens this activity
        Button retakeSurvey = findViewById(R.id.retake_survey);
        retakeSurvey.setVisibility(View.INVISIBLE);
    }

    /*On pressing the submit button, retrieve the country name from the input and print out a toast with a score.
     The toast changes depending on the score of the user*/
    public void submitSurvey(View view) {

        //Use an alert dialog to ensure that the user is really sure of submitting the survey for grading
        new AlertDialog.Builder(this)
                .setTitle(R.string.confirm_submission)
                .setMessage(R.string.do_you_want_to_submit)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        EditText nameOfCountry = findViewById(R.id.survey_enter_country_name);
                        String countryName = nameOfCountry.getText().toString();

                        CheckBox questionTwoCheckBox1 = findViewById(R.id.question2CheckBox1);
                        boolean hasCheckedBox1OfQuestion2 = questionTwoCheckBox1.isChecked();

                        CheckBox questionTwoCheckBox2 = findViewById(R.id.question2CheckBox2);
                        boolean hasCheckedBox2OfQuestion2 = questionTwoCheckBox2.isChecked();

                        CheckBox questionTwoCheckBox3 = findViewById(R.id.question2CheckBox3);
                        boolean hasCheckedBox3OfQuestion2 = questionTwoCheckBox3.isChecked();

                        //Get the final score from the helper method created
                        int finalUserSurveyScore = calculateUserSurveyScore();

                        if (finalUserSurveyScore == 5 && (hasCheckedBox1OfQuestion2 || hasCheckedBox2OfQuestion2)) {
                            Toast.makeText(getApplicationContext(), getString(R.string.user_score_perfect)
                                    + "\n" + getString(R.string.user_your_score) + finalUserSurveyScore + getString(R.string.test_total_score)
                                    + "\n" + getString(R.string.user_your_country) + countryName, Toast.LENGTH_LONG).show();
                        } else if (finalUserSurveyScore == 5 && hasCheckedBox3OfQuestion2) {
                            Toast.makeText(getApplicationContext(), getString(R.string.user_score_almost_perfect)
                                    + "\n" + getString(R.string.user_your_score) + finalUserSurveyScore + getString(R.string.test_total_score)
                                    + "\n" + getString(R.string.user_your_country) + countryName, Toast.LENGTH_LONG).show();
                        } else if (finalUserSurveyScore >= 3 && finalUserSurveyScore < 5) {
                            Toast.makeText(getApplicationContext(), getString(R.string.user_score_fair)
                                    + "\n" + getString(R.string.user_your_score) + finalUserSurveyScore + getString(R.string.test_total_score)
                                    + "\n" + getString(R.string.user_your_country) + countryName, Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), getString(R.string.user_score_poor)
                                    + "\n" + getString(R.string.user_your_score) + finalUserSurveyScore + getString(R.string.test_total_score)
                                    + "\n" + getString(R.string.user_your_country) + countryName, Toast.LENGTH_LONG).show();
                        }

                        //After doing all this, hide this button and show the button for retaking the survey
                        Button submitSurvey = findViewById(R.id.submit_survey);
                        submitSurvey.setVisibility(View.INVISIBLE);
                        Button retakeSurvey = findViewById(R.id.retake_survey);
                        retakeSurvey.setVisibility(View.VISIBLE);
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }

    /*Helper method for checking the correct answers (where applicable) in the answers given by the user
    Then set the correct background depending on the answer, green for correct and leave the color
    as it is if the answers are wrong. Return the user score*/
    private int calculateUserSurveyScore() {

        EditText mentalAwarenessMonth = findViewById(R.id.survey_awareness_month);
        String awarenessMonth = String.valueOf(mentalAwarenessMonth.getText());

        CheckBox questionFiveCheckBox1 = findViewById(R.id.question5CheckBox1);
        boolean hasCheckedBox1OfQuestion5 = questionFiveCheckBox1.isChecked();

        CheckBox questionFiveCheckBox2 = findViewById(R.id.question5CheckBox2);
        boolean hasCheckedBox2OfQuestion5 = questionFiveCheckBox2.isChecked();

        CheckBox questionFiveCheckBox3 = findViewById(R.id.question5CheckBox3);
        boolean hasCheckedBox3OfQuestion5 = questionFiveCheckBox3.isChecked();

        RadioButton yesQuestion3 = findViewById(R.id.yes_question3);
        boolean hasCheckedYesQuestion3 = yesQuestion3.isChecked();

        RadioButton noQuestion4 = findViewById(R.id.no_question4);
        boolean hasCheckedNoQuestion4 = noQuestion4.isChecked();

        RadioButton noQuestion6 = findViewById(R.id.no_question6);
        boolean hasCheckedNoQuestion6 = noQuestion6.isChecked();

        if (awarenessMonth.equalsIgnoreCase(getString(R.string.awareness_month))) {
            userSurveyScore++;
            mentalAwarenessMonth.setBackgroundColor(Color.GREEN);
        }
        if (hasCheckedYesQuestion3) {
            userSurveyScore++;
            yesQuestion3.setBackgroundColor(Color.GREEN);
        }
        if (hasCheckedNoQuestion4) {
            userSurveyScore++;
            noQuestion4.setBackgroundColor(Color.GREEN);
        }
        if (hasCheckedBox2OfQuestion5 && !hasCheckedBox1OfQuestion5 && hasCheckedBox3OfQuestion5) {
            userSurveyScore++;
            questionFiveCheckBox2.setBackgroundColor(Color.GREEN);
            questionFiveCheckBox3.setBackgroundColor(Color.GREEN);
        }
        if (hasCheckedNoQuestion6) {
            userSurveyScore++;
            noQuestion6.setBackgroundColor(Color.GREEN);
        }
        return userSurveyScore;
    }

    /*This method is for enabling the user to take the survey again after pressing the retake survey button. It resets all the values
        to their initial states*/
    public void retakeSurvey(View view) {

        //Get all the views and reset them to how they were initially
        RadioGroup radioGroupQuestion3 = findViewById(R.id.radio_group_question3);
        radioGroupQuestion3.clearCheck();
        radioGroupQuestion3.findViewById(R.id.yes_question3).setBackgroundColor(Color.TRANSPARENT);

        RadioGroup radioGroupQuestion4 = findViewById(R.id.radio_group_question4);
        radioGroupQuestion4.clearCheck();
        radioGroupQuestion4.findViewById(R.id.no_question4).setBackgroundColor(Color.TRANSPARENT);

        RadioGroup radioGroupQuestion6 = findViewById(R.id.radio_group_question6);
        radioGroupQuestion6.clearCheck();
        radioGroupQuestion6.findViewById(R.id.no_question6).setBackgroundColor(Color.TRANSPARENT);

        CheckBox questionFiveCheckBox1 = findViewById(R.id.question5CheckBox1);
        questionFiveCheckBox1.setChecked(false);

        CheckBox questionFiveCheckBox2 = findViewById(R.id.question5CheckBox2);
        questionFiveCheckBox2.setChecked(false);
        questionFiveCheckBox2.setBackgroundColor(Color.TRANSPARENT);

        CheckBox questionFiveCheckBox3 = findViewById(R.id.question5CheckBox3);
        questionFiveCheckBox3.setChecked(false);
        questionFiveCheckBox3.setBackgroundColor(Color.TRANSPARENT);

        EditText mentalAwarenessMonth = findViewById(R.id.survey_awareness_month);
        mentalAwarenessMonth.setText("");
        mentalAwarenessMonth.setBackgroundColor(Color.TRANSPARENT);

        EditText nameOfCountry = findViewById(R.id.survey_enter_country_name);
        nameOfCountry.setText("");

        CheckBox questionTwoCheckBox1 = findViewById(R.id.question2CheckBox1);
        questionTwoCheckBox1.setChecked(false);

        CheckBox questionTwoCheckBox2 = findViewById(R.id.question2CheckBox2);
        questionTwoCheckBox2.setChecked(false);

        CheckBox questionTwoCheckBox3 = findViewById(R.id.question2CheckBox3);
        questionTwoCheckBox3.setChecked(false);

        userSurveyScore = 0;

        //Then set hide this button and show the submit button
        Button retakeSurvey = findViewById(R.id.retake_survey);
        retakeSurvey.setVisibility(View.INVISIBLE);
        Button submitSurvey = findViewById(R.id.submit_survey);
        submitSurvey.setVisibility(View.VISIBLE);
    }
}
