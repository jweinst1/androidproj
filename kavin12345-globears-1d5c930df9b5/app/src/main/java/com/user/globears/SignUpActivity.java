package com.user.globears;

import android.app.ActionBar;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SignUpActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText name, email, password;
    private TextView back;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.pass_word);

        back = (TextView) toolbar.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        button = (Button) findViewById(R.id.next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidName() && isValidPass() && isValidEmail(email.getText().toString())) {
                    displayToast("Login info works!");
                }
            }
        });
    }

    public boolean isValidName() {
        String nameString = name.getText().toString();
        if (nameString.contains(" ")) {
            return true;
        } else if (nameString.length() == 0) {
            displayToast("Please enter your full name.");
            return false;
        } else {
            displayToast("Please enter a last name.");
            return false;
        }
    }

    public boolean isValidPass() {
        String passwordString = password.getText().toString();
        if (passwordString.length() > 5) {
            return true;
        } else if (passwordString.length() == 0) {
            displayToast("Please enter a password.");
            return false;
        } else {
            displayToast("Password must be at least 6 characters long.");
            return false;
        }
    }

    public boolean isValidEmail(CharSequence target) {
        if (target == null) {
            displayToast("Please enter an email.");
            return false;
        }

        boolean bool = android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        if (bool) {
            return true;
        } else {
            displayToast("Please enter a valid email");
            return false;
        }
    }



    public void displayToast(String string) {
        Toast toast = Toast.makeText(getApplicationContext(),
                string,
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }

    @Override
    public void onBackPressed()
    {
        finish();
    }
}
