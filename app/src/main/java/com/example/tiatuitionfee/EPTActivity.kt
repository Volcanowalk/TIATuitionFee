package com.example.tiatuitionfee

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.tiatuitionfee.databinding.ActivityEptactivityBinding
import com.example.tiatuitionfee.databinding.ActivityLocalFeeBinding
import java.text.DecimalFormat

class EPTActivity : AppCompatActivity() {

    private var currentTotal : Double = 0.00
    private val feePathway : Double = 350.00
    private val feeCertificates : Double = 100.00
    private val feeCertificatesOption1 : Double = 3300.00
    private val feeCertificatesOption2 : Double = 6600.00
    private val feeCertificatesOption3 : Double = 9900.00
    private val feeProgram : Double = 17600.00
    private val feePrepCourses : Double = 2400.00


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityEptactivityBinding.inflate(layoutInflater)

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

        //Registration
        binding.chkPathwayProgram.setOnCheckedChangeListener { _, isChecked ->
            var txtColor : Int
            if (isChecked) { //Add
                currentTotal += feePathway
                txtColor = Color.GRAY
            } else {//Subtract
                currentTotal -= feePathway
                txtColor = Color.BLACK
            }

            binding.chkCertificates.isEnabled = !isChecked
            binding.chkCertificates.setTextColor(txtColor)
            binding.feeCertificates.setTextColor(txtColor)
            updateTotal(currentTotal, binding)
        }

        binding.chkCertificates.setOnCheckedChangeListener { _, isChecked ->

            var txtColor : Int

            if (isChecked) { //Add
                currentTotal += feeCertificates
                txtColor = Color.GRAY
            } else {//Subtract
                currentTotal -= feeCertificates
                txtColor = Color.BLACK
            }


            binding.chkPathwayProgram.isEnabled = !isChecked
            binding.chkPathwayProgram.setTextColor(txtColor)
            binding.feePathwayProgram.setTextColor(txtColor)
            updateTotal(currentTotal, binding)
        }

        //Certificates courses
        binding.chkWeeks1.setOnCheckedChangeListener { _, isChecked ->
            var txtColor : Int
            if (isChecked) { //Add
                currentTotal += feeCertificatesOption1
                txtColor = Color.GRAY
            } else {//Subtract
                currentTotal -= feeCertificatesOption1
                txtColor = Color.BLACK
            }

            binding.chkWeeks2.isEnabled = !isChecked
            binding.chkWeeks2.setTextColor(txtColor)
            binding.feeWeeks2.setTextColor(txtColor)
            binding.chkWeeks3.isEnabled = !isChecked
            binding.chkWeeks3.setTextColor(txtColor)
            binding.feeWeekS3.setTextColor(txtColor)
            updateTotal(currentTotal, binding)
        }

        binding.chkWeeks2.setOnCheckedChangeListener { _, isChecked ->
            var txtColor : Int
            if (isChecked) { //Add
                currentTotal += feeCertificatesOption2
                txtColor = Color.GRAY
            } else {//Subtract
                currentTotal -= feeCertificatesOption2
                txtColor = Color.BLACK
            }

            binding.chkWeeks1.isEnabled = !isChecked
            binding.chkWeeks1.setTextColor(txtColor)
            binding.feeWeeks1.setTextColor(txtColor)
            binding.chkWeeks3.isEnabled = !isChecked
            binding.chkWeeks3.setTextColor(txtColor)
            binding.feeWeekS3.setTextColor(txtColor)
            updateTotal(currentTotal, binding)
        }

        binding.chkWeeks3.setOnCheckedChangeListener { _, isChecked ->
            var txtColor : Int
            if (isChecked) { //Add
                currentTotal += feeCertificatesOption3
                txtColor = Color.GRAY
            } else {//Subtract
                currentTotal -= feeCertificatesOption3
                txtColor = Color.BLACK
            }

            binding.chkWeeks1.isEnabled = !isChecked
            binding.chkWeeks1.setTextColor(txtColor)
            binding.feeWeeks1.setTextColor(txtColor)
            binding.chkWeeks2.isEnabled = !isChecked
            binding.chkWeeks2.setTextColor(txtColor)
            binding.feeWeeks2.setTextColor(txtColor)
            updateTotal(currentTotal, binding)
        }

        //University Pathway Program
        binding.chkProgram.setOnCheckedChangeListener { buttonView, isChecked ->

            if(isChecked) { //Add
                currentTotal += feeProgram
            } else {//Subtract
                currentTotal -= feeProgram
            }

            updateTotal(currentTotal, binding)

        }

        binding.chkBeginner.setOnCheckedChangeListener { _, isChecked ->
            var txtColor : Int
            if (isChecked) { //Add
                currentTotal += feePrepCourses
                txtColor = Color.GRAY
            } else {//Subtract
                currentTotal -= feePrepCourses
                txtColor = Color.BLACK
            }

            binding.chkIntermediate.isEnabled = !isChecked
            binding.chkIntermediate.setTextColor(txtColor)
            binding.feeIntermediate.setTextColor(txtColor)
            binding.chkAdvanced.isEnabled = !isChecked
            binding.chkAdvanced.setTextColor(txtColor)
            binding.feeAdvanced.setTextColor(txtColor)
            updateTotal(currentTotal, binding)
        }

        binding.chkIntermediate.setOnCheckedChangeListener { _, isChecked ->
            var txtColor : Int
            if (isChecked) { //Add
                currentTotal += feePrepCourses
                txtColor = Color.GRAY
            } else {//Subtract
                currentTotal -= feePrepCourses
                txtColor = Color.BLACK
            }

            binding.chkBeginner.isEnabled = !isChecked
            binding.chkBeginner.setTextColor(txtColor)
            binding.feeBeginner.setTextColor(txtColor)
            binding.chkAdvanced.isEnabled = !isChecked
            binding.chkAdvanced.setTextColor(txtColor)
            binding.feeAdvanced.setTextColor(txtColor)
            updateTotal(currentTotal, binding)
        }

        binding.chkAdvanced.setOnCheckedChangeListener { _, isChecked ->
            var txtColor : Int
            if (isChecked) { //Add
                currentTotal += feePrepCourses
                txtColor = Color.GRAY
            } else {//Subtract
                currentTotal -= feePrepCourses
                txtColor = Color.BLACK
            }

            binding.chkBeginner.isEnabled = !isChecked
            binding.chkBeginner.setTextColor(txtColor)
            binding.feeBeginner.setTextColor(txtColor)
            binding.chkIntermediate.isEnabled = !isChecked
            binding.chkIntermediate.setTextColor(txtColor)
            binding.feeIntermediate.setTextColor(txtColor)
            updateTotal(currentTotal, binding)
        }



    }

    private fun updateTotal(currentTotal : Double, binding : ActivityEptactivityBinding) {
        DecimalFormat("#,###.00").format(currentTotal).also {
            binding.currentTotal.text = it }
    }

    private fun note() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("NOTE")
            .setMessage("1) All FEES are subject to change without prior notice.\n" +
                    "** You can access this note again by clicking the 'NOTE' button **")
            .setPositiveButton("OK") { _: DialogInterface, _: Int -> }
            .show()
    }
}