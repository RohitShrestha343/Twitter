package com.shresthagaurav.androidprojecttwitter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Final_Step_signUP extends AppCompatActivity {
Button btn_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_final__step_sign_up );
        btn_signup=findViewById( R.id.btn_FS_signup );
        btn_signup.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( Final_Step_signUP.this,Verification.class );
                startActivity( intent );
            }
        } );
    }
}
