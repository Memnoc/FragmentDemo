package com.smartdroidesign.fragmentdemo;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

//    1 - create variables for views.

    private ConstraintLayout fragmentContainer;
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        2 - Initialize the variables from point 1.
        fragmentContainer = (ConstraintLayout) findViewById(R.id.fragment_container);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.sendBtn);

//        3 - Set onClickListener for the button. The onClick method includes another method to open up the fragment.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//         5 - We need now to pass the String text here, and the string is equal to the content of the variable editText.
                String text = editText.getText().toString();
                openFragment(text);

            }
        });
    }

//    4 - Creating the method openFragment.
//        Since we want to pass text to our fragment, the method takes one parameter String text.
    public void openFragment(String text){

    }
}
