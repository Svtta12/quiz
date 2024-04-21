package com.example.skillbox_hw_quiz.ui.main

import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.navigation.fragment.findNavController
import com.example.skillbox_hw_quiz.R
import com.example.skillbox_hw_quiz.databinding.FragmentResultBinding
import com.example.skillbox_hw_quiz.quiz.QuizStorage
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"


class ResultFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var param3: String? = null

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    val locale = Locale.getDefault().getLanguage()
    val quizlet = QuizStorage.getQuiz(
        if (locale == "ru") QuizStorage.Locale.Ru
        else QuizStorage.Locale.En
    )


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
        _binding = FragmentResultBinding.inflate(inflater)

        binding.buttonAgain.setOnClickListener {
            parentFragmentManager.popBackStack()
            findNavController().navigate(R.id.action_resultFragment_to_quizFragment)
        }

        binding.textOneQuestion.text = quizlet.questions[0].question
        binding.textTwoQuestion.text = quizlet.questions[1].question
        binding.textThreeQuestion.text = quizlet.questions[2].question


        ObjectAnimator.ofArgb(
            binding.buttonAgain,
            "textColor",
            Color.parseColor(getString(R.string.red)),
            Color.parseColor(getString(R.string.blue))
        ).apply {
            duration = 4000
            interpolator = AccelerateDecelerateInterpolator()
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            start()
        }

        ObjectAnimator.ofFloat(
            binding.textResult,
            View.ROTATION_X, 360f
        ).apply {
            duration = 4000
            interpolator = AccelerateDecelerateInterpolator()
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            start()
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textOneAnswer.text = param1
        binding.textTwoAnswer.text = param2
        binding.textThreeAnswer.text = param3

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String, param3: String) =
            ResultFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString(ARG_PARAM3, param3)
                }
            }
    }
}