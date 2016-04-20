package com.user.globears;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimesFragment extends Fragment {

    private DatePicker datePicker;
    private TimePicker timePicker;

    public TimesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_times, container, false);

        return view;
    }

}
