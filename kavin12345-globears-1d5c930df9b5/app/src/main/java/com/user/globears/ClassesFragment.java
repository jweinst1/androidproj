package com.user.globears;


import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClassesFragment extends Fragment {

    private ListView listView;
    private SearchView searchView;
    private String[] classes = {"CS61A - The Structure and Interpretation of Computer Programming", "CS61B - Data Structures", "CS61C - Machine Structures",
        "MATH1A - Calculus", "MATH1B - Calculus"};
    private ArrayAdapter<String> arrayAdapter;
    private ImageView check;

    public ClassesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_classes, container, false);

        listView = (ListView) view.findViewById(R.id.list_of_classes);
        listView.setVisibility(View.INVISIBLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                searchView.setQuery(adapterView.getItemAtPosition(i).toString(), false);
                listView.setVisibility(View.INVISIBLE);
            }
        });

        searchView = (SearchView) view.findViewById(R.id.search_for_classes);
        searchView.setQueryHint("Search Classes..");
        searchView.onActionViewExpanded();

        arrayAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, classes);
        listView.setAdapter(arrayAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                if (text.length() == 0) {
                    listView.setVisibility(View.INVISIBLE);
                } else {
                    listView.setVisibility(View.VISIBLE);
                    arrayAdapter.getFilter().filter(text);
                }

                return false;
            }
        });

        check = (ImageView) view.findViewById(R.id.check);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TimesActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
