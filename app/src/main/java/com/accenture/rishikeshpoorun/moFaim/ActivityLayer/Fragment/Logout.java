package com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Activity.Dashboard;
import com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Activity.MainActivity;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.User;
import com.accenture.rishikeshpoorun.moFaim.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Logout extends Fragment implements View.OnClickListener {
    Button buttonActionContinue, buttonActionLogout;

    public Logout() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_logout, container, false);

        buttonActionContinue = view.findViewById(R.id.button_action_continue);
        buttonActionLogout = view.findViewById(R.id.button_action_logout);

        String[] username = MainActivity.userSession.getUserName().split(" ");
        String buttonText = "Continue as "+username[0];
        buttonActionContinue.setText(buttonText);

        buttonActionLogout.setOnClickListener(this);
        buttonActionContinue.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_action_continue:
                Intent toDashboard = new Intent(getContext(), Dashboard.class);
                startActivity(toDashboard);
                getActivity().finish();
                break;

            case R.id.button_action_logout:
                MainActivity.userSession.destroySession();
                Intent toMain = new Intent(getContext(), MainActivity.class);
                startActivity(toMain);
                getActivity().finish();
                break;
        }
    }
}
