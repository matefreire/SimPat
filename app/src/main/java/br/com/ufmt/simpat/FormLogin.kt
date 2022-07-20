package br.com.ufmt.simpat

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import br.com.ufmt.simpat.FormCadastroUsuario as FormCadastroUsuario


class FormLogin : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_login)
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Firebase
            // Initialize Firebase Auth
        auth = Firebase.auth

        title = "SimPat"

        //Buscando informações da tela para validação e encaminhamento
        val btnLogin:Button = findViewById(R.id.btn_login)
        val editTxtEmail:EditText = findViewById(R.id.inputemail)
        val editTxtSenha:EditText = findViewById(R.id.inputsenha)
        val txtSemConta:TextView = findViewById(R.id.text_sem_conta)
        val txtEsqueceuSenha:TextView = findViewById(R.id.text_esqueceu_senha)

        //Criando intenção para o login
        btnLogin.setOnClickListener {
            val intent = Intent(this@FormLogin, Home::class.java)

            if (editTxtEmail.toString() == "" || editTxtSenha.toString() == "") {
                Toast.makeText(applicationContext, "Os campos de login e senha são obrigatórios!", Toast.LENGTH_LONG).show()
            //Criar Validações
            } else {
                startActivity(intent)
            }
        }

        //Criando intenção para o cadastro
        txtSemConta.setOnClickListener {
            val intent = Intent(this@FormLogin, FormCadastroUsuario::class.java)
            startActivity(intent)
        }

        //Criando intenção para o esqueceu a senha
        txtEsqueceuSenha.setOnClickListener {
            val intent = Intent(this@FormLogin, FormEsqueceuSenha::class.java)
            startActivity(intent)
        }
    }
}