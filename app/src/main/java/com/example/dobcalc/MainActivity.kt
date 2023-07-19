package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var tvselectedDate: TextView? =null
    private var ageinminutes: TextView? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val butt: Button= findViewById(R.id.button)
        tvselectedDate=findViewById(R.id.SelectedDate)
        ageinminutes=findViewById(R.id.ageinmins)
        butt.setOnClickListener {
            clickdatepicker()
        }


    }
    private fun clickdatepicker(){
        val myCalendar= Calendar.getInstance()
        val year=myCalendar.get(Calendar.YEAR)
        val mon=myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)
                val dpd=DatePickerDialog(this, { _, year, mon, day ->
                    Toast.makeText(this, "Year is $year, Month is $mon, Day of year is $day",
                        Toast.LENGTH_SHORT).show()
                    val selectedday="$day/${mon+1}/$year"
                    tvselectedDate?.text=selectedday

                    val sdf= SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                    val theDate=sdf.parse(selectedday)
                    theDate?.let{
                        val selectedDateinMinutes= theDate.time/60000

                        val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                        currentDate?.let{
                            val currentDateinMinutes=currentDate.time/60000
                            val aim=currentDateinMinutes-selectedDateinMinutes
                            ageinminutes?.text=aim.toString()
                        }

                    }


                },year,mon,day)
        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000



        dpd.show()


    }
}