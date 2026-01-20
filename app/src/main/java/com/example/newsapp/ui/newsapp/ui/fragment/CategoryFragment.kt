package com.example.newsapp.ui.newsapp.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.core.graphics.toColorInt
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentCategoryBinding
import com.example.newsapp.ui.newsapp.domain.repository.Repository
import com.example.newsapp.ui.newsapp.ui.viewmodel.CategoryViewModel
import com.example.newsapp.ui.newsapp.ui.viewmodel.HomeViewModel
import com.example.newsapp.ui.newsapp.ui.viewmodel.MainViewModelFactory

class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupCategoryButton(binding.sportBtn)
        setupCategoryButton(binding.artBtn)
        setupCategoryButton(binding.technologyBtn)
        setupCategoryButton(binding.lifeStyleBtn)
        setupCategoryButton(binding.politicsBtn)
        setupCategoryButton(binding.scienceBtn)
        setupCategoryButton(binding.fashionBtn)
        setupCategoryButton(binding.healthBtn)
        setupCategoryButton(binding.businessBtn)
        setupCategoryButton(binding.nationalBtn)
        setupCategoryButton(binding.internationalBtn)
//        binding.apply {
//            sportBtn.setOnClickListener {
//                val category = sportBtn.text.toString().lowercase()
//                val action = CategoryFragmentDirections.actionCategoryFragment2ToViewCateggoryFragment(category)
//                findNavController().navigate(action)
//
//                sportBtn.setBackgroundColor(resources.getColor(R.color.blue))
//                sportBtn.setTextColor(resources.getColor(R.color.white))
//            }
//            nationalBtn.setOnClickListener {
//                val category = nationalBtn.text.toString().lowercase()
//                val action = CategoryFragmentDirections.actionCategoryFragment2ToViewCateggoryFragment(category)
//                findNavController().navigate(action)
//                nationalBtn.setBackgroundColor(resources.getColor(R.color.blue))
//                nationalBtn.setTextColor(resources.getColor(R.color.white))
//            }
//            internationalBtn.setOnClickListener {
//                val category = internationalBtn.text.toString().lowercase()
//                val action = CategoryFragmentDirections.actionCategoryFragment2ToViewCateggoryFragment(category)
//                findNavController().navigate(action)
//                internationalBtn.setBackgroundColor(resources.getColor(R.color.blue))
//                internationalBtn.setTextColor(resources.getColor(R.color.white))
//            }
//            lifeStyleBtn.setOnClickListener {
//                val category = lifeStyleBtn.text.toString().lowercase()
//                val action = CategoryFragmentDirections.actionCategoryFragment2ToViewCateggoryFragment(category)
//                findNavController().navigate(action)
//                lifeStyleBtn.setBackgroundColor(resources.getColor(R.color.blue))
//                lifeStyleBtn.setTextColor(resources.getColor(R.color.white))
//            }
//            businessBtn.setOnClickListener {
//                val category = businessBtn.text.toString().lowercase()
//                val action = CategoryFragmentDirections.actionCategoryFragment2ToViewCateggoryFragment(category)
//                findNavController().navigate(action)
//                businessBtn.setBackgroundColor(resources.getColor(R.color.blue))
//                businessBtn.setTextColor(resources.getColor(R.color.white))
//            }
//            healthBtn.setOnClickListener {
//                val category = healthBtn.text.toString().lowercase()
//                val action = CategoryFragmentDirections.actionCategoryFragment2ToViewCateggoryFragment(category)
//                findNavController().navigate(action)
//                healthBtn.setBackgroundColor(resources.getColor(R.color.blue))
//                healthBtn.setTextColor(resources.getColor(R.color.white))
//            }
//            fashionBtn.setOnClickListener {
//                val category = fashionBtn.text.toString().lowercase()
//                val action = CategoryFragmentDirections.actionCategoryFragment2ToViewCateggoryFragment(category)
//                findNavController().navigate(action)
//                fashionBtn.setBackgroundColor(resources.getColor(R.color.blue))
//                fashionBtn.setTextColor(resources.getColor(R.color.white))
//            }
//            technologyBtn.setOnClickListener {
//                val category = technologyBtn.text.toString().lowercase()
//                val action = CategoryFragmentDirections.actionCategoryFragment2ToViewCateggoryFragment(category)
//                findNavController().navigate(action)
//                technologyBtn.setBackgroundColor(resources.getColor(R.color.blue))
//                technologyBtn.setTextColor(resources.getColor(R.color.white))
//            }
//            scienceBtn.setOnClickListener {
//                val category = scienceBtn.text.toString().lowercase()
//                val action = CategoryFragmentDirections.actionCategoryFragment2ToViewCateggoryFragment(category)
//                findNavController().navigate(action)
//                scienceBtn.setBackgroundColor(resources.getColor(R.color.blue))
//                scienceBtn.setTextColor(resources.getColor(R.color.white))
//            }
//            artBtn.setOnClickListener {
//                val category = artBtn.text.toString().lowercase()
//                val action = CategoryFragmentDirections.actionCategoryFragment2ToViewCateggoryFragment(category)
//                findNavController().navigate(action)
//                artBtn.setBackgroundColor(resources.getColor(R.color.blue))
//                artBtn.setTextColor(resources.getColor(R.color.white))
//            }
//            politicsBtn.setOnClickListener {
//                val category = politicsBtn.text.toString().lowercase()
//                val action = CategoryFragmentDirections.actionCategoryFragment2ToViewCateggoryFragment(category)
//                findNavController().navigate(action)
//                politicsBtn.setBackgroundColor(resources.getColor(R.color.blue))
//                politicsBtn.setTextColor(resources.getColor(R.color.white))
//            }
//        }

    }

    fun setupCategoryButton(btn: AppCompatButton) {
        btn.setOnClickListener {
            resetAllButtons()
            btn.isSelected = true
            val category = btn.text.toString().lowercase()
//            btn.setBackgroundColor(resources.getColor(R.color.blue))
//            btn.setTextColor(resources.getColor(R.color.white))
            Handler().postDelayed({
                val action =
                    CategoryFragmentDirections.actionCategoryFragment2ToViewCateggoryFragment(
                        category
                    )
                findNavController().navigate(action)
            }, 1000)
        }
    }

    fun resetAllButtons() {
        binding.apply {
            nationalBtn.isSelected = false
            internationalBtn.isSelected = false
            sportBtn.isSelected = false
            lifeStyleBtn.isSelected = false
            businessBtn.isSelected = false
            healthBtn.isSelected = false
            fashionBtn.isSelected = false
            technologyBtn.isSelected = false
            scienceBtn.isSelected = false
            artBtn.isSelected = false
            politicsBtn.isSelected = false
        }
    }
}


