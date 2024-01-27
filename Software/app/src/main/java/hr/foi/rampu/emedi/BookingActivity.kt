package hr.foi.rampu.emedi

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.appcompat.app.AppCompatActivity
import hr.foi.rampu.emedi.database.AppDatabase
import hr.foi.rampu.emedi.entities.BookingReason
import hr.foi.rampu.emedi.entities.Doctor
import hr.foi.rampu.emedi.helpers.TextSizeUtility
import hr.foi.rampu.emedi.helpers.UserSession

class BookingActivity : AppCompatActivity() {
    private lateinit var textSizeUtility: TextSizeUtility
    private lateinit var sharedPreferences: SharedPreferences

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
                    AppDatabase.getInstance().getBookingReasonsDao()
                        .insertBookingReason(newBookingReason)

                    // Get the appointment start time
                    val appointmentStartTime = newBookingReason.appointmentStartTime.time

                    Handler(Looper.getMainLooper()).postDelayed({
                        showNotification(
                            this,
                            "$doctorName $doctorSurname has accepted the reason for your visit."
                        )

                        // Schedule notification 1 hour before the appointment_start_time
                        scheduleNotificationsForAppointments(appointmentStartTime)
                    }, 5000)

                    finish()
                } else {
                    warning.visibility = View.VISIBLE
                }
            } else {
                warning.visibility = View.VISIBLE
            }
        }

        // Your existing code...
    }

    private fun getNewBookingReasonId(): Int {
        val bookingReasonsDAO = AppDatabase.getInstance().getBookingReasonsDao()
        val allBookingReasons = bookingReasonsDAO.getAllBookingReasons()
        return allBookingReasons.size + 1
    }

    private fun showNotification(context: Context, message: String) {
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
        sharedPreferences = getSharedPreferences("Prefs", Context.MODE_PRIVATE)
        changeTextSize()
    }

    private fun scheduleNotificationsForAppointments(appointmentStartTime: Long) {
        val currentTimeMillis = System.currentTimeMillis()
        val notificationTimeMillis = appointmentStartTime - 3600000 // 1 hour in milliseconds

        if (notificationTimeMillis > currentTimeMillis) {
            Handler(Looper.getMainLooper()).postDelayed({
                sendCustomNotification(
                    this,
                    "Appointment Reminder",
                    "Your appointment is in 1 hour."
                )
            }, notificationTimeMillis - currentTimeMillis)
        }
    }

    private fun sendCustomNotification(context: Context, title: String, message: String) {
        val channelId = "custom_channel_id"
        val channelName = "Custom Channel"
        val notificationId = 2 // Use a unique ID for each notification

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.lightColor = Color.GREEN
            channel.lockscreenVisibility = NotificationCompat.VISIBILITY_PRIVATE
            notificationManager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_checkmark)
            .setAutoCancel(true)

        notificationManager.notify(notificationId, builder.build())
    }

    private fun changeTextSize() {
        val position = sharedPreferences.getInt("selectedPosition", 1)
        TextSizeUtility.initialize(this)
        textSizeUtility = TextSizeUtility.getInstance()

        textSizeUtility.registerAllTextViews(findViewById(R.id.tv_warning))
        textSizeUtility.registerTextViewStyle(this, findViewById(R.id.tv_warning), position)

        textSizeUtility.registerAllButtons(findViewById(R.id.btn_sendBooking))
        textSizeUtility.registerButtonStyle(this, findViewById(R.id.btn_sendBooking), position)
    }
}
