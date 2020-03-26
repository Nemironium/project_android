package io.nemiron.meetgo.view.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import io.nemiron.meetgo.R

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileViewModel =
            ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        val textView: TextView = root.findViewById(R.id.text_profile)
        profileViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        root.findViewById<Button>(R.id.button).setOnClickListener {
            findNavController().navigate(R.id.action_profile_to_settings)
        }

        root.findViewById<Button>(R.id.button2).setOnClickListener {
            findNavController().navigate(R.id.action_profile_to_changePartner)
        }

        return root
    }
}
