package com.missouristate.guadagnano.groupprojectapp;

import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateStudent extends AppCompatActivity{
    DBManager dbManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DBManager(this);
        updateView();
    }
    public void updateView() {
        ArrayList<Student> list = dbManager.selectAllStudents();
        RelativeLayout layout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);
        RadioGroup group = new RadioGroup(this);
        for (Student student : list) {
            RadioButton rb = new RadioButton(this);
            rb.setId(student.getId());
            rb.setText(student.toString());
            group.addView(rb);
        }

        scrollView.addView(group);
        layout.addView(scrollView);
        setContentView(layout);
    }
}
