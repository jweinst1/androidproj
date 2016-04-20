package com.user.globears;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.devmarvel.creditcardentry.library.CreditCard;
import com.devmarvel.creditcardentry.library.CreditCardForm;
import com.stripe.android.*;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;

import io.card.payment.CardIOActivity;

public class StripeForm extends AppCompatActivity {

    private static final int MY_SCAN_REQUEST_CODE = 1;

    private RelativeLayout relativeLayout;
    private CreditCardForm creditCardForm;
    private TextView textView;
    private Button button;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_stripe_form);

        relativeLayout = (RelativeLayout) findViewById(R.id.relative_layout);
        creditCardForm = (CreditCardForm) findViewById(R.id.credit_card_form);
        textView = (TextView) findViewById(R.id.scan_card);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onScanPress(view);
            }
        });

        button = (Button) findViewById(R.id.save_info);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (creditCardForm.isCreditCardValid()) {
                    CreditCard creditCard = creditCardForm.getCreditCard();
                    Card card = new Card(creditCard.getCardNumber(),
                            creditCard.getExpMonth(),
                            creditCard.getExpYear(),
                            creditCard.getSecurityCode());
                    //Pass credit card to service
                    if (!card.validateCard()) {
                        Toast.makeText(getApplicationContext(),
                                "Invalid Card", Toast.LENGTH_SHORT).show();
                    } else {
                        new Stripe().createToken(card, "sk_test_Ek9QmFRxQjkjT7cFeHdtmuKZ",
                                new TokenCallback() {
                                    @Override
                                    public void onError(Exception error) {
                                        // Show localized error message
                                        Toast.makeText(getApplicationContext(),
                                                "Error",
                                                Toast.LENGTH_LONG
                                        ).show();
                                    }

                                    @Override
                                    public void onSuccess(Token token) {
                                        // send token to server
                                    }
                                });
                    }
                } else {
                    //Alert Credit card invalid
                    Toast.makeText(getApplicationContext(),
                            "Invalid Card", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onScanPress(View v) {
        Intent scanIntent = new Intent(this, CardIOActivity.class);

        // customize these values to suit your needs.
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, false); // default: false
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false); // default: false
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false); // default: false

        // MY_SCAN_REQUEST_CODE is arbitrary and is only used within this activity.
        startActivityForResult(scanIntent, MY_SCAN_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MY_SCAN_REQUEST_CODE) {
            String resultDisplayStr;
            if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
                io.card.payment.CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);

                // Never log a raw card number. Avoid displaying it, but if necessary use getFormattedCardNumber()
                resultDisplayStr = "Card Number: " + scanResult.getRedactedCardNumber() + "\n";

                // Do something with the raw number, e.g.:
                // myService.setCardNumber( scanResult.cardNumber );
                creditCardForm.setCardNumber(scanResult.cardNumber, true);

                if (scanResult.isExpiryValid()) {
                    resultDisplayStr += "Expiration Date: " + scanResult.expiryMonth + "/" + scanResult.expiryYear + "\n";
                }

                if (scanResult.cvv != null) {
                    // Never log or display a CVV
                    resultDisplayStr += "CVV has " + scanResult.cvv.length() + " digits.\n";
                }

                if (scanResult.postalCode != null) {
                    resultDisplayStr += "Postal Code: " + scanResult.postalCode + "\n";
                }
            }
            else {
                resultDisplayStr = "Scan was canceled.";
            }
            // do something with resultDisplayStr, maybe display it in a textView
            // resultTextView.setText(resultDisplayStr);
        }
        // else handle other activity results
    }
}
