package com.example.skillbox_hw_quiz.ui.main

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.skillbox_hw_quiz.R
import com.example.skillbox_hw_quiz.databinding.FragmentQuizBinding
import com.example.skillbox_hw_quiz.quiz.QuizStorage
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"


class QuizFragment : Fragment() {

    val locale = Locale.getDefault().getLanguage()
    val quizlet = QuizStorage.getQuiz(
        if (locale == "ru") QuizStorage.Locale.Ru
        else QuizStorage.Locale.En
    )

    private var param1: String? = null
    private var param2: String? = null
    private var param3: String? = null

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param3 = it.getString(ARG_PARAM3)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizBinding.inflate(inflater)

        options()

        binding.buttonSend.setOnClickListener {


            val bundle = Bundle().apply {
                if (binding.checkBox1AnswerOne.isChecked) {
                    putString("param1", binding.text1AnswerOne.text.toString())
                } else if (binding.checkBox2AnswerOne.isChecked) {
                    putString("param1", binding.text2AnswerOne.text.toString())
                } else if (binding.checkBox3AnswerOne.isChecked) {
                    putString("param1", binding.text3AnswerOne.text.toString())
                } else if (binding.checkBox4AnswerOne.isChecked) {
                    putString("param1", binding.text4AnswerOne.text.toString())
                } else {
                    putString("param1", getString(R.string.not_answer))
                }

                if (binding.checkBox1AnswerTwo.isChecked) {
                    putString("param2", binding.text1AnswerTwo.text.toString())
                } else if (binding.checkBox2AnswerTwo.isChecked) {
                    putString("param2", binding.text2AnswerTwo.text.toString())
                } else if (binding.checkBox3AnswerTwo.isChecked) {
                    putString("param2", binding.text3AnswerTwo.text.toString())
                } else if (binding.checkBox4AnswerTwo.isChecked) {
                    putString("param2", binding.text4AnswerTwo.text.toString())
                } else {
                    putString("param2", getString(R.string.not_answer))
                }

                if (binding.checkBox1AnswerThree.isChecked) {
                    putString("param3", binding.text1AnswerThree.text.toString())
                } else if (binding.checkBox2AnswerThree.isChecked) {
                    putString("param3", binding.text2AnswerThree.text.toString())
                } else if (binding.checkBox3AnswerThree.isChecked) {
                    putString("param3", binding.text3AnswerThree.text.toString())
                } else if (binding.checkBox4AnswerThree.isChecked) {
                    putString("param3", binding.text4AnswerThree.text.toString())
                } else {
                    putString("param3", getString(R.string.not_answer))
                }
            }
            findNavController().navigate(R.id.action_quizFragment_to_resultFragment, bundle)
        }


        binding.buttonBack.setOnClickListener {
            parentFragmentManager.popBackStack()
            findNavController().navigate(R.id.action_quizFragment_to_welcomeFragment)
        }

        ObjectAnimator.ofFloat(
            binding.textQuestionOne,
            View.ALPHA, 0.0f, 1.0f
        ).apply {
            duration = 1000
            start()
        }

        ObjectAnimator.ofFloat(
            binding.textQuestionTwo,
            View.ALPHA, 0.0f, 1.0f
        ).apply {
            duration = 1000
            start()
        }

        ObjectAnimator.ofFloat(
            binding.textQuestionThree,
            View.ALPHA, 0.0f, 1.0f
        ).apply {
            duration = 1000
            start()
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun options() {
        binding.textQuestionOne.text = quizlet.questions[0].question
        binding.text1AnswerOne.text = quizlet.questions[0].answers[0]
        binding.text2AnswerOne.text = quizlet.questions[0].answers[1]
        binding.text3AnswerOne.text = quizlet.questions[0].answers[2]
        binding.text4AnswerOne.text = quizlet.questions[0].answers[3]

        binding.textQuestionTwo.text = quizlet.questions[1].question
        binding.text1AnswerTwo.text = quizlet.questions[1].answers[0]
        binding.text2AnswerTwo.text = quizlet.questions[1].answers[1]
        binding.text3AnswerTwo.text = quizlet.questions[1].answers[2]
        binding.text4AnswerTwo.text = quizlet.questions[1].answers[3]

        binding.textQuestionThree.text = quizlet.questions[2].question
        binding.text1AnswerThree.text = quizlet.questions[2].answers[0]
        binding.text2AnswerThree.text = quizlet.questions[2].answers[1]
        binding.text3AnswerThree.text = quizlet.questions[2].answers[2]
        binding.text4AnswerThree.text = quizlet.questions[2].answers[3]
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String, param3: String) =
            QuizFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString(ARG_PARAM3, param3)
                }
            }
    }
}