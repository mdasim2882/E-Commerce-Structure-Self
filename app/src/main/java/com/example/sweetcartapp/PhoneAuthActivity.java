package com.example.sweetcartapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks;

import java.util.concurrent.TimeUnit;

/*
* Here a Log.d(....) is only used for debugging purpose
*
* */

public class PhoneAuthActivity extends AppCompatActivity {


    private String verificationCodeBySystem;
    private boolean codeSentStatus;
    private final String TAG = getClass().getSimpleName();
    private boolean autoverifiedStatus;
    private boolean vfOnPressed;
    private String inputOTP;
    EditText mobileno;
    EditText otpno;

    Button btnsend,btnvalidate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_auth);
        mobileno = findViewById(R.id.contactno);
        otpno = findViewById(R.id.otpnum);
        btnsend=findViewById(R.id.verifybutton);
        btnvalidate=findViewById(R.id.verifybutton);

    }

    public void verifyUsingButton(View view) {
        String input = mobileno.getText().toString();

        sendVerificationCodeToUser(input);
    }

/*--------------------------------Phone Authentication Methods-----------------------------------*/
    public void sendVerificationCodeToUser(String phoneNo) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNo,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                TaskExecutors.MAIN_THREAD,   // Activity (for callback binding)
                mCallbacks);// OnVerificationStateChangedCallbacks
    }

    private OnVerificationStateChangedCallbacks mCallbacks =
            new OnVerificationStateChangedCallbacks() {

                @Override
                public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    Log.d(TAG, "onCodeSent: Called");
                    codeSentStatus = true;
                    verificationCodeBySystem = s;
                    autoverifiedStatus = false;
                    startTimerResendCode();

                }

                @Override
                public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                    Log.d(TAG, "onVerificationCompleted: Automatically Verified");

                    String code = phoneAuthCredential.getSmsCode();
                    /* Check if OTP is received on the current device is not null
                     *  Store it into the CommonUserInfoDetails.inputOTP
                     *  Verify it with PhoneAuth Credentials
                     * */

                    if (code != null) {
                        inputOTP = code;
                        Log.d(TAG, " RECEIVED OTP ON DEVICE: " + inputOTP);
                        verifyCode(code);
                    }
                }

                @Override
                public void onVerificationFailed(FirebaseException e) {

                    if (codeSentStatus) {
                        autoverifiedStatus = false;
                    }

                    Log.d(TAG, "onVerificationFailed Called: LOG MESSAGE : \n" + e.getMessage());
                }
            };

    private void startTimerResendCode() {
        // Start Countdown timer here for 50 seconds

    }

    public void verifyCode(String codeByUser) {
        // Show a Verifying... dialog
        vfOnPressed = false;
        Log.d(TAG, "verifyCode: VERIFYING . . . ");
        Toast.makeText(this, "VERIFYING . . . ", Toast.LENGTH_SHORT).show();

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCodeBySystem, codeByUser);
        signInTheUserByCredentials(credential);

    }

    private void signInTheUserByCredentials(PhoneAuthCredential credential) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(PhoneAuthActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            autoverifiedStatus = true;
                            if (autoverifiedStatus) {
                                //Start the Welcome Fragments to the user
                                startWelcomePages();


                            }

                            Log.d(TAG, "onComplete: DONE WITH OTP VERIFICATION ");
                            Toast.makeText(PhoneAuthActivity.this, "Verification Success", Toast.LENGTH_SHORT).show();
                            vfOnPressed = true;


                        } else {
                            Log.d(TAG, "onComplete: FAILED LOG MESSAGE : " + task.getException().getMessage());
                            autoverifiedStatus = false;

                            Toast.makeText(PhoneAuthActivity.this, "Wrong OTP enterred", Toast.LENGTH_SHORT).show();


                        }

                    }
                });
    }

    private void startWelcomePages() {
        Intent i=new Intent(this,WelcomePagesActivity.class);
        startActivity(i);
        finish();
    }


    public void authenticateOTP(View view) {
        String input = otpno.getText().toString();
        verifyCode(input);
    }



/*
    public boolean checkValidityInputs() {


        if (TextUtils.isEmpty(mobileno.getText().toString())) {
            tilUser.setErrorEnabled(true);
            tilUser.setError("Username can't be empty");
            unStatus = false;
            // titleDetails.setVisibility(View.GONE);

        } else {
            unStatus = true;
            tilUser.setErrorEnabled(false);
            tilUser.setError(null);
            // titleDetails.setVisibility(View.VISIBLE);

        }
    }
*/



    }