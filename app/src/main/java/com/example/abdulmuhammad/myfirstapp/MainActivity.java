package com.example.abdulmuhammad.myfirstapp;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private TextView myText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //create a variable for referencing the text view (this is where voice 2 text stuff will show)
        myText = (TextView) findViewById(R.id.voice2Text);

    }

    //clears the text on the text view
    public void clearTextView(View view){
        myText.setText("Click record for Voice2Text.");
    }

    //method to get speech input
    public void getSpeechInput(View view){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, Locale.getDefault());
        if(intent.resolveActivity(getPackageManager()) !=null){
            //start a new activity that is temporary, and broadcast a request code "10"
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "Your device is shit, it doesn't support speech. Get a new phone.", Toast.LENGTH_LONG).show();
        }
    }

    //an application lifecycle method that takes the result of an activity and does something with it
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){

            //do the follow chunk of code when android receives the broadcasted request code
            case 10:
                if (resultCode == RESULT_OK && data !=null){
                    // get the data from the intent and put it into an array list
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    // use the data from the array list and output it to the text view
                    myText.setText(result.get(0));
                }
                break;
        }

    }
}