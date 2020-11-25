package com.babbangona.hyperlogger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.babbangona.hyperlogger.Database.AppDatabase;

public class MainActivity extends AppCompatActivity {

    AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appDatabase = AppDatabase.getInstance(this);
    }
}