package hr.foi.rampu.emedi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hr.foi.rampu.emedi.R
import hr.foi.rampu.emedi.entities.Doctor
import hr.foi.rampu.emedi.entities.Review
import kotlin.math.round

class DoctorsAdapter(private val doctorsList : List<Doctor>, private val clickListener : (Doctor) -> Unit) : RecyclerView.Adapter<DoctorsAdapter.TaskViewHolder>() {
    //doctor_list.xml je sta se sve prikazuje za svakog doktora u listi, tamo doradit kak se sta prikazuje i ovdje bindat to iz mock/baze
    inner class TaskViewHolder(view: View, clickAtPosition : (Int) -> Unit) : RecyclerView.ViewHolder(view){
        private val doctorNameSurname : TextView
        private val doctorSpecialisation : TextView
        private val doctorYearsOfExpiriance : TextView
        private val doctorsReviewScore : TextView
        init{ //koji sve elementi postoje za koje prikazujemo
            doctorNameSurname = view.findViewById(R.id.tv_doctor_name)
            doctorSpecialisation = view.findViewById(R.id.tv_dynamic_specialization)
            doctorYearsOfExpiriance = view.findViewById(R.id.tv_dynamic_years)
            doctorsReviewScore = view.findViewById(R.id.tv_dynamic_review_score)

            view.setOnClickListener {
                clickAtPosition(adapterPosition)
            }
        }
        fun bind(doctor: Doctor){ //ovdje ide koji se podatci bindaju na element koji se prikazuje
            doctorNameSurname.text = doctor.name + " " + doctor.surname
            doctorSpecialisation.text = doctor.specialization
            doctorYearsOfExpiriance.text = "Godine iskustva: " + doctor.yearsEmployed.toString()
            doctorsReviewScore.text = String.format("%.2f", Review.getAverageRatingForDoctor(doctor))

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val doctorView = LayoutInflater.from(parent.context).inflate(R.layout.doctor_list, parent, false)

        return TaskViewHolder(doctorView){
            clickListener(doctorsList[it])
        }
    }

    override fun getItemCount() = doctorsList.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(doctorsList[position])
    }
}