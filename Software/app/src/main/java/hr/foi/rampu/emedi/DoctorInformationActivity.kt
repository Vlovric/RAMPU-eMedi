package hr.foi.rampu.emedi

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import hr.foi.rampu.emedi.entities.Doctor
import hr.foi.rampu.emedi.entities.User
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DoctorInformationActivity : AppCompatActivity() {
    private var selectedDate: Calendar = Calendar.getInstance()
    private var dateChosen: Boolean = false
    private var selectedStartTime: String = "00:00"
    private var selectedEndTime: String = "00:00"

    private val sdfDate = SimpleDateFormat("dd.MM.yyyy.", Locale.US)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_information)

        val btnCheckReviews: Button = findViewById(R.id.btn_reviews)
        btnCheckReviews.setOnClickListener {
            checkReviews()
        }

        val btnAddAppointment: Button = findViewById(R.id.btn_add_appointment)
        btnAddAppointment.setOnClickListener {
            showNewAppointmentDialog()
        }

        val receivedDoctor = intent.getParcelableExtra<Doctor>("doctor")
        // inicijaliziram textview-ove
        val tvName = findViewById<TextView>(R.id.tv_dynamic_name)
        val tvSurname = findViewById<TextView>(R.id.tv_dynamic_surname)
        val tvSpecialization = findViewById<TextView>(R.id.tv_dynamic_specialization)
        val tvYearsEmployed = findViewById<TextView>(R.id.tv_dynamic_years)
        val tvJobDescription = findViewById<TextView>(R.id.tv_dynamic_job_description)
        val tvClinicName = findViewById<TextView>(R.id.tv_dynamic_clinic_name)
        val tvAddress = findViewById<TextView>(R.id.tv_dynamic_address)
        val tvEmail = findViewById<TextView>(R.id.tv_dynamic_email)
        val tvTelephone = findViewById<TextView>(R.id.tv_dynamic_telephone)

        // učitavam u text-view
        tvName.text = receivedDoctor?.name.orEmpty()
        tvSurname.text = receivedDoctor?.surname.orEmpty()
        tvSpecialization.text = receivedDoctor?.specialization.orEmpty()
        tvYearsEmployed.text = "${receivedDoctor?.yearsEmployed ?: 0}"
        tvJobDescription.text = receivedDoctor?.jobDescription.orEmpty()
        tvClinicName.text = receivedDoctor?.clinicName.orEmpty()
        tvAddress.text = receivedDoctor?.address.orEmpty()
        tvEmail.text = receivedDoctor?.email.orEmpty()
        tvTelephone.text = receivedDoctor?.telephone.orEmpty()
    }

    private fun showNewAppointmentDialog() {
        val newAppointmentDialog = LayoutInflater
            .from(this)
            .inflate(R.layout.new_appointment_dialog, null)

        selectedDate = Calendar.getInstance()
        dateChosen = false
        selectedStartTime = "00:00"
        selectedEndTime = "00:00"

        AlertDialog.Builder(this)
            .setView(newAppointmentDialog)
            .setPositiveButton(getString(R.string.save_appointment_time)) { _, _ ->
                if (!dateChosen) {
                    Toast.makeText(this, getString(R.string.date_is_not_chosen), Toast.LENGTH_SHORT).show()
                } else if (selectedStartTime == "00:00" || selectedEndTime == "00:00") {
                    Toast.makeText(this, getString(R.string.time_is_not_chosen), Toast.LENGTH_SHORT).show()
                } else {
                    Log.i("NOVO", "Ovo implementiraj!")
                    // Implementirati dodavanje novog appointment u bazu!
                }
            }
            .setNegativeButton(getString(R.string.close)) { dialog, which ->
                dialog.dismiss()
            }
            .show()

        activateDateTimeListeners(newAppointmentDialog)
    }

    private fun checkReviews() {
        val receivedDoctor = intent.getParcelableExtra<Doctor>("doctor")
        val intent = Intent(this, AllReviewsActivity::class.java)
        intent.putExtra("doctor", receivedDoctor)
        startActivity(intent)
    }

    private fun activateDateTimeListeners(view: View) {
        val dateSelection = view.findViewById<EditText>(R.id.et_appointment_date)
        val startTimeSelection = view.findViewById<EditText>(R.id.et_appointment_start_time)
        val endTimeSelection = view.findViewById<EditText>(R.id.et_appointment_end_time)

        dateSelection.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                DatePickerDialog(
                    view.context,
                    { _, year, monthOfYear, dayOfMonth ->
                        selectedDate.set(year, monthOfYear, dayOfMonth)
                        dateSelection.setText(sdfDate.format(selectedDate.time).toString())
                        dateChosen = true
                    },
                    selectedDate.get(Calendar.YEAR),
                    selectedDate.get(Calendar.MONTH),
                    selectedDate.get(Calendar.DAY_OF_MONTH)
                ).show()
                view.clearFocus()
            }
        }

        startTimeSelection.setOnFocusChangeListener {view, hasFocus ->
            if (hasFocus) {
                TimePickerDialog(
                    view.context,
                    { _, hourOfDay, minute ->
                        selectedStartTime = getTimeString(hourOfDay, minute)
                        startTimeSelection.setText(selectedStartTime)

                        selectedEndTime = addToTime(selectedStartTime, 30)
                        endTimeSelection.setText(selectedEndTime)
                    },
                    getStringHour(selectedStartTime),
                    getStringMinute(selectedStartTime),
                    true
                ).show()
                view.clearFocus()
            }
        }

        endTimeSelection.setOnFocusChangeListener {view, hasFocus ->
            if (hasFocus) {
                TimePickerDialog(
                    view.context,
                    { _, hourOfDay, minute ->
                        if (compareTimes(selectedStartTime, getTimeString(hourOfDay, minute)) != 1) {
                            Toast.makeText(view.context,
                                getString(R.string.end_time_must_be_after_start_time), Toast.LENGTH_SHORT).show()
                            selectedEndTime = "00:00"
                            endTimeSelection.setText("")
                        } else {
                            selectedEndTime = getTimeString(hourOfDay, minute)
                            endTimeSelection.setText(selectedEndTime)
                        }
                    },
                    getStringHour(selectedEndTime),
                    getStringMinute(selectedEndTime),
                    true
                ).show()
                view.clearFocus()
            }
        }
    }

    private fun getTimeString(hour: Int, minute: Int): String {
        return String.format("%02d:%02d", hour, minute)
    }

    private fun getStringHour(time: String): Int {
        return time.substring(0, 2).toInt()
    }

    private fun getStringMinute(time: String): Int {
        return time.substring(3, 5).toInt()
    }

    private fun addToTime(time: String, minutesAdd: Int): String {
        var hours: Int = getStringHour(time)
        var minutes: Int = getStringMinute(time)

        minutes += minutesAdd;
        hours += (minutes / 60)
        minutes %= 60
        hours %= 24

        return getTimeString(hours, minutes)
    }

    private fun compareTimes(time1: String, time2: String): Int {
        val hours1: Int = getStringHour(time1)
        val hours2: Int = getStringHour(time2)
        val minutes1: Int = getStringMinute(time1)
        val minutes2: Int = getStringMinute(time2)

        if (hours1 > hours2) {
            return -1
        } else if (hours1 < hours2) {
            return 1
        } else {
            if (minutes1 > minutes2) {
                return -1
            } else if (minutes1 < minutes2) {
                return 1
            } else {
                return 0
            }
        }

        return 0
    }
}
