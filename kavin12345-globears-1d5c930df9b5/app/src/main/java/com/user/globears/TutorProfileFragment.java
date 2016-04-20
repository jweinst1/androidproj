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
public class TutorProfileFragment extends Fragment {

    private ListView listView;
    private List<String> list;
    private ArrayAdapter<String> arrayAdapter;

    public TutorProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tutor_profile, container, false);

        listView = (ListView) view.findViewById(R.id.list_of_classes);
        list = new ArrayList<String>();
        list.add("61a");
        list.add("61b");
        list.add("61c");
        arrayAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);

        return view;
    }

}
