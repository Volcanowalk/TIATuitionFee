package com.example.tiatuitionfee

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.tiatuitionfee.databinding.ActivityInternationalFulltimeBinding
import com.example.tiatuitionfee.databinding.ActivityLocalFeeBinding
import java.text.DecimalFormat

class LocalFeeActivity : AppCompatActivity() {

    private var currentTotal : Double = 0.00
    private val feeFulltime : Double = 150.00
    private val feeParttime : Double = 100.00
    private val feeProgram : Double = 12000.00
    private val feeCourses : Double = 1500.00

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityLocalFeeBinding.inflate(layoutInflater)

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

        binding.chkRegistrationFulltime.setOnCheckedChangeListener { _, isChecked ->
            var txtColor : Int
            if (isChecked) { //Add
                currentTotal += feeFulltime
                txtColor = Color.GRAY
            } else {//Subtract
                currentTotal -= feeFulltime
                txtColor = Color.BLACK
            }

            binding.chkRegistrationParttime.isEnabled = !isChecked
            binding.chkRegistrationParttime.setTextColor(txtColor)
            binding.feeParttime.setTextColor(txtColor)
            updateTotal(currentTotal, binding)
        }

        binding.chkRegistrationParttime.setOnCheckedChangeListener { _, isChecked ->

            var txtColor : Int

            if (isChecked) { //Add
                currentTotal += feeParttime
                txtColor = Color.GRAY
            } else {//Subtract
                currentTotal -= feeParttime
                txtColor = Color.BLACK
            }


            binding.chkRegistrationFulltime.isEnabled = !isChecked
            binding.chkRegistrationFulltime.setTextColor(txtColor)
            binding.feeFulltime.setTextColor(txtColor)
            updateTotal(currentTotal, binding)
        }

        binding.chkProgram.setOnCheckedChangeListener { buttonView, isChecked ->

            if(isChecked) { //Add
                currentTotal += feeProgram
            } else {//Subtract
                currentTotal -= feeProgram
            }

            updateTotal(currentTotal, binding)

        }

        binding.chkAdditional.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                try {
                    val numCourses : Int = binding.numAdditionalCourses.text.toString().toInt()
                    currentTotal += numCourses * feeCourses
                    binding.numAdditionalCourses.isEnabled = false
                } catch (e : Exception) {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("INFORMATION")
                        .setMessage("Please type the number of additional courses first and tap " +
                                "this checkbox. Thank you.")
                        .setPositiveButton("OK") { _: DialogInterface, _: Int -> }
                        .show()
                    binding.chkAdditional.isChecked = false
                }
            } else {
                val numCourses : Int = binding.numAdditionalCourses.text.toString().toInt()
                currentTotal -= numCourses * feeCourses
                binding.numAdditionalCourses.isEnabled = true
            }
            updateTotal(currentTotal, binding)
        }

    }

    private fun updateTotal(currentTotal : Double, binding : ActivityLocalFeeBinding) {
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