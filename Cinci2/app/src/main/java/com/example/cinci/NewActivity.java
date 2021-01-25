package com.example.cinci;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



public class NewActivity extends AppCompatActivity {
    private String height;
    @Override
    protected <height> void onCreate(@Nullable Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.new_activity);

        if(getIntent().getExtras()!=null){
            height=getIntent().getStringExtra("height");
        }

        String finalHeight = height;
        findViewById(R.id.analyze).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                int heightInOn = Integer.valueOf(height);
                if(heightInOn<8){
                    setResult(Activity.RESULT_CANCELED);
                    finish();
                }
                String isEnough;
                if (heightInOn < 180) {
                    isEnough = "YES";
                } else {
                    isEnough = "NO";

                }
                Intent intent = new Intent();
                intent.putExtra("result", isEnough);
                setResult(Activity.RESULT_OK, intent);
                finish();
            } catch (NumberFormatException e) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }

        });

        Log.i("Lifestyle","onCreate - " + height);


    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Lifestyle","onStart");

    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.i("Lifestyle","onResume");

    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i("Lifestyle","onPause");

    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i("Lifestyle","onStop");

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i("Lifestyle","onDestroy");

    }
}
