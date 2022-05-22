package com.example.deals.presentation.task.taskcreate

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.deals.R
import com.example.deals.databinding.FragmentTaskCreateBinding
import com.example.deals.domain.model.Deal
import com.example.deals.domain.model.Task
import com.example.deals.presentation.task.TaskFragmentArgs
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class TaskCreateFragment : Fragment() {

    private val viewModel by viewModels<TaskCreateViewModel>()
    private val binding by viewBinding(FragmentTaskCreateBinding::bind)

    private var deal: Deal? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arg: TaskFragmentArgs by navArgs()
        deal = arg.deal

        init()
    }

    private fun init() {
        binding.btnTaskAdd.setOnClickListener {
            viewModel.createTask(
                task = Task(
                    name = binding.editTextTaskNameCreate.text.toString(),
                    commentary = binding.editTextTaskCommentaryCreate.text.toString(),
                    deadlineDate = binding.tvDealDateCreate.text.toString(),
                    isCompleted = false,
                    dealID = deal!!.id
                )
            )
            view?.findNavController()?.popBackStack()
        }

        binding.layoutTaskDeadlineCreate.setOnClickListener {
// Process to get Current Date
            val  c = Calendar.getInstance();
            val mYear = c.get(Calendar.YEAR);
            val mMonth = c.get(Calendar.MONTH);
            val mDay = c.get(Calendar.DAY_OF_MONTH);

            // Launch Date Picker Dialog
//            val dpd = DatePickerDialog(requireActivity(),
//                 DatePickerDialog . OnDateSetListener () {
//
//                    @Override
//                    public void onDateSet(
//                        DatePicker view, int year,
//                        int monthOfYear, int dayOfMonth
//                    ) {
//                        // Display Selected date in textbox
//                        txtDate.setText(
//                            dayOfMonth + "-"
//                                    + (monthOfYear + 1) + "-" + year
//                        );
//
//                    }
//                }, mYear, mMonth, mDay
//            );
            val dpd = DatePickerDialog(requireContext(), { view, year, monthOfYear, dayOfMonth ->

                var finalMonth: String = monthOfYear.toString()
                var finalDayOfMonth: String = dayOfMonth.toString()
                if (year < year) {
                    finalMonth = mMonth.toString()
                    finalDayOfMonth = mDay.toString()
                }
                if (finalDayOfMonth.length == 1) finalDayOfMonth = "0$finalDayOfMonth"
                if (finalMonth.length == 1) finalMonth = "0$finalMonth"
                binding.tvDealDateCreate.text = "$finalDayOfMonth.$finalMonth.$year"


            }, mYear, mMonth, mDay)
            dpd.show();
        }
}
}