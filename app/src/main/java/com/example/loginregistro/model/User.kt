package com.example.loginregistro.model

import android.os.Bundle

class User {
    var usr : String = ""
    var pwd : String = ""
    var name : String = ""
    var apellido : String = ""

    fun getBundle(): Bundle{
        var bundle = Bundle()
        bundle.putCharSequence("usuario", usr)
        bundle.putCharSequence("contraseña", pwd)
        bundle.putCharSequence("nombre", name)
        bundle.putCharSequence("apellido", apellido)

        return bundle
    }

    fun setBundle(bundle: Bundle){
        usr = bundle.getString("usuario", "")
        pwd = bundle.getString("contraseña", "")
        name = bundle.getString("nombre", "")
        apellido = bundle.getString("apellido", "")
    }
}