package br.com.ufmt.simpat

import android.content.pm.ActivityInfo
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FormCadastroUsuario : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var btnCadastrar: Button
    private lateinit var txtNome: EditText
    private lateinit var txtEmail: EditText
    private lateinit var txtSenha1: EditText
    private lateinit var txtSenha2: EditText
    private lateinit var txtVoltarLogin: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_cadastro_usuario)
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        auth = FirebaseAuth.getInstance()

        btnCadastrar = findViewById(R.id.btncadastrar)
        txtNome = findViewById(R.id.inputinfonome)
        txtEmail = findViewById(R.id.inputinfoemail)
        txtSenha1 = findViewById(R.id.inputinfosenha1)
        txtSenha2 = findViewById(R.id.inputinfosenha2)

        btnCadastrar.setOnClickListener(){
            var possuiErro:Boolean = validaFormulario()
            if (!possuiErro) {
                cadastraUsuario()
                voltarLogin()
            }
        }

        txtVoltarLogin = findViewById(R.id.voltar)
        //Criando intenção para voltar para Home
        txtVoltarLogin.setOnClickListener {
            val intent = Intent(this@FormCadastroUsuario, FormLogin::class.java)
            startActivity(intent)
        }
    }

    private fun voltarLogin(){
        val intent = Intent(this@FormCadastroUsuario, FormLogin::class.java)
        startActivity(intent)
    }

    private fun validaFormulario(): Boolean {
        var possuiErro:Boolean = false

        if (txtNome.text.isNullOrEmpty()) {
            txtNome.error = "O nome é obrigatório!"
            possuiErro = true
        }
        if (txtEmail.text.isNullOrEmpty()) {
            txtEmail.error = "O e-mail é obrigatório!"
            possuiErro = true
        }
        if (txtSenha1.text.isNullOrEmpty()) {
            txtSenha1.error = "A senha é obrigatória!"
            possuiErro = true
        }
        if (txtSenha2.text.isNullOrEmpty()) {
            txtSenha2.error = "A senha é obrigatória!"
            possuiErro = true
        }
        if (txtSenha1.text.toString() != txtSenha2.text.toString()) {
            txtSenha2.error = "As senhas são diferentes!"
            possuiErro = true
        }
        if (txtSenha1.text.length < 6) {
            txtSenha1.error = "A senha deve possuir no mínimo 6 caracteres!"
            possuiErro = true
        }
        if (txtSenha2.text.length < 6) {
            txtSenha2.error = "A senha deve possuir no mínimo 6 caracteres!"
            possuiErro = true
        }

        return possuiErro
    }

    private fun cadastraUsuario() {
        auth.createUserWithEmailAndPassword(txtEmail.text.toString(), txtSenha1.text.toString()).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Toast.makeText(baseContext, "Cadastro efetuado com sucesso!", Toast.LENGTH_LONG).show()
                val user = auth.currentUser
                Log.d(TAG, "createUserWithEmail:success")
            } else {
                Log.w(TAG, "createUserWithEmail:failure", task.exception)
                Toast.makeText(baseContext, task.exception.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun updateUI(user: FirebaseUser?) {

    }

    private fun reload() {

    }

    companion object {
        private const val TAG = "EmailPassword"
    }

}

