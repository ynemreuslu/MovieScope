package com.example.moviescope.screens.auth.login

import androidx.databinding.InverseMethod
import java.util.IllegalFormatException

object Converter {
    @InverseMethod(value = "toStr")
    fun toInt(str: String) : Int
    {
        var result = 0

        try {
            result = str.toInt()
        }
        catch (ignore: NumberFormatException) {

        }
        return result
    }

    fun toStr(value: Int) = value.toString()
}