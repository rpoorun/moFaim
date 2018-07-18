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
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.User;
import com.accenture.rishikeshpoorun.moFaim.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgotPassword extends Fragment implements View.OnClickListener {
    private EditText mUsername, mEmail;
    private Button buttonActionResetPassword, buttonActionSendResetEmail;
    private TextView mStatus;

    public ForgotPassword() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);

        mUsername = view.findViewById(R.id.editText_username);
        mEmail = view.findViewById(R.id.editText_email);
        mStatus = view.findViewById(R.id.textView_status);

        buttonActionResetPassword = view.findViewById(R.id.button_action_reset_password);
        buttonActionSendResetEmail = view.findViewById(R.id.button_action_send_email);

        buttonActionSendResetEmail.setOnClickListener(this);
        buttonActionResetPassword.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_action_reset_password:
                try{
                    MainActivity.userService.matchUsernameAndEmail(mUsername.getText().toString(),mEmail.getText().toString());
                    User user = MainActivity.userService.searchUserByEmail(mEmail.getText().toString());
                    MainActivity.fragmentManager.beginTransaction().replace(R.id.fragmentLayout_main, new ResetPassword(user))
                            .addToBackStack(null)
                            .commit();


                } catch (Exception e){
                    mStatus.setText(e.getMessage());
                }finally {
                    break;
                }

            case R.id.button_action_send_email:
                try {
                    //dummy function to be impl
                    Toast.makeText(getActivity(),"Password Reset Email Sent", Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    mStatus.setText(e.getMessage());
                }finally {
                    break;
                }
        }
    }
}
