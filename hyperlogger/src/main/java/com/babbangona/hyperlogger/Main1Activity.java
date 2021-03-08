package com.babbangona.hyperlogger;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.babbangona.hyperlogger.Database.AppDatabase;

public class Main1Activity extends AppCompatActivity {

    AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appDatabase = AppDatabase.getInstance(this);
    }
}