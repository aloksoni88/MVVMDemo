package com.alok.codeassignment.view.fragment

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.alok.codeassignment.R
import com.alok.codeassignment.model.User
import com.alok.codeassignment.utils.ValidationUtil
import com.alok.codeassignment.viewmodel.CalculateAgeViewModel
import com.alok.readmessageapp.utils.snackbar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_first.*
import java.util.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    companion object{
        val TAG = FirstFragment::class.java.simpleName
    }

    private lateinit var viewMode: CalculateAgeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewMode = ViewModelProvider(requireActivity()).get(CalculateAgeViewModel::class.java)
    }
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edittext_dob.keyListener = null
        edittext_dob.setOnTouchListener(OnTouchListener { v, event ->
            val DRAWABLE_LEFT = 0
            val DRAWABLE_TOP = 1
            val DRAWABLE_RIGHT = 2
            val DRAWABLE_BOTTOM = 3
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= edittext_dob.getRight() - edittext_dob.getCompoundDrawables()
                        .get(DRAWABLE_RIGHT).getBounds().width()
                ) {
                    showDatePickerDialog()
                    return@OnTouchListener true
                }
            }
            false
        })

        next_button.setOnClickListener {
            val firstName = edittext_firstname.text.toString();
            val lastName = edittext_lastname.text.toString()
            val dob = edittext_dob.text.toString()
            val validationMsg = ValidationUtil.isUserDataValid(firstName,lastName,dob)
            if(!validationMsg.isEmpty()){
                rootview.snackbar(validationMsg,Snackbar.LENGTH_LONG)
            }else{
                val user = User(firstName, lastName, dob)
                viewMode.calculateAge(user)
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }

        }
    }

    fun showDatePickerDialog(){
        Log.d(TAG, "showDatePickerDialog: ")
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)



        val dpd = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

            var strBuilder = StringBuilder()
            strBuilder.append(dayOfMonth)
            strBuilder.append("/")
            strBuilder.append(monthOfYear+1)
            strBuilder.append("/")
            strBuilder.append(year)
            edittext_dob.setText(strBuilder.toString())

        }, year, month, day)

        dpd.show()
    }
}