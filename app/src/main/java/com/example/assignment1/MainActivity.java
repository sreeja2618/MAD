package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        student = new Student();
        setTitle("Week 4 Assignment");

        fillState();
    }

    public void saveData(View view) {
        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextDOB = findViewById(R.id.editTextDOB);

        student.setName(editTextName.getText().toString());
        student.setGender(getGender());
        student.setDob(editTextDOB.getText().toString());

        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        intent.putExtra("key", student);
        startActivity(intent);
    }

    private String getGender() {
        RadioGroup rbGroupGender = findViewById(R.id.rbGroupGender);
        int selectedRadioButtonId = rbGroupGender.getCheckedRadioButtonId();
        if (selectedRadioButtonId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            return selectedRadioButton.getText().toString();
        }
        return "";
    }

    private void fillState() {
        String[] data = {"Professor", "Associate Professor", "Assistant Professor"};
        Spinner spinnerState = findViewById(R.id.spinnerState);

        ArrayAdapter<String> aa = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerState.setAdapter(aa);

        spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                student.setState(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public void showDatePicker(View view) {
        DatePickerDialog dialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        dialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        monthOfYear++;
        student.setDob(dayOfMonth + "-" + monthOfYear + "-" + year);

        EditText editTextDOB = findViewById(R.id.editTextDOB);
        editTextDOB.setText(student.getDob());
    }
}
