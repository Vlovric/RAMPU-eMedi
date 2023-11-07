package hr.foi.rampu.emedi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hr.foi.rampu.emedi.R
import hr.foi.rampu.emedi.entities.Doctor

class DoctorsAdapter(private val doctorsList : List<Doctor>) : RecyclerView.Adapter<DoctorsAdapter.TaskViewHolder>() {
    //doctor_list.xml je sta se sve prikazuje za svakog doktora u listi, tamo doradit kak se sta prikazuje i ovdje bindat to iz mock/baze
    inner class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val doctorNameSurname : TextView
        init{ //koji sve elementi postoje za koje prikazujemo
            doctorNameSurname = view.findViewById(R.id.tv_doctor_name)
        }
        fun bind(doctor: Doctor){ //ovdje ide koji se podatci bindaju na element koji se prikazuje
            doctorNameSurname.text = doctor.name + " " + doctor.surname
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val doctorView = LayoutInflater.from(parent.context).inflate(R.layout.doctor_list, parent, false)
        return TaskViewHolder(doctorView)
    }

    override fun getItemCount() = doctorsList.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(doctorsList[position])
    }
}