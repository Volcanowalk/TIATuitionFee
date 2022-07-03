package com.example.tiatuitionfee

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AlertDialog
import com.example.tiatuitionfee.databinding.ActivityInternationalFulltimeBinding
import java.text.DecimalFormat

class InternationalFulltimeActivity : AppCompatActivity() {

    private var currentTotal : Double = 350.00

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //View Binding
        val binding = ActivityInternationalFulltimeBinding.inflate(layoutInflater)

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

        binding.numAdditionalCourses.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                binding.chkAdditional.isEnabled = true
            }
        })

        binding.chkProgram.setOnCheckedChangeListener { buttonView, isChecked ->

            if(isChecked) { //Add
                currentTotal += 17600.00
            } else {//Subtract
                currentTotal -= 17600.00
            }

            updateTotal(currentTotal, binding)

        }

        binding.chkAdditional.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                try {
                    val numCourses : Int = binding.numAdditionalCourses.text.toString().toInt()
                    currentTotal += numCourses * 2200.00
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
                currentTotal -= numCourses * 2200.00
                binding.numAdditionalCourses.isEnabled = true
            }
            updateTotal(currentTotal, binding)
        }

        binding.chkHomestayMinimum.setOnCheckedChangeListener { _, isChecked ->

            var colorEnabled : Int
            var colorDetails : Int

            if(isChecked) {

                currentTotal += 6 * 1250.00 + 250.00

                colorEnabled = Color.BLACK
                colorDetails = Color.parseColor("#D50000")

            } else {
                binding.chkPlacementFee.isChecked = false
                binding.chkAirportFee.isChecked = false

                colorEnabled = Color.GRAY
                colorDetails = Color.GRAY

                currentTotal -= 6 * 1250.00 + 250.00

            }

            binding.chkPlacementFee.isChecked = isChecked
            binding.chkAirportFee.isEnabled = isChecked
            binding.chkPlacementFee.setTextColor(colorEnabled)
            binding.chkAirportFee.setTextColor(colorEnabled)
            binding.feePlacement.setTextColor(colorEnabled)
            binding.feeAirportPickup.setTextColor(colorEnabled)
            binding.detailPlacement.setTextColor(colorDetails)
            binding.detailPickup.setTextColor(colorDetails)

            updateTotal(currentTotal, binding)
        }

        binding.chkAirportFee.setOnCheckedChangeListener { _, isChecked ->

            if(isChecked) {
                currentTotal += 150.00
            } else {
                currentTotal -= 150.00
            }

            updateTotal(currentTotal, binding)
        }

        binding.chkAnnualActivity.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                currentTotal += 500.00
            } else {
                currentTotal -= 500.00
            }

            updateTotal(currentTotal, binding)
        }

        binding.chkRentalFee.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                currentTotal += 200.00
            } else {
                currentTotal -= 200.00
            }

            updateTotal(currentTotal, binding)
        }

        binding.chkSecurityFee.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                currentTotal += 500.00
            } else {
                currentTotal -= 500.00
            }

            updateTotal(currentTotal, binding)
        }

        binding.chkHealthInsurance.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                currentTotal += 600.00
            } else {
                currentTotal -= 600.00
            }

            updateTotal(currentTotal, binding)
        }

        binding.chkCustodianship.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                currentTotal += 1700.00
            } else {
                currentTotal -= 1700.00
            }

            updateTotal(currentTotal, binding)
        }

    }

    private fun updateTotal(currentTotal : Double, binding : ActivityInternationalFulltimeBinding) {
        DecimalFormat("#,###.00").format(currentTotal).also {
            binding.currentTotal.text = it }
    }

    private fun note() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("NOTE")
            .setMessage("1) All FEES are subject to change without prior notice.\n" +
                    "2) For High School Program, textbooks are not included but rentals are available." +
                    " All rented books must be returned upon completion of the course.\n" +
                    "3) Homestay fee includes single room, meal plan, and internet access.\n\n" +
                    "** You can access this note again by clicking the 'NOTE' button **")
            .setPositiveButton("OK") { _: DialogInterface, _: Int -> }
            .show()
    }

}