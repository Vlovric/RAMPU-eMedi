package hr.foi.rampu.emedi

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import android.os.Handler
import android.os.Looper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import hr.foi.rampu.emedi.database.AppDatabase
import hr.foi.rampu.emedi.entities.BookingReason
import hr.foi.rampu.emedi.entities.Doctor
import hr.foi.rampu.emedi.helpers.UserSession

class BookingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)
        val warning = findViewById<TextView>(R.id.tv_warning)
        val doctorName = intent.getParcelableExtra<Doctor>("doctor")?.name
        val doctorSurname = intent.getParcelableExtra<Doctor>("doctor")?.surname


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
                        id = getNewBookingReasonId(),
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

                Handler(Looper.getMainLooper()).postDelayed({
                    showNotification(this, "$doctorName $doctorSurname has accepted the reason for you visit.")
                }, 5000)

                finish()
            }else{
                warning.visibility = View.VISIBLE
            }
        }
        //AppDatabase.getInstance().getBookingReasonsDao().deleteAllBookingReasons()

        val count = AppDatabase.getInstance().getBookingReasonsDao().getBookingReasonCount()
        Log.i("COUNT", "$count")

        val list = AppDatabase.getInstance().getBookingReasonsDao().getAllBookingReasons()
        for(l in list){
            Log.i("COUNT", "$l")
        }
    }

    fun getNewBookingReasonId(): Int {
        val bookingReasonsDAO = AppDatabase.getInstance().getBookingReasonsDao()
        val allBookingReasons = bookingReasonsDAO.getAllBookingReasons()
        var newId = allBookingReasons.count()

        for (bookingReason in allBookingReasons) {
            if (bookingReason.id > newId) {
                newId = bookingReason.id
            }
        }

        return ++newId
    }

    fun showNotification(context: Context, message: String) {
        val channelId = "default_channel_id"
        val channelName = "Default Channel"
        val notificationId = 1

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.lightColor = Color.BLUE
            channel.lockscreenVisibility = NotificationCompat.VISIBILITY_PRIVATE
            notificationManager.createNotificationChannel(channel)
        }


        val builder = NotificationCompat.Builder(context, channelId)
            .setContentTitle("Accepted!")
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_checkmark)
            .setAutoCancel(true)


        notificationManager.notify(notificationId, builder.build())
    }


}