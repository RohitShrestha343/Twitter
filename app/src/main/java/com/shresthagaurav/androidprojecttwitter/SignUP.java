package com.shresthagaurav.androidprojecttwitter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.shresthagaurav.androidprojecttwitter.api.ApiClass;
import com.shresthagaurav.androidprojecttwitter.model.Check;
import com.shresthagaurav.androidprojecttwitter.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUP extends AppCompatActivity {
    EditText sn_email, sn_username;
    ImageButton sn_Us, sn_Em;
    Button btn_next;
    int countUsername = 0;
    int initialbtn = 0;
    String method = "email";
    String Email = "";
    String Username="";
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
        Bundle bundle=getIntent().getExtras();
        if (bundle != null) {
Email= bundle.getString( "email" );
Username=bundle.getString( "username" );
            sn_email.setText( bundle.getString( "email" ) );
            sn_username.setText( bundle.getString( "username" ) );
        }
        btn_next.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Email.isEmpty()&&(Username.isEmpty())) {
                    Toast.makeText( SignUP.this, "check value", Toast.LENGTH_SHORT ).show();
                    return;
                } else {
                    User user = new User( Email );
                    Checkuser( user );
                }
            }
        } );
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
                        Username=sn_username.getText().toString();
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
                    sn_email.setMaxLines( 13 );
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
                        sn_em_error.setText( "" );
                        if ((sn_email.getText().toString().toLowerCase().contains( "@" )) && (sn_email.getText().toString().toLowerCase().contains( ".com" ))) {
                            sn_Em.setImageResource( R.drawable.ic_checked );
                            Email = sn_email.getText().toString();
                        } else {
                            sn_em_error.setText( "check your email" );
                            sn_Em.setImageResource( R.drawable.ic_clear );

                        }
                        break;
                    case "phone":
                        sn_em_error.setText( "" );
                        if ((sn_email.length() <= 1)||(sn_email.length()>10)) {
                            sn_em_error.setText( "check your number" );
                            sn_Em.setImageResource( R.drawable.ic_clear );
                            return;

                        } else {
                            sn_Em.setImageResource( R.drawable.ic_checked );
                            Email = sn_email.getText().toString();
                            return;

                        }
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        } );

    }

    void Checkuser(User us) {
        Toast.makeText( this, "   " + us.getEmail(), Toast.LENGTH_SHORT ).show();
        ApiClass apiClass = new ApiClass();
        Call<Check> checkCall = apiClass.calls().check( us );
        checkCall.enqueue( new Callback<Check>() {
            @Override
            public void onResponse(Call<Check> call, Response<Check> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText( SignUP.this, "error" + response.code(), Toast.LENGTH_SHORT ).show();
                    Log.d( "error", "error" + response.code() );
                    return;
                }
                Check check = response.body();
                Toast.makeText( SignUP.this, "user " + check.getStatus(), Toast.LENGTH_SHORT ).show();
if(check.getStatus().equals( "good to go" )){
    Intent next=new Intent( SignUP.this,Final_Step_signUP.class );
    next.putExtra( "email",Email );
    next.putExtra( "username",Username );
    startActivity( next );
    return;
}else{
    Toast.makeText( SignUP.this, "user " + check.getStatus(), Toast.LENGTH_SHORT ).show();
sn_em_error.setText( "exited" );
sn_em_error.setTextColor( Color.RED );
}
            }

            @Override
            public void onFailure(Call<Check> call, Throwable t) {
                Toast.makeText( SignUP.this, "error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
                Log.d( "error", "error   " + t.getLocalizedMessage() );

            }
        } );
    }
}
