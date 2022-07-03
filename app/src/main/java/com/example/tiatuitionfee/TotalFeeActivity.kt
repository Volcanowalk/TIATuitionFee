package com.example.tiatuitionfee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiatuitionfee.databinding.ActivityInternationalFulltimeBinding
import com.example.tiatuitionfee.databinding.ActivityTotalFeeBinding
import java.text.DecimalFormat

class TotalFeeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityTotalFeeBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val subtotal : Double = intent.getDoubleExtra("currentTotal", 0.00)
        val tax : Double = subtotal * 0.13
        val total : Double = subtotal + tax

        DecimalFormat("#,###.00").format(subtotal).also {
            binding.txtSubtotal.text = "$$it"
        }

        DecimalFormat("#,###.00").format(tax).also {
            binding.txtTax.text = "$$it"
        }

        DecimalFormat("#,###.00").format(total).also {
            binding.txtTotal.text = "$$it"
        }

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }






    }
}