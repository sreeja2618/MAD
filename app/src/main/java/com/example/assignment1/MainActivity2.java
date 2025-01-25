package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Student student = (Student) getIntent().getSerializableExtra("key");

        String name = "Faculty Name: <i>" + student.getName() + "</i>";
        String state = "Designation: <i>" + student.getState() + "</i>";
        String gender = "Gender: <i>" + student.getGender() + "</i>";
        String dob = "Date of Join: <i>" + student.getDob() + "</i>";

        String experience = calculateExperience(student.getDob());

        Toast.makeText(this, "Hello, " + student.getName() + "!", Toast.LENGTH_SHORT).show();

        TextView textViewName = findViewById(R.id.textViewName);
        textViewName.setText(Html.fromHtml(name));

        TextView textViewGender = findViewById(R.id.textViewGender);
        textViewGender.setText(Html.fromHtml(gender));

        TextView textViewDOB = findViewById(R.id.textViewDOB);
        textViewDOB.setText(Html.fromHtml(dob));

        TextView textViewState = findViewById(R.id.textViewState);
        textViewState.setText(Html.fromHtml(state));

        TextView textViewExperience = findViewById(R.id.experience);
        textViewExperience.setText(Html.fromHtml("Experience: <i>" + experience + "</i>"));
    }

    private String calculateExperience(String dob) {
        try {
            String[] dateParts = dob.split("-");
            int day = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]) - 1;
            int year = Integer.parseInt(dateParts[2]);

            Calendar today = Calendar.getInstance();
            Calendar joiningDate = Calendar.getInstance();
            joiningDate.set(year, month, day);

            int diffYear = today.get(Calendar.YEAR) - joiningDate.get(Calendar.YEAR);
            int diffMonth = today.get(Calendar.MONTH) - joiningDate.get(Calendar.MONTH);
            int diffDay = today.get(Calendar.DAY_OF_MONTH) - joiningDate.get(Calendar.DAY_OF_MONTH);

            if (diffDay < 0) {
                diffMonth--;
                diffDay += joiningDate.getActualMaximum(Calendar.DAY_OF_MONTH);
            }
            if (diffMonth < 0) {
                diffYear--;
                diffMonth += 12;
            }

            return diffYear + " years, " + diffMonth + " months, " + diffDay + " days";

        } catch (Exception e) {
            e.printStackTrace();
            return "Invalid Date of Join";
        }
    }
}
