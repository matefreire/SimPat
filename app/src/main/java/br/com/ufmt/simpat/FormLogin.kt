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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_login)
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Firebase
            // Initialize Firebase Auth
            auth = Firebase.auth

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

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            reload();
        }
    }

//    private fun createAccount(email: String, password: String) {
//        // [START create_user_with_email]
//        auth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "createUserWithEmail:success")
//                    val user = auth.currentUser
//                    updateUI(user)
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
//                    Toast.makeText(baseContext, "Authentication failed.",
//                        Toast.LENGTH_SHORT).show()
//                    updateUI(null)
//                }
//            }
//        // [END create_user_with_email]
//    }


    private fun reload() {

    }

}