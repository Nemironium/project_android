package io.nemiron.meetgo.view.new_task

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import io.nemiron.meetgo.R
import io.nemiron.meetgo.databinding.FragmentNewTaskBinding

class NewTaskFragment : Fragment(R.layout.fragment_new_task) {

    private val binding: FragmentNewTaskBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
