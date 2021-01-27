package com.alok.codeassignment.utils

/**
 * Created by Alok Soni on 27/01/21.
 */
class ValidationUtil{
    companion object{
        fun isUserDataValid(fName: String, lName: String, dob: String): String{
            if(fName == null || fName.trim().isEmpty()){
                return "First Name cannot be empty";
            }else if(lName == null || lName.trim().isEmpty()){
                return "Last Name cannot be empty";
            }else if(dob == null || dob.trim().isEmpty()){
                return "Please select the date of birth";
            }
            return ""
        }
    }
}