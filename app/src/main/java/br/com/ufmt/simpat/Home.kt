package br.com.ufmt.simpat

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Home : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var txtVoltarLogin: TextView
    private lateinit var imageBem: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        auth = Firebase.auth
        val currentUser = auth.currentUser

        if(currentUser != null){
            reload();
        }

        imageBem = findViewById(R.id.imageBem)
        txtVoltarLogin = findViewById(R.id.textSair)

        //Criando intenção para voltar para Home
        txtVoltarLogin.setOnClickListener() {
            voltarLogin()
        }

        imageBem.setOnClickListener(){
            val intent = Intent(this@Home, ListaBem::class.java)
            startActivity(intent)
        }
    }

    private fun reload() {

    }

    private fun voltarLogin(){
        val intent = Intent(this@Home, FormLogin::class.java)
        Firebase.auth.signOut()
        startActivity(intent)
    }
}