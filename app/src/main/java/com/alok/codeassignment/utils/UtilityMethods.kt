package com.alok.codeassignment.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Month

/**
 * Created by Alok Soni on 27/01/21.
 */
class UtilityMethods{
    companion object{
        @RequiresApi(Build.VERSION_CODES.O)
        fun getMonth(month: Int): Month {
            if (month == 1) {
                return Month.JANUARY
            } else if (month == 2) {
                return Month.FEBRUARY
            } else if (month == 3) {
                return Month.MARCH
            } else if (month == 4) {
                return Month.APRIL
            } else if (month == 5) {
                return Month.MAY
            } else if (month == 6) {
                return Month.JUNE
            } else if (month == 7) {
                return Month.JULY
            } else if (month == 8) {
                return Month.AUGUST
            } else if (month == 9) {
                return Month.SEPTEMBER
            } else if (month == 10) {
                return Month.OCTOBER
            } else if (month == 11) {
                return Month.NOVEMBER
            } else
                return Month.DECEMBER
        }
    }
}