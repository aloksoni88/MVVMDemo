package com.alok.codeassignment.viewmodel

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alok.codeassignment.model.User
import com.alok.codeassignment.utils.UtilityMethods
import java.lang.Integer.parseInt
import java.time.LocalDate
import java.time.Month
import java.time.Period

/**
 * Created by Alok Soni on 27/01/21.
 */
class CalculateAgeViewModel(application: Application) : AndroidViewModel(application) {
    companion object{
        val TAG = CalculateAgeViewModel::class.java.simpleName
    }

    private var userData = MutableLiveData<User>()
    val userDataValue: LiveData<User> = userData

    @RequiresApi(Build.VERSION_CODES.O)
    fun calculateAge(user: User){
        if(user == null){
            userData.value = null
        }
        if(user.dob == null){
            userData.value = user
        }

        val dateArr = user.dob!!.split("/")

        val today = LocalDate.now()
        val birthday: LocalDate = LocalDate.of(parseInt(dateArr[2]), UtilityMethods.getMonth(
            parseInt(dateArr[1])), parseInt(dateArr[0]))

        val date: Period = Period.between(birthday, today)
        val dateStr = "${date.years} years, ${date.months} month, ${date.days} days"
        user.dob = dateStr
        userData.value = user
        Log.d(TAG, dateStr)
    }
}