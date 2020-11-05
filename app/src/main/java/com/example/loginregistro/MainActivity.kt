package com.example.loginregistro

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.loginregistro.activities.Registro
import com.example.loginregistro.dialog.LoginDialog
import com.example.loginregistro.model.User
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    val REQUEST_CODE_REGISTRO = 1000

    var usuario = User()

    var pwd : String = ""

    lateinit var btnLogin : Button
    lateinit var btnInfo : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin = findViewById(R.id.buttonLogin)
        btnInfo = findViewById(R.id.buttonInfo)

        btnInfo.isEnabled = false

    }

    fun login(view: View) {
        var loginDialog = LoginDialog()
        var bufferendReader = BufferedReader(InputStreamReader(openFileInput("usuarios.json")))
        var buferedVuelta = bufferendReader.readLine()
        var json = JSONObject(buferedVuelta)

        loginDialog.usr = json.getString("usuario")
        loginDialog.passwd = json.getString("password")
        bufferendReader.close()


        loginDialog.mainActivity = this
        loginDialog.show(supportFragmentManager, "loginDialog_tag")
    }


    fun registro(view: View) {
        var intentRegistro = Intent(this, Registro::class.java)
        intentRegistro.putExtra("registro", true)
        startActivityForResult(intentRegistro, REQUEST_CODE_REGISTRO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE_REGISTRO){
                var bundleData = data!!.getBundleExtra("usuario")

                usuario.setBundle(bundleData!!)

                btnLogin.isEnabled = true
            }
        }
    }

    fun activarBotonInfo(){
        btnInfo.isEnabled = true
    }

    fun info(view: View) {
        var intentInfo = Intent(this, Registro::class.java)
        intentInfo.putExtra("registro", false)
        intentInfo.putExtra("usuario", usuario.getBundle())

        startActivity(intentInfo)
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Atención: ")
        builder.setMessage("¿Desea salir de la aplicación?")

        builder.setPositiveButton("SI"){dialog, _ -> finish()}
        builder.setNegativeButton("NO"){dialog, which ->  }
        builder.show()
    }
}