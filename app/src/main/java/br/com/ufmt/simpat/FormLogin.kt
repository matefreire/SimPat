package br.com.ufmt.simpat

import android.app.Activity
import android.util.Log
import com.google.firebase.auth.FirebaseUser
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
    private lateinit var txtSemConta:TextView
    private lateinit var txtEsqueceuSenha:TextView
    private lateinit var btnLogin:Button
    private lateinit var editTxtEmail:EditText
    private lateinit var editTxtSenha:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_login)
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Firebase
            // Initialize Firebase Auth
            auth = Firebase.auth

        //Buscando compenentes na tela e alimentando variável global
        txtSemConta = findViewById(R.id.text_sem_conta)
        txtEsqueceuSenha = findViewById(R.id.text_esqueceu_senha)
        btnLogin = findViewById(R.id.btn_login)
        editTxtEmail = findViewById(R.id.inputemail)
        editTxtSenha = findViewById(R.id.inputsenha)

        //Criando intenção para o login
        btnLogin.setOnClickListener {
            val possuiErro:Boolean = validaFormulario()
            if (!possuiErro)
                autentica()
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


    private fun validaFormulario(): Boolean {
        var possuiErro:Boolean = false

        if (editTxtEmail.text.isNullOrEmpty()) {
            editTxtEmail.error = "O e-mail é obrigatório!"
            possuiErro = true
        } else {
            if (editTxtSenha.text.isNullOrEmpty()) {
                editTxtSenha.error = "A senha é obrigatória!"
                possuiErro = true
            }
        }
        return possuiErro
    }

    private fun autentica() {
        val intent = Intent(this@FormLogin, Home::class.java)
        auth.signInWithEmailAndPassword(editTxtEmail.text.toString(), editTxtSenha.text.toString()).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Toast.makeText(baseContext, "Login realizado com sucesso!", Toast.LENGTH_LONG).show()
                startActivity(intent)
            } else {
                Toast.makeText(baseContext, "Erro ao efetuar login!", Toast.LENGTH_LONG).show()
            }
        }
    }
}