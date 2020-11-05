package com.example.loginregistro.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.loginregistro.MainActivity
import com.example.loginregistro.R
import com.example.loginregistro.model.User
import org.json.JSONObject

class Registro : AppCompatActivity() {

    var usuario = User()

    lateinit var et_usr: EditText
    lateinit var et_pwd: EditText
    lateinit var et_nom: EditText
    lateinit var et_ape: EditText

    lateinit var btnCancelar: Button
    lateinit var btnAceptar: Button

    var registro = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        et_usr = findViewById(R.id.editTextTextUsr)
        et_pwd = findViewById(R.id.editTextTextPwd)
        et_nom = findViewById(R.id.editTextTextNombre)
        et_ape = findViewById(R.id.editTextPwd)

        btnCancelar = findViewById(R.id.buttonCancel)
        btnAceptar = findViewById(R.id.buttonAceptar)

        var registro = intent.getBooleanExtra("registro", true)
        if (!registro) {
            var bundle: Bundle? = intent.getBundleExtra("usuario")
            usuario.setBundle(bundle!!)
            et_usr.setText(usuario.usr)
            et_pwd.setText(usuario.pwd)
            et_nom.setText(usuario.name)
            et_ape.setText(usuario.apellido)
            btnAceptar.setText("Actualizar")

        }
    }

    fun aceptar(view: View) {

        if (registro) {

            var usuario = User()
            usuario.usr = et_usr.text.toString()
            usuario.pwd = et_pwd.text.toString()
            usuario.name = et_nom.text.toString()
            usuario.apellido = et_ape.text.toString()

            var resultIntent = Intent(this, MainActivity::class.java)
            resultIntent.putExtra("usuario", usuario.getBundle())

            setResult(Activity.RESULT_OK, resultIntent)
            val obj = JSONObject()
            obj.put("usuario", et_usr.text.toString())
            obj.put("password", et_pwd.text.toString())
            obj.put("nombre", et_nom.text.toString())
            obj.put("apellido", et_ape.text.toString())
            try {
                var fileOutputStream = openFileOutput("usuarios.json", Context.MODE_PRIVATE)
                fileOutputStream.write(obj.toString().toByteArray())
                fileOutputStream.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }else{

        }
        finish()
    }

    fun cancelar(view: View) {
        finish()
    }
    
}