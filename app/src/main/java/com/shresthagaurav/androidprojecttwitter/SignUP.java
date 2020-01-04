package com.shresthagaurav.androidprojecttwitter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

public class SignUP extends AppCompatActivity {
    EditText sn_email, sn_username;
    ImageButton sn_Us, sn_Em;
    Button btn_next;
    int countUsername = 0;
    int initialbtn = 0;
    String method = "email";
    TextView tvChange, sn_em_error, sn_us_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sign_up );
        sn_email = findViewById( R.id.SN_email );
        sn_username = findViewById( R.id.SN_usernmae );
        sn_em_error = findViewById( R.id.SN_pass_error );
        sn_us_error = findViewById( R.id.SN_username_error );
        sn_Us = findViewById( R.id.SN_userP );
        sn_Em = findViewById( R.id.SN_emailP );
        btn_next = findViewById( R.id.btn_FS_signup );
        tvChange = findViewById( R.id.textView9 );
        sn_username.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int countL = sn_username.length();
                if (count > 0) {
                    if (countUsername >= 0) {
                        countUsername = 50 - countL;
                        sn_us_error.setTextColor( Color.BLACK );
                        sn_us_error.setText( "" + countUsername );
                        sn_Us.setImageResource( R.drawable.ic_checked );
                        sn_email.setText( String.valueOf( countUsername ) );
                        return;
                    } else if (countUsername < 0) {
                        countUsername = 50 - countL;
                        sn_us_error.setTextColor( Color.RED );
                        sn_us_error.setText( "Must be 50 characters or fewer." );
                        sn_us_error.append( "      " + countUsername );
                        sn_email.setText( String.valueOf( countUsername ) );
                        sn_Us.setImageResource( R.drawable.ic_clear );
                        return;


                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        } );
        tvChange.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (initialbtn == 0) {
                    method = "phone";
                    sn_email.setText( "" );
                    initialbtn++;
                    sn_email.setHint( "used Phone number" );
                    sn_email.setInputType( InputType.TYPE_CLASS_PHONE );
                    tvChange.setText( "use email instead" );
                    return;
                } else {
                    method = "email";
                    sn_email.setText( "" );
                    initialbtn = 0;
                    sn_email.setInputType( InputType.TYPE_CLASS_TEXT );
                    sn_email.setHint( "used Email" );
                    tvChange.setText( "use phone instead" );
                    return;
                }

            }
        } );
        sn_email.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                switch (method) {
                    case "email":

                        if ((sn_email.getText().toString().toLowerCase().contains( "@" ))&&(sn_email.getText().toString().toLowerCase().contains( ".com" ))) {
                            sn_Em.setImageResource( R.drawable.ic_checked );

                        } else {
                            sn_em_error.setText( "check your email" );
                            sn_Em.setImageResource( R.drawable.ic_clear );

                        }
                        break;
                    case "phone":
                        if (sn_email.length() <= 10) {
                            sn_Em.setImageResource( R.drawable.ic_checked );
                            sn_em_error.setText( "" );

                            return;

                        } else {
                            sn_em_error.setText( "check your number" );
                            sn_Em.setImageResource( R.drawable.ic_clear );
                            return;

                        }
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        } );

    }
}
