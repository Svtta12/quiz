package com.example.skillbox_hw_quiz.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.navigation.fragment.findNavController
import com.example.skillbox_hw_quiz.R
import com.example.skillbox_hw_quiz.databinding.FragmentWelcomeBinding
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class WelcomeFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWelcomeBinding.inflate(inflater)

        binding.startButton.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_quizFragment2)
        }

        binding.dateButton.setOnClickListener {

            val constraints = CalendarConstraints.Builder()
                .setOpenAt(calendar.timeInMillis)
                .build()

            val date = MaterialDatePicker.Builder.datePicker()
                .setCalendarConstraints(constraints)
                .setTitleText(resources.getString(R.string.enter_date_of_birth))
                .build()

            date.addOnPositiveButtonClickListener { timeInMillis ->
                calendar.timeInMillis = timeInMillis

                val day = calendar.get(Calendar.DAY_OF_MONTH)
                val monthNumber = calendar.get(Calendar.MONTH)
                val arrayMonth: Array<String> = resources.getStringArray(R.array.month)
                val month = arrayMonth[monthNumber]

                val year = calendar.get(Calendar.YEAR)

                val text = "$day $month $year"
                Snackbar.make(binding.dateButton, text, Snackbar.LENGTH_LONG).show()
            }

            date.show(parentFragmentManager, "DatePicker")
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}