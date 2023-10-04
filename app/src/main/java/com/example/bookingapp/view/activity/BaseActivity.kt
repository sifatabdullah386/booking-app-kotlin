package com.example.bookingapp.view.activity

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bookingapp.utilities.SessionManager


open class BaseActivity : AppCompatActivity() {

    private var dialog: Dialog? = null
    var sessionManager: SessionManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dialog = Dialog(applicationContext)
        sessionManager = SessionManager(applicationContext)
    }

    fun ShowAlert(msg: String?) {
//        dialog.setContentView(R.layout.custom_alert_dialog)
//        val message = dialog.findViewById(R.id.message_string) as TextView
//        val ok = dialog.findViewById(R.id.ok) as TextView
//        message.text = msg
//        ok.setOnClickListener { dialog.dismiss() }
        dialog?.setTitle("Alert!!!")
        dialog?.show()
    }

    open fun ShowLoading() {
        dialog?.setCancelable(false)
        dialog?.show()
    }

    open fun HideLoading() {
        dialog?.dismiss()
//        progressDialog.dismiss();
    }
}

