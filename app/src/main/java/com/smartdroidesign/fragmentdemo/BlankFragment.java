package com.smartdroidesign.fragmentdemo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlankFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {
//  6 - We delete one variable as we only need to pass one String of text
    private static final String TEXT = "text";
//  7 - This will receive and contain the text we are passing from MainActivity.
    private String mText;
    private OnFragmentInteractionListener mListener;
//  12 - Time to initialize the editText and Button we have created for the fragment.
    private EditText editTextFragment;
    private Button buttonFragment;

    public BlankFragment() {
        // Required empty public constructor
    }
//  8 -   In our newInstance method we only need 1 parameter
    public static BlankFragment newInstance(String text) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
//   9 - Same here, we pass our TEXT constant and the String text from above
//        When we call newInstance in MainActivity, we'll pass our text into String text, and then onto TEXT as an argument.
        args.putString(TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//   10 - On our onCreate() we can get the text out of the parameters into actual arguments
//   *** points 1 to 10 are the whole process of passing the text from MainActivity to BlankFragment ***
            mText = getArguments().getString(TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//      13 - Time to initialize the views in point 12. Put the inflater.inflate inside a view variable so you call findViewById on it and get our editText and Button.
        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        buttonFragment = view.findViewById(R.id.fragmentBtn);
        editTextFragment = view.findViewById(R.id.editText_fragment);

//      14 - When our fragment opens, we want to get the text passed onto mText (point 7-8-9) and add it to editTextFragment.
        editTextFragment.setText(mText);
        editTextFragment.requestFocus(); // 15 - This is to make sure the fragment is automatically focused at opening.

//      16 - Next, we need an onClickListener for the button. In this part we send back the content of editText and fragment to the MainActivity.
        buttonFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sendBackText = editTextFragment.getText().toString();
                sendBack(sendBackText); // 17 - In order to send back the text to the MainActivity, we use a method, but most importantly, we need an interface (point 18).

            }
        });



        return view;
    }

//    18 - We can use the dummy method provided by Android as our sendBack method. So change onButtonPressed to sendBack(); and the argument to String sendBackText.
    public void sendBack(String sendBackText) {
        if (mListener != null) { // 18.1 this is a check to see if our MainActivity, represented here by mListener is not null.
            mListener.onFragmentInteraction(sendBackText); // 18.2 If not null, this method is called, which is the INTERFACE (point 19).
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String sendBackText); // 19 - This is the interface called in point 18.
                                                        // Change the argument to String sendBackText, and do the same in point 18.2 on mListener.onFragmentInteraction(uri)
    }
}


/*The sum up is*/

/*Click the Button (16)
* SendBack gets called(17) and passes the content of editTextFragment
* sendBack method(18) calls onFragmentInteraction on our mListener, which our MainActivity, passes the sendBackText to it(18.2)
* This all goes back to our MainActivity which is going to implement the onFragmentInteraction method*/