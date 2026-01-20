package com.example.newsapp.ui.newsapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textInputEditTextEmail.doOnTextChanged{ text, start, before, count ->
            if (text!!.length>20){
                binding.textInputLayoutEmail.error = "No more!"
            }else if (text.length < 16){
                binding.textInputLayoutEmail.error = null
            }
        }
        binding.textInputEditTextPassword.doOnTextChanged { text, start, before, count ->
            if (text!!.length>8){
                binding.textInputLayoutPassword.error = "greater than 8"
            }else if(text.length<=8){
                binding.textInputLayoutPassword.error = null
            }
        }
        binding.loginTxt.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
        val auth = FirebaseAuth.getInstance()

        binding.appCompatButton.setOnClickListener {
            val email = binding.textInputEditTextEmail.text.toString().trim()
            val password = binding.textInputEditTextPassword.text.toString().trim()
            Toast.makeText(context,"Success!", Toast.LENGTH_SHORT).show()
            auth.createUserWithEmailAndPassword(email,password)
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }

    }

}