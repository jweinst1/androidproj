package com.user.globears;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class TutorActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor);

        // Set the fragment initially
        TutorHomeFragment tutorHomeFragment = new TutorHomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, tutorHomeFragment);
        fragmentTransaction.commit();
        setTitle("Home");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tutor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            TutorHomeFragment tutorHomeFragment = new TutorHomeFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, tutorHomeFragment);
            fragmentTransaction.commit();
            setTitle("Home");
        } else if (id == R.id.profile) {
            TutorProfileFragment tutorProfileFragment = new TutorProfileFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, tutorProfileFragment);
            fragmentTransaction.commit();
            setTitle("Profile");
        } else if (id == R.id.inbox) {
            TutorInboxFragment tutorInboxFragment = new TutorInboxFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, tutorInboxFragment);
            fragmentTransaction.commit();
            setTitle("Inbox");
        } else if (id == R.id.upcoming_sessions) {
            TutorSessionsFragment tutorSessionsFragment = new TutorSessionsFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, tutorSessionsFragment);
            fragmentTransaction.commit();
            setTitle("Upcoming Sessions");
        } else if (id == R.id.times_of_availability) {
            TutorAvailabilityFragment tutorAvailabilityFragment = new TutorAvailabilityFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, tutorAvailabilityFragment);
            fragmentTransaction.commit();
            setTitle("Times of Availability");
        } else if (id == R.id.upload_transcript) {
            TutorTranscriptFragment tutorTranscriptFragment = new TutorTranscriptFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, tutorTranscriptFragment);
            fragmentTransaction.commit();
            setTitle("Upload Transcript");
        } else if (id == R.id.my_classes) {
            TutorClassesFragment tutorClassesFragment = new TutorClassesFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, tutorClassesFragment);
            fragmentTransaction.commit();
            setTitle("My Classes");
        } else if (id == R.id.payment) {
            PaymentFragment paymentFragment = new PaymentFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, paymentFragment);
            fragmentTransaction.commit();
            setTitle("Payment");
        } else if (id == R.id.switch_to_tutee) {
            Intent intent = new Intent(this, TuteeActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
