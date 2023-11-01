package com.example.mynewcalculator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
    private double currentResult = 0;
    private String currentInput = "";
    private String currentOperator = "";
    private Button button_CE, button_BS, button_C;
    private Button button_plus, button_sub, button_mul, button_divide, button_result, button_negative, button_stp;
    private Button button_0, button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.result);

        button_CE = findViewById(R.id.button_CE);
        button_BS = findViewById(R.id.button_BS);
        button_C = findViewById(R.id.button_C);
        button_divide = findViewById(R.id.button_chia);
        button_plus = findViewById(R.id.button_plus);
        button_sub = findViewById(R.id.button_sub);
        button_mul = findViewById(R.id.button_mul);
        button_result = findViewById(R.id.button_equals);
        button_negative = findViewById(R.id.button);
        button_stp = findViewById(R.id.button_stp);

        button_0 = findViewById(R.id.button_0);
        button_1 = findViewById(R.id.button_1);
        button_2 = findViewById(R.id.button_2);
        button_3 = findViewById(R.id.button_3);
        button_4 = findViewById(R.id.button_4);
        button_5 = findViewById(R.id.button_5);
        button_6 = findViewById(R.id.button_6);
        button_7 = findViewById(R.id.button_7);
        button_8 = findViewById(R.id.button_8);
        button_9 = findViewById(R.id.button_9);

        button_plus.setOnClickListener(this);
        button_sub.setOnClickListener(this);
        button_mul.setOnClickListener(this);
        button_divide.setOnClickListener(this);
        button_0.setOnClickListener(this);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);
        button_5.setOnClickListener(this);
        button_6.setOnClickListener(this);
        button_7.setOnClickListener(this);
        button_8.setOnClickListener(this);
        button_9.setOnClickListener(this);

        button_CE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("0");
            }
        });

        button_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("0");
            }
        });

        button_BS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backspace();
            }
        });

        button_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
            }
        });
    }


    private void backspace() {
        String currentText = textView.getText().toString();
        if (currentText.length() > 0) {
            currentText = currentText.substring(0, currentText.length() - 1);
            textView.setText(currentText);
        }
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String buttonText = button.getText().toString();
        String currentText = textView.getText().toString();

        // Thêm số được nhấn vào nội dung hiện tại
        currentText = buttonText;
        textView.setText(currentText);

        switch (buttonText) {
            case "+":
                handleOperator(buttonText);
                break;
            case "-":
                handleOperator(buttonText);
                break;
            case "x":
                handleOperator(buttonText);
                break;
            case "/":
                handleOperator(buttonText);
                break;
            case "=":
                calculateResult();
                break;
            default:
                appendDigit(buttonText);
                break;
        }

    }

    private void handleOperator(String operator) {
        if (!currentInput.isEmpty()) {
            if (!currentOperator.isEmpty()) {
                calculateResult();
            }
            currentOperator = operator;
            currentResult = Double.parseDouble(currentInput);
            currentInput = "";
        }
    }

    private void calculateResult() {
        if (!currentInput.isEmpty() && !currentOperator.isEmpty()) {
            double inputNumber = Double.parseDouble(currentInput);
            switch (currentOperator) {
                case "+":
                    currentResult += inputNumber;
                    String result=String.valueOf(currentResult);
                    textView.setText(result);
                    break;
                case "-":
                    currentResult -= inputNumber;
                    String result1=String.valueOf(currentResult);
                    textView.setText(result1);
                    break;
                case "x":
                    currentResult =(currentResult * inputNumber);
                    String result2=String.valueOf(currentResult);
                    textView.setText(result2);
                    break;
                case "/":
                    if (inputNumber != 0) {
                        currentResult =(currentResult/inputNumber);
                        String result3=String.valueOf(currentResult);
                        textView.setText(result3);
                    } else {
                        textView.setText("ERROR");
                    }
                    break;
            }
            updateDisplay(String.valueOf(currentResult));
            currentInput = "";
            currentOperator = "";
        }
    }

    private void appendDigit(String digit) {
        if (currentInput.length() < 10) { // Limit the input length to prevent overflow
            currentInput += digit;
            updateDisplay(currentInput);
        }
    }

    private void updateDisplay(String text) {
        textView.setText(text);
    }

}