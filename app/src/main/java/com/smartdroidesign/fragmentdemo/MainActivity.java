package com.smartdroidesign.fragmentdemo;

import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

// 20 - Extending the OnFragmentInteractionListener method from point 19
public class MainActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener {

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
//    11 - Time to open the fragment now using this method
    public void openFragment(String text){
        BlankFragment fragment = BlankFragment.newInstance(text); // 11.1 Creating fragment instance. The "text" within this newInstance object gets passed to BlankFragment.
        FragmentManager fragmentManager = getSupportFragmentManager(); // 11.2 Invoking FragmentManager
        FragmentTransaction transaction = fragmentManager.beginTransaction(); // 11.3 Beginning the transaction on the fragmentManager
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right); // 11.4 This line adds the animation to the fragment and to the button(this is why is repeated twice).
        transaction.addToBackStack(null); // 11.4 This method specifies the when you click the back button, only the fragment gets closed, NOT the whole activity.
        transaction.add(R.id.fragment_container, fragment, "BLANK_FRAGMENT").commit(); // 11.5 Here we add the fragment_container(ActivityMain.xml), the fragment object we have initialized, and we add a TAG to the fragment so it can be found later on.

    }


// 21 - ctrl+o and we can override the onFragmentInteraction method.
// Our sendBackText is provided, and we cna manipulate it as we please.
// 21.1 - We take our editText and we set it to sendBackText
// 21.2 - We also call onBackPressed so that our fragment gets automatically closed
    @Override
    public void onFragmentInteraction(String sendBackText) {
        editText.setText(sendBackText);
        onBackPressed();

    }
}
