package com.vinnorman.bmi_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button calculateBtn;
    private TextView result;
    private RadioButton male;
    private RadioButton female;
    private EditText age;
    private EditText feet;
    private EditText inches;
    private EditText weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi_main);

        calculateBtn = findViewById(R.id.button);
        result = findViewById(R.id.result);
        male = findViewById(R.id.maleRadioButton);
        female = findViewById(R.id.femaleRadioButton);
        age = findViewById(R.id.age);
        feet = findViewById(R.id.feet);
        inches = findViewById(R.id.inches);
        weight = findViewById(R.id.weight);

        calculateBtn.setOnClickListener(v -> {
            if ((!male.isChecked() && !female.isChecked()) || age.getText().toString().isEmpty() || feet.getText().toString().isEmpty() || inches.getText().toString().isEmpty() || weight.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            if(Integer.parseInt(age.getText().toString()) < 18) {
                Toast.makeText(this, "You must be 18 years or older to use this app", Toast.LENGTH_SHORT).show();
                return;
            }
            calculate_bmi();
        });
    }

    private void calculate_bmi() {
        int total_inches = Integer.parseInt(feet.getText().toString()) * 12 + Integer.parseInt(inches.getText().toString());
        double height = total_inches * 0.0254;
        double bmi = Double.parseDouble(weight.getText().toString()) / (height * height);

        String result_string;
        if (bmi < 18.5) {
            result_string = "Underweight";
        } else if (bmi < 24.9) {
            result_string = "Normal weight";
        } else if (bmi < 29.9) {
            result_string = "Overweight";
        } else {
            result_string = "Obese";
        }
        result.setText(result_string);
    };
}