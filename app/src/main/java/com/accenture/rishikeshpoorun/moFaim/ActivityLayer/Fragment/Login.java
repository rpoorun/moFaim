package com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Activity.MainActivity;
import com.accenture.rishikeshpoorun.moFaim.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment implements View.OnClickListener {
    private Button buttonToRegister;

    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        buttonToRegister = view.findViewById(R.id.button_register);
        buttonToRegister.setOnClickListener(this);

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
            case R.id.button_register:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragmentLayout_main, new Register())
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }
}
