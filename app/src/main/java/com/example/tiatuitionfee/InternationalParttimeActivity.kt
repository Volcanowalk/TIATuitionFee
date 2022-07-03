package com.example.tiatuitionfee

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.tiatuitionfee.databinding.ActivityInternationalFulltimeBinding
import com.example.tiatuitionfee.databinding.ActivityInternationalParttimeBinding
import java.text.DecimalFormat

class InternationalParttimeActivity : AppCompatActivity() {

    private var currentTotal : Double = 100.00

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityInternationalParttimeBinding.inflate(layoutInflater)

        setContentView(binding.root)

        note()

        binding.btnBack.setOnClickListener {
            //Back to Main Menu
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, TotalFeeActivity::class.java)
            intent.putExtra("currentTotal", currentTotal)
            startActivity(intent)
        }

        binding.btnNote.setOnClickListener {
            note()
        }

        binding.chkDayCourse.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                try {
                    val numCourses : Int = binding.numDayCourses.text.toString().toInt()
                    currentTotal += numCourses * 2200.00
                    binding.numDayCourses.isEnabled = false
                } catch (e : Exception) {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("INFORMATION")
                        .setMessage("Please type the number of day courses first and tap " +
                                "this checkbox. Thank you.")
                        .setPositiveButton("OK") { _: DialogInterface, _: Int -> }
                        .show()
                    binding.chkDayCourse.isChecked = false
                }
            } else {
                val numCourses : Int = binding.numDayCourses.text.toString().toInt()
                currentTotal -= numCourses * 2200.00
                binding.numDayCourses.isEnabled = true
            }
            updateTotal(currentTotal, binding)
        }

        binding.chkNightCourse.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                try {
                    val numCourses : Int = binding.numNightCourses.text.toString().toInt()
                    currentTotal += numCourses * 1800.00
                    binding.numNightCourses.isEnabled = false
                } catch (e : Exception) {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("INFORMATION")
                        .setMessage("Please type the number of night courses first and tap " +
                                "this checkbox. Thank you.")
                        .setPositiveButton("OK") { _: DialogInterface, _: Int -> }
                        .show()
                    binding.chkNightCourse.isChecked = false
                }
            } else {
                val numCourses : Int = binding.numNightCourses.text.toString().toInt()
                currentTotal -= numCourses * 2200.00
                binding.numNightCourses.isEnabled = true
            }
            updateTotal(currentTotal, binding)
        }

    }


    private fun updateTotal(currentTotal : Double, binding : ActivityInternationalParttimeBinding) {
        DecimalFormat("#,###.00").format(currentTotal).also {
            binding.currentTotal.text = it }
    }

    private fun note() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("NOTE")
            .setMessage("1) All FEES are subject to change without prior notice.\n" +
                    "2) For High School Program, textbooks are not included but rentals are available" +
                    " at CAD $100 deposit per course and 70% will be refunded upon the return of " +
                    "the textbook.\n" +
                    "3) All rented books must be returned upon completion of the course.\n\n" +
                    "** You can access this note again by clicking the 'NOTE' button **")
            .setPositiveButton("OK") { _: DialogInterface, _: Int -> }
            .show()
    }
}