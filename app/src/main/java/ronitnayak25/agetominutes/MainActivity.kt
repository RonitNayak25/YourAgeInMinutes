package ronitnayak25.agetominutes

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.btnDatePicker)
        button.setOnClickListener { view ->
            clickDatePicker(view)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun clickDatePicker(view: View) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                    Toast.makeText(this, "The chosen Date is $selectedDayOfMonth/$selectedMonth/$selectedYear", Toast.LENGTH_LONG).show()
                    val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                    val tvSelectedDate: TextView = findViewById(R.id.tvSelectedDate)
                    tvSelectedDate.text = selectedDate
                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                    val theDate = sdf.parse(selectedDate)
                    val selectedDateToMinutes = theDate!!.time / 60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    val currentDateToMinutes = currentDate!!.time / 60000
                    val differenceInMinutes = currentDateToMinutes - selectedDateToMinutes
                    val tvSelectedDateInMinutes: TextView = findViewById(R.id.tvSelectedDateInMinutes)
                    tvSelectedDateInMinutes.text = differenceInMinutes.toString()
                },
                year,
                month,
                day
        )
        dpd.show()
    }
}