package com.user.globears;

import android.content.Intent;
import android.media.Image;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class HomeActivity extends AppCompatActivity {

    private FloatingActionMenu floatingActionMenu;
    private FloatingActionButton inbox, sessions, classes, payment, profile, home, question;
    private ImageView keyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        floatingActionMenu = (FloatingActionMenu) findViewById(R.id.menu);

        inbox = (FloatingActionButton) findViewById(R.id.inbox_item);
        sessions = (FloatingActionButton) findViewById(R.id.upcoming_sessions_item);
        classes = (FloatingActionButton) findViewById(R.id.my_classes_item);
        payment = (FloatingActionButton) findViewById(R.id.payment_item);
        profile = (FloatingActionButton) findViewById(R.id.profile_item);
        home = (FloatingActionButton) findViewById(R.id.home_item);
        question = (FloatingActionButton) findViewById(R.id.question);

        TutorHomeFragment tutorHomeFragment = new TutorHomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, tutorHomeFragment);
        fragmentTransaction.commit();
        setTitle("Home");

        inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TutorInboxFragment tutorInboxFragment = new TutorInboxFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, tutorInboxFragment);
                fragmentTransaction.commit();
                setTitle("Inbox");
                floatingActionMenu.close(true);
            }
        });

        sessions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TutorSessionsFragment tutorSessionsFragment = new TutorSessionsFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, tutorSessionsFragment);
                fragmentTransaction.commit();
                setTitle("Upcoming Sessions");
                floatingActionMenu.close(true);
            }
        });

        classes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TutorClassesFragment tutorClassesFragment = new TutorClassesFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, tutorClassesFragment);
                fragmentTransaction.commit();
                setTitle("My Classes");
                floatingActionMenu.close(true);
            }
        });

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaymentFragment paymentFragment = new PaymentFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, paymentFragment);
                fragmentTransaction.commit();
                setTitle("Payment");
                floatingActionMenu.close(true);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TutorProfileFragment tutorProfileFragment = new TutorProfileFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, tutorProfileFragment);
                fragmentTransaction.commit();
                setTitle("Profile");
                floatingActionMenu.close(true);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TutorHomeFragment tutorHomeFragment = new TutorHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, tutorHomeFragment);
                fragmentTransaction.commit();
                setTitle("Home");
                floatingActionMenu.close(true);
            }
        });

        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.action_logout:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
