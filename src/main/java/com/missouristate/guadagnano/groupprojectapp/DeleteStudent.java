package com.missouristate.guadagnano.groupprojectapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import java.util.ArrayList;

public class DeleteStudent extends AppCompatActivity{
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DBManager(this);
        updateView();
    }

    //Build a dynamic view with all the available students
    private void updateView() {
        ArrayList<Student> students = dbManager.selectAllStudents( );
        RelativeLayout layout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);
        RadioGroup group = new RadioGroup(this);

        for (Student student : students) {
            RadioButton rb = new RadioButton(this);
            rb.setId(student.getId());
            rb.setText(student.toString());
            group.addView(rb);
        }

        //sets up event handling
        RadioButtonHandler rbh = new RadioButtonHandler();
        group.setOnCheckedChangeListener(rbh);

        //creates a back button
        Button backButton = new Button(this);
        backButton.setText("BACK");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteStudent.this.finish();
            }
        });

        scrollView.addView(group);
        layout.addView(scrollView);

        //adds a back button at the bottom of the view
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.setMargins(0, 0, 0, 50);
        layout.addView(backButton, params);

        setContentView(layout);
    }

    private class RadioButtonHandler implements RadioGroup.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            //Deletes from database
            dbManager.deleteStudentById(checkedId);
            Toast.makeText(DeleteStudent.this, "Student deleted", Toast.LENGTH_SHORT).show();

            //updates screen
            updateView();
        }
    }
}
