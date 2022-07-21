package br.com.ufmt.simpat

import android.content.pm.ActivityInfo
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ListaBem : AppCompatActivity() {

    private lateinit var txtListaBemVoltarHome: TextView
    private lateinit var txtListaDeslogar: TextView
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_bem)
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        auth = Firebase.auth
        val currentUser = auth.currentUser

        if(currentUser != null){
            reload();
        }

        txtListaBemVoltarHome = findViewById(R.id.textVoltar)
        txtListaDeslogar = findViewById(R.id.textLogout)

        //Criando intenção para voltar para Home
        txtListaBemVoltarHome.setOnClickListener {
            val intent = Intent(this@ListaBem, Home::class.java)
            startActivity(intent)
        }

        //Criando intenção para voltar para Home
        txtListaDeslogar.setOnClickListener {
            voltarLogin()
        }
    }

    private fun reload() {

    }

    private fun voltarLogin(){
        val intent = Intent(this@ListaBem, FormLogin::class.java)
        Firebase.auth.signOut()
        startActivity(intent)
    }
}