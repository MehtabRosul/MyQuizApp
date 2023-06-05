package com.example.myquizapp;

import static android.app.ProgressDialog.show;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView totalquestionsTextView;
    TextView questionTextView;
    Button  optionA, optionB, optionC, optionD;
    Button submitbtn;

    int score = 0;
    int totalquestion = QuestionAnswer.Question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";
    private String totalQuestion;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        totalquestionsTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);
        submitbtn = findViewById(R.id.submit_btn);


        optionA.setOnClickListener(this);
        optionB.setOnClickListener(this);
        optionC.setOnClickListener(this);
        optionD.setOnClickListener(this);
        submitbtn.setOnClickListener(this);

        totalquestionsTextView.setText("Total Questions :"+totalquestion);

        loadNewQuestion();

    }
            public void onClick (View view) {

               Button clickedButton = (Button) view;
               if (clickedButton.getId()==R.id.submit_btn) {
                   if (selectedAnswer.equals(QuestionAnswer.CorrectAnswer[currentQuestionIndex])) {
                       score++;
                   }
                   currentQuestionIndex++;
                   loadNewQuestion();

               } else{
                     selectedAnswer = clickedButton.getText().toString();
                }
               
        }


    public void loadNewQuestion(){

        if(currentQuestionIndex == totalquestion){
            finishQuiz();
            show(score);
            return;
        }
        questionTextView.setText(QuestionAnswer.Question[currentQuestionIndex]);
        optionA.setText(QuestionAnswer.Choices[currentQuestionIndex][0]);
        optionB.setText(QuestionAnswer.Choices[currentQuestionIndex][1]);
        optionC.setText(QuestionAnswer.Choices[currentQuestionIndex][2]);
        optionD.setText(QuestionAnswer.Choices[currentQuestionIndex][3]);
    }

    private void finishQuiz() {
        String passStatus = "";
        if (score > totalquestion * 60) {
            passStatus = "Passed";
        } else {
            passStatus = " Failed";
        }

        new AlertDialog.Builder(this);
        setTitle(passStatus);
        setMessage("score is:"+ score);
        setCancelable(false);
        show(score);
    }

    private void show(int score) {

    }

    private void setCancelable(boolean b) {
    }

    private void setMessage(String s) {
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
