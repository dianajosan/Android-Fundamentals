package com.example.cinci;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public static final int REQUEST_CODE=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText heigthEdit=findViewById(R.id.edit_text_height);

        findViewById(R.id.navigate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, NewActivity.class);
                intent.putExtra("height", heigthEdit.getText().toString());
                startActivityForResult(intent, REQUEST_CODE);
            }

        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode, data);

        if(requestCode==REQUEST_CODE){
            if(resultCode== Activity.RESULT_OK){
                if(data!=null){
                    String result=data.getStringExtra("result");
                    Toast.makeText(this, "Has my height enough" +result, Toast.LENGTH_SHORT).show();
                }
            } else if(resultCode==Activity.RESULT_CANCELED){
                Toast.makeText(this,"Invalid height",
                Toast.LENGTH_LONG).show();
            }
        }
    }
}