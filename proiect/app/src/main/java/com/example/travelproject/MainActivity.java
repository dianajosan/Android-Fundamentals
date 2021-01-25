package com.example.travelproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter=3;

    private String U="d";
    private String P="1";
    boolean isValid=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username = (EditText) findViewById(R.id.username);
        Password = (EditText) findViewById(R.id.password);
        Info = (TextView) findViewById(R.id.attempts);
        Login = (Button) findViewById(R.id.login_button);

        Info.setText("Number of attempts remaining: 3");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=Username.getText().toString();
                String pass=Password.getText().toString();

                if(user.isEmpty() || pass.isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter the data correctly!", Toast.LENGTH_SHORT).show();
                }else{
                    isValid=validate(user, pass);

                    if(!isValid){
                        counter--;
                        Toast.makeText(MainActivity.this, "Incorrect data entered!", Toast.LENGTH_SHORT).show();

                        Info.setText("Number of attempts remaining: " +counter);

                        if(counter==0){
                            Login.setEnabled(false);
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MainActivity.this, FirstActivity.class);
                        startActivity(intent);
                    }
                }

            }
        });

    }

    private boolean validate(String usern, String passw){
        if(usern.equals(U) && passw.equals(P)){
            return true;
        }
        return false;
    }



}