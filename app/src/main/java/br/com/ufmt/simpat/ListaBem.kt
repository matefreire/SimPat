package br.com.ufmt.simpat

import android.content.pm.ActivityInfo
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ListaBem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_bem)
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        val txtListaBemVoltarHome: TextView = findViewById(R.id.textVoltar)

        //Criando intenção para voltar para Home
        txtListaBemVoltarHome.setOnClickListener {
            val intent = Intent(this@ListaBem, Home::class.java)
            startActivity(intent)
        }
    }
}