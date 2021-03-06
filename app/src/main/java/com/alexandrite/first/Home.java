package com.alexandrite.first;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.alexandrite.first.R.layout.activity_home);
    }

    public void goToPreferences(View view) {
        Intent preferencesIntent = new Intent(this, Preference.class);
        startActivity(preferencesIntent);
    }

    public void goToDatabases(View view) {
        Intent preferencesIntent = new Intent(this, Databases.class);
        startActivity(preferencesIntent);
    }

    public void goToFiles(View view) {
        Intent preferencesIntent = new Intent(this, Files.class);
        startActivity(preferencesIntent);
    }

}
