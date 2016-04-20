package com.user.globears;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TuteeSessionsFragment extends Fragment {

    private ListView listView;
    private List<String> list;
    private ArrayAdapter<String> arrayAdapter;

    public TuteeSessionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tutee_sessions, container, false);

        listView = (ListView) view.findViewById(R.id.list_of_sessions);
        list = new ArrayList<String>();
        list.add("Eric Seo: 4:00 PM - 5:00 PM / Dwinelle Hall");
        list.add("Umesh Vazirani: 8:00 AM - 8:00 PM / 320 Soda");
        list.add("John Gray: 12:00 PM - 1:00 PM / FSM Cafe");
        arrayAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);

        return view;
    }

}
