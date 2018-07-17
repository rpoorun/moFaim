package com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Fragment;


import android.annotation.SuppressLint;
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
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Exception.EmptyFieldException;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Exception.InvalidPasswordException;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.User;
import com.accenture.rishikeshpoorun.moFaim.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResetPassword extends Fragment implements View.OnClickListener {

    private EditText mPassword, mConfirmPassword;
    private TextView mStatus;
    private Button buttonActionSetPassword;
    private User user;

    public ResetPassword() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public ResetPassword(User user) {
        this.user = user;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reset_password, container, false);

        mStatus = view.findViewById(R.id.textView_status);
        mPassword = view.findViewById(R.id.editText_password);
        mConfirmPassword = view.findViewById(R.id.editText_confirmpassword);

        buttonActionSetPassword = view.findViewById(R.id.button_action_set_password);

        buttonActionSetPassword.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_action_set_password:
                try {
                    MainActivity.userService.setNewPassword(user, mPassword.getText().toString(), mConfirmPassword.getText().toString());
                    Toast.makeText(getActivity(),"Password changed successfully", Toast.LENGTH_LONG).show();

                    MainActivity.fragmentManager.beginTransaction().replace(R.id.fragmentLayout_main, new Login())
                            .addToBackStack(null)
                            .commit();
                } catch (Exception e) {
                    mStatus.setText(e.getMessage());
                } finally {
                    break;
                }
        }
    }
}
