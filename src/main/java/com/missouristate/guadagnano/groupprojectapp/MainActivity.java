package com.missouristate.guadagnano.groupprojectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DBManager(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflates the menu
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Contains handle action bar items
        int id = item.getItemId();
        switch (id) {

            case R.id.action_student:
                Log.w("MainActivity", "Add Selected");
                Intent insertStudentIntent = new Intent(this, InsertStudent.class);
                this.startActivity(insertStudentIntent);
                return true;

            case R.id.action_instructor:
                Log.w("MainActivity", "Add Selected");
                Intent insertInstructorIntent = new Intent(this, InsertInstructor.class);
                this.startActivity(insertInstructorIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
