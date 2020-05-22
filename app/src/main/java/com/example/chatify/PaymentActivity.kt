package com.example.chatify


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.payment_interface.*


class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment_interface)
    }


    fun confirmCardInfo(view: View) {
        card_holder_name_input.setText(card_holder_name_input.text.toString().toUpperCase());
        val cardNumber = card_number_input.text.toString()
        val cardName = card_holder_name_input.text.toString()
        val expDate = exp_date_input.text.toString()
        val cvv = cvv_input.text.toString()

        val builder = AlertDialog.Builder(this)
        builder.setPositiveButton("OK", null)

        if (!(cardNumber.length != 16 || cardName.isEmpty() || expDate.isEmpty() || cvv.length < 3)) {
            val rnds = (0..10).random()
            if (rnds < 9) {
                // TODO enable premium features
                builder.setTitle("Payment Successful")
                builder.setMessage("Congratulations, you have upgraded to ChatiFy Premium")
            }
            else {
                builder.setTitle("Payment Failed")
                builder.setMessage("Your card wasn't charged by the bank")
            }
        }
        else {
            builder.setTitle("Invalid Card Info")
            builder.setMessage("Please check your card credentials.")
        }

        builder.show()
    }
}