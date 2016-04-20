package com.user.globears;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TutorHomeFragment extends Fragment {

    private ImageView keyboard, mic;

    public TutorHomeFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tutor_home, container, false);

        keyboard = (ImageView) view.findViewById(R.id.type);
        keyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new ClassesFragment(), "Classes");
                fragmentTransaction.commit();
            }
        });

        mic = (ImageView) view.findViewById(R.id.voice);
        return view;
    }

}
