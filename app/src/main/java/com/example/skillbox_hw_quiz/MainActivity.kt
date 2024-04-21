package com.example.skillbox_hw_quiz


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.skillbox_hw_quiz.ui.main.QuizFragment
import com.example.skillbox_hw_quiz.ui.main.ResultFragment
import com.example.skillbox_hw_quiz.ui.main.WelcomeFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

    }


}