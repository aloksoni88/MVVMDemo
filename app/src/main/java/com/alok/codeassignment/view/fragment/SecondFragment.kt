package com.alok.codeassignment.view.fragment

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alok.codeassignment.R
import com.alok.codeassignment.viewmodel.CalculateAgeViewModel
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@Suppress("DEPRECATION")
class SecondFragment : Fragment() {
    companion object{
        val TAG = SecondFragment::class.java.simpleName
    }

    private lateinit var viewModel: CalculateAgeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(CalculateAgeViewModel::class.java)

        viewModel.userDataValue.observe(this, Observer {
            Log.d(TAG, "Values observed... ")
            setValues()
        })
    }
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun setValues(){
        Log.d(TAG, "${viewModel.userDataValue.value}")
        firstname.setText("${viewModel.userDataValue.value?.firstName}")
        lastname.setText("${viewModel.userDataValue.value?.lastName}")
        age.setText("${viewModel.userDataValue.value?.dob}")
    }
}