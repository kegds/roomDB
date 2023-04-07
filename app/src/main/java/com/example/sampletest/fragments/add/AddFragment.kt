package com.example.sampletest.fragments.add


import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sampletest.R
import com.example.sampletest.data.User
import com.example.sampletest.data.UserViewModel
import com.example.sampletest.databinding.FragmentAddBinding


class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAddBinding.inflate(inflater, container, false)

        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        binding.addBtn.setOnClickListener {
            insertDataToDatabase()
        }

        return binding.root
    }

    private fun insertDataToDatabase() {
        val firstName = view?.findViewById<TextView>(R.id.firstName)?.text.toString()
        val lastName = view?.findViewById<TextView>(R.id.lastName)?.text.toString()
        val age = view?.findViewById<TextView>(R.id.age)?.text
        val gender = view?.findViewById<TextView>(R.id.gender)?.text.toString()
        val city = view?.findViewById<TextView>(R.id.city)?.text.toString()

        if (inputCheck(firstName, lastName, age as Editable)) {
            val user = User(0, firstName, lastName, Integer.parseInt(age.toString()), gender, city)
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG)
                .show()
        }
    }


    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }
}

