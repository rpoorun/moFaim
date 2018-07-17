package com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Activity.Dashboard;
import com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Activity.MainActivity;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.User;
import com.accenture.rishikeshpoorun.moFaim.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment implements View.OnClickListener {
    private Button buttonToRegister, buttonActionLogin;
    private EditText mEmail, mPassword;

    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        buttonToRegister = view.findViewById(R.id.button_to_register);
        buttonActionLogin = view.findViewById(R.id.button_action_login);

        mEmail = view.findViewById(R.id.editText_email);
        mPassword = view.findViewById(R.id.editText_password);

        buttonToRegister.setOnClickListener(this);
        buttonActionLogin.setOnClickListener(this);

        return view;
    }

    /**
     * When the Register button is tapped on the Login Fragment,
     * Replace the fragment container of the Main Activity with the Register Fragment
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_to_register:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragmentLayout_main, new Register())
                        .addToBackStack(null)
                        .commit();
                break;

            case R.id.button_action_login:
                User user = MainActivity.userDatabase.userDAO()
                        .selectUserAndPassword(mEmail.getText().toString(), mPassword.getText().toString());

                if(user == null){
                    Toast.makeText(getActivity(),"User Login does not Exist", Toast.LENGTH_LONG).show();
                    break;
                }
                else{
                    Intent toDashboard = new Intent(getActivity(), Dashboard.class);
                    startActivity(toDashboard.putExtra("Email", user.getEmail()));

                }
        }
    }
}
