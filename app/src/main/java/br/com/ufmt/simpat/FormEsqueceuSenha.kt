package br.com.ufmt.simpat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class FormEsqueceuSenha : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_esqueceu_senha)


        val txtEsqueceuSenhaVoltarHome: TextView = findViewById(R.id.textView)

        //Criando intenção para voltar no menu principal
        txtEsqueceuSenhaVoltarHome.setOnClickListener {
            val intent = Intent(this@FormEsqueceuSenha, FormLogin::class.java)
            startActivity(intent)
        }
    }
}