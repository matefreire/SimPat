package br.com.ufmt.simpat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FormCadastroUsuario : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_cadastro_usuario)


        val txtCadastroHome: TextView = findViewById(R.id.textView)

        //Criando intenção para voltar para Home
        txtCadastroHome.setOnClickListener {
            val intent = Intent(this@FormCadastroUsuario, FormLogin::class.java)
            startActivity(intent)
        }
    }
}