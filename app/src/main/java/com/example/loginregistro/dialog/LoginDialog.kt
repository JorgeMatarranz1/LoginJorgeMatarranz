package com.example.loginregistro.dialog

import android.app.ActionBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.loginregistro.MainActivity
import com.example.loginregistro.R
import com.example.loginregistro.model.User
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

class LoginDialog:DialogFragment() {

    lateinit var usuario:User
    lateinit var passwd : String
    lateinit var usr : String

    lateinit var mainActivity:MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var viewDialog = inflater.inflate(R.layout.login_dialog, container, false)

        var botonLogin2 = viewDialog.findViewById<Button>(R.id.buttonAlta)
        botonLogin2.setOnClickListener { view ->login(view) }
        return viewDialog
    }

    override fun onStart() {
        super.onStart()

        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog!!.window?.setLayout(width, ActionBar.LayoutParams.WRAP_CONTENT)
    }

    fun login(view: View){
        var et_user = dialog!!.findViewById<EditText>(R.id.editTextTextNombre)
        var et_pwd = dialog!!.findViewById<EditText>(R.id.editTextPwd)

        var user = et_user.text.toString()
        var pwd = et_pwd.text.toString()

        if (user.equals(usr) && pwd.equals(passwd)){
            mainActivity.activarBotonInfo()
            dismiss()
        }else{
            Toast.makeText(this.activity, "Usuario/contraseña incorrectos", Toast.LENGTH_LONG).show()
        }
    }
}