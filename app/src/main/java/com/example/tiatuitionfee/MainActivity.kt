package com.example.tiatuitionfee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.tiatuitionfee.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //ViewBinding
        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //International Full-time
        binding.btnIFT.setOnClickListener {
            val intent = Intent(this, InternationalFulltimeActivity::class.java)
            startActivity(intent)
        }

        //International Part-time
        binding.btnIPT.setOnClickListener {
            val intent = Intent(this, InternationalParttimeActivity::class.java)
            startActivity(intent)
        }

        //Local Full-time & Part-time
        binding.btnLFPT.setOnClickListener {
            val intent = Intent(this, LocalFeeActivity::class.java)
            startActivity(intent)
        }

        //ESL / PATHWAY / TEST PREP.
        binding.btnEPT.setOnClickListener {
            val intent = Intent(this, EPTActivity::class.java)
            startActivity(intent)
        }

    }


}