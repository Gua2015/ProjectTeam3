package com.missouristate.guadagnano.groupprojectapp;

import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InsertStudent extends AppCompatActivity {
    DBManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        dbManager = new DBManager(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflates the menu
        getMenuInflater().inflate(R.menu.student_menu, menu);

        return true;
    }


    public void insert(View view) {
        //Get User input
        EditText firstEditText = findViewById(R.id.input_first);
        String first = firstEditText.getText().toString();
        EditText lastEditText = findViewById(R.id.input_last);
        String last = lastEditText.getText().toString();
        EditText statusEditText = findViewById(R.id.input_status);
        String status = lastEditText.getText().toString();


        //Put into DB
        Student student = new Student(0, first, last, status);
        dbManager.insertStudent(student);
        Toast.makeText(this, "Item added!", Toast.LENGTH_SHORT).show();

        //Clear Box
        firstEditText.setText("");
        lastEditText.setText("");
    }

    public void goBack(View v) {
        Intent MainActivityIntent = new Intent(this, MainActivity.class);
        this.startActivity(MainActivityIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Contains handle action bar items
        int id = item.getItemId();
        switch (id) {

            case R.id.action_delete:
                Log.w("MainActivity", "Delete Selected");
                Intent deleteStudentIntent = new Intent(this, DeleteStudent.class);
                this.startActivity(deleteStudentIntent);
                return true;

            case R.id.action_update:
                Log.w("MainActivity", "Update Selected");
                Intent updateStudentIntent = new Intent(this, UpdateStudent.class);
                this.startActivity(updateStudentIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
