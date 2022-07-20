package br.com.ufmt.simpat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val txtHomeListaBem: TextView = findViewById(R.id.textVoltar)

        //Criando intenção para voltar para Home
        txtHomeListaBem.setOnClickListener {
            val intent = Intent(this@Home, ListaBem::class.java)
            startActivity(intent)
        }

    }
}