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
        val cardNumber = card_number_input.text.toString()
        val cardName = card_holder_name_input.text.toString()
        val expDate = exp_date_input.text.toString()
        val cvv = cvv_input.text.toString()



        if (!(cardNumber.length != 16 || cardName.isEmpty() || expDate.isEmpty() || cvv.length < 3)) {
            val rnds = (0..10).random()
            if (rnds < 9) {
                // TODO enable premium features
                val builder = AlertDialog.Builder(this)
                with(builder)
                {
                    setTitle("Payment Successful")
                    setMessage("Congratulations, you have upgraded to ChatiFy Premium")
                    setPositiveButton("OK", null)
                    show()
                }
            }
            else {
                val builder = AlertDialog.Builder(this)
                with(builder)
                {
                    setTitle("Payment Failed")
                    setMessage("Your card wasn't charged by the bank")
                    setPositiveButton("OK", null)
                    show()
                }
            }
        }
        else {
            val builder = AlertDialog.Builder(this)
            with(builder)
            {
                setTitle("Invalid Card Info")
                setMessage("Please check your card credentials.")
                setPositiveButton("OK", null)
                show()
            }
        }
    }
}