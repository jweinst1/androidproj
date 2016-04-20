package com.user.globears;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText password, confirmPassword;
    private Button reset;
    private TextView back;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        password = (EditText) findViewById(R.id.new_password);
        confirmPassword = (EditText) findViewById(R.id.confirm_password);

        reset = (Button) findViewById(R.id.reset_password);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (passwordValid() && confirmPasswordValid()) {
                    goToSignInActivity();
                }
            }
        });

        toolbar = (Toolbar) findViewById(R.id.tool_bar);

        back = (TextView) toolbar.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private boolean passwordValid() {
        String passwordString = password.getText().toString();

        if (passwordString.length() > 5) {
            return true;
        }
        displayToast("Password must be atleast 6 characters long.");
        return false;
    }

    private boolean confirmPasswordValid() {
        String passwordString = password.getText().toString();
        String confirmPasswordString = confirmPassword.getText().toString();

        if (confirmPasswordString.equals(passwordString)) {
            return true;
        }
        displayToast("Passwords do not match.");
        return false;
    }

    private void goToSignInActivity() {
        Intent i = new Intent(this, SignInActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    public void displayToast(String string) {
        Toast toast = Toast.makeText(getApplicationContext(),
                string,
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }

    @Override
    public void onBackPressed()
    {
        finish();
    }
}
