package com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Activity.MainActivity;
import com.accenture.rishikeshpoorun.moFaim.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Register extends Fragment implements View.OnClickListener {
    private Button buttonToLogin, buttonActionRegister;
    private EditText mUsername, mEmail, mPassword, mConfirmPassword;
    private TextView mStatus;

    public Register() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        buttonToLogin = view.findViewById(R.id.button_to_login);
        buttonActionRegister = view.findViewById(R.id.button_action_register);

        mUsername = view.findViewById(R.id.editText_username);
        mEmail = view.findViewById(R.id.editText_email);
        mPassword = view.findViewById(R.id.editText_password);
        mConfirmPassword = view.findViewById(R.id.editText_confirm_password);
        mStatus = view.findViewById(R.id.textView_status);

        buttonToLogin.setOnClickListener(this);
        buttonActionRegister.setOnClickListener(this);
        return view;
    }

    /**
     * The OnClick method evaluate which button was tapped/clicked to execute the corresponding actions
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_to_login:
                // When the Login button is tapped/Clicked the Fragment is replaced  with login screen
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.fragmentLayout_main, new Login())
                        .commit();
                break;

            case R.id.button_action_register:
                String username = mUsername.getText().toString();
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                String confirmPassword = mConfirmPassword.getText().toString();

                try {
                    MainActivity.userService.addUser(username, email, password, confirmPassword);
                    // Toast prints a notification overlay on the main activity
                    Toast.makeText(getActivity(), "User Added Successfully", Toast.LENGTH_LONG).show();
                    MainActivity.fragmentManager.beginTransaction()
                            .replace(R.id.fragmentLayout_main, new Login())
                            .commit();

                } catch (Exception e) {
                    //if fields are invalid, print to status
                    mStatus.setText(e.getMessage());

                    //reset password fields
                    mPassword.setText("");
                    mConfirmPassword.setText("");

                } finally {
                    break;
                }

        }
    }
}
