package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView questionTextView, scoreTextView;
    private RadioGroup optionsRadioGroup;
    private Button submitButton;
    private String[] questions;
    private String[][] options;
    private String[] answers;
    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.questionTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        submitButton = findViewById(R.id.submitButton);

        questions = getResources().getStringArray(R.array.questions);
        options = new String[questions.length][10];
        options[0] = getResources().getStringArray(R.array.question1_options);
        options[1] = getResources().getStringArray(R.array.question2_options);
        options[2] = getResources().getStringArray(R.array.question3_options);
        options[3] = getResources().getStringArray(R.array.question4_options);
        options[4] = getResources().getStringArray(R.array.question5_options);


        answers = getResources().getStringArray(R.array.answers);

        displayQuestion();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedOptionId = optionsRadioGroup.getCheckedRadioButtonId();
                if (selectedOptionId != -1) {
                    RadioButton selectedOption = findViewById(selectedOptionId);
                    String selectedAnswer = selectedOption.getText().toString();
                    if (selectedAnswer.equals(answers[currentQuestionIndex])) {
                        score++;
                    }
                    currentQuestionIndex++;
                    if (currentQuestionIndex < questions.length) {
                        displayQuestion();
                    } else {
                        Toast.makeText(MainActivity.this, "Your Quiz Score: " + score, Toast.LENGTH_SHORT).show();
                    }
                    scoreTextView.setText("Score: " + score);
                    optionsRadioGroup.clearCheck();
                } else {
                    Toast.makeText(MainActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void displayQuestion() {
        questionTextView.setText(questions[currentQuestionIndex]);
        for (int i = 0; i < options[currentQuestionIndex].length; i++) {
            RadioButton radioButton = (RadioButton) optionsRadioGroup.getChildAt(i);
            radioButton.setText(options[currentQuestionIndex][i]);
        }
    }
}
