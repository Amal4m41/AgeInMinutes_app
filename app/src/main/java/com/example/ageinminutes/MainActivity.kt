package com.example.ageinminutes

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View  //a view is something which is displayed on screen... like an element
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.ageinminutes.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    // enable viewBinding in gradle :
//    buildFeatures{
//        viewBinding true
//    }
    // and the add the next line of code.
    private lateinit var binding: ActivityMainBinding      //now there's a binding/connection with the activity_main.xml,
    // using binding we can directly access the ui components using the id.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)   //making the layout visible for us to access.
        setContentView(binding.root)

        //testing the button, context:this cuz mainactivity is where the toast should be displayed and toast.length_short i.e display for a short time.
//        binding.btnDatePicker.setOnClickListener {
//            Toast.makeText(this,"Button works!",Toast.LENGTH_SHORT).show() }

        binding.btnDatePicker.setOnClickListener {view ->
            clickDatePicker(view)
        }



    }


    fun clickDatePicker(view: View){

        //getting the present day year,month and day of month
        val myCalendar= Calendar.getInstance()
        val year_set= myCalendar.get(Calendar.YEAR)
        val month_set=myCalendar.get(Calendar.MONTH)
        val day_set=myCalendar.get(Calendar.DAY_OF_MONTH)

        //dialog is like a pop up, we want to pop up a dialog which is a date picker(pops up a calendar to pick date)
        DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener {
                    view, selectedYear, selectedMonth, selectedDay ->          //this year,month,dayOfMonth is the value we get from the datepickerdialog.
                    Toast.makeText(this,"The chosen year is $selectedYear, month is ${selectedMonth+1}, dayOfMonth is $selectedDay",Toast.LENGTH_SHORT).show()    //the code to be executed when the ok button is clicked.(month is kotlin starts from 0)

                    val selectedDate:String="$selectedDay/${selectedMonth+1}/$selectedYear"
//                    binding.tvSelectedDate.text=selectedDate //OR
                    binding.tvSelectedDate.setText(selectedDate)

                    //Converting the dates in string to date objects
                    val sdf=SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)  //dateformat common to english language
                    val birthDay=sdf.parse(selectedDate)  //theDate is of type Date object.
                    val tdayDate=sdf.parse("$day_set/${month_set+1}/$year_set")  //converting todays date to date object

                    //updating the age in minutes text
                    val ageInMinutes=(tdayDate.time-birthDay.time)/60000
                    binding.tvAgeInMinutes.text=ageInMinutes.toString()

                    //updating the age in days text
                    val ageInDays=(tdayDate.time-birthDay.time)/(3600000*24)
                    binding.tvAgeInDays.text=ageInDays.toString()

                },year_set,month_set,day_set).show()          //this year,month,day is the value to be set when the datepickerdialog is opened.
    }





}