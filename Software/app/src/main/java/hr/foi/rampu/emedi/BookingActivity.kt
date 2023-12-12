package hr.foi.rampu.emedi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import hr.foi.rampu.emedi.database.AppDatabase
import hr.foi.rampu.emedi.database.BookingReasonsDAO
import hr.foi.rampu.emedi.entities.BookingReason
import hr.foi.rampu.emedi.entities.Doctor
import hr.foi.rampu.emedi.helpers.UserSession
import org.w3c.dom.Text

class BookingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)
        val warning = findViewById<TextView>(R.id.tv_warning)

        val btnSendBooking: Button = findViewById(R.id.btn_sendBooking)
        btnSendBooking.setOnClickListener {
            val symptoms = findViewById<EditText>(R.id.et_symptoms).text.toString()
            val duration = findViewById<EditText>(R.id.et_duration).text.toString()
            val history = findViewById<EditText>(R.id.et_history).text.toString()
            val urgency = findViewById<EditText>(R.id.et_urgency).text.toString()
            val additional = findViewById<EditText>(R.id.et_additional_info).text.toString()

            val warning = findViewById<TextView>(R.id.tv_warning)

            if (symptoms.isNotEmpty() && duration.isNotEmpty() && history.isNotEmpty()
                && urgency.isNotEmpty()
            ) {
                val newBookingReason = intent.getParcelableExtra<Doctor>("doctor")?.let {
                    BookingReason(
                        id = 0,
                        symptoms = symptoms,
                        duration = duration,
                        history = history,
                        urgency = urgency,
                        additional = additional,
                        doctorId = it.id,
                        userId = UserSession.loggedUser.id
                    )
                }
                if (newBookingReason != null) {
                    Log.i("COUNT", "Stvorio")
                    AppDatabase.getInstance().getBookingReasonsDao()
                        .insertBookingReason(newBookingReason)
                }
                Log.i("COUNT", "$newBookingReason")
                finish()
            }else{
                warning.visibility = View.VISIBLE
            }
        }


        //AppDatabase.getInstance().getBookingReasonsDao().deleteAllBookingReasons()

        val count = AppDatabase.getInstance().getBookingReasonsDao().getBookingReasonCount()
        Log.i("COUNT", "$count")

        var list = AppDatabase.getInstance().getBookingReasonsDao().getAllBookingReasons()
        for(l in list){
            Log.i("COUNT", "$l")
        }
    }
}