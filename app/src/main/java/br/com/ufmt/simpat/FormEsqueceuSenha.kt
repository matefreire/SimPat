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

class FormEsqueceuSenha : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var editTxtEmail: EditText
    private lateinit var btnRedefinir: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_esqueceu_senha)
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        auth = Firebase.auth
        editTxtEmail = findViewById(R.id.inputinfoemail)
        btnRedefinir = findViewById(R.id.btn_redefinir_senha)
        val txtEsqueceuSenhaVoltarHome: TextView = findViewById(R.id.text_voltar)

        //Criando intenção para voltar no menu principal
        txtEsqueceuSenhaVoltarHome.setOnClickListener {
            val intent = Intent(this@FormEsqueceuSenha, FormLogin::class.java)
            startActivity(intent)
        }

        //Criando Evento de Envio de e-mail para alterar senha
        btnRedefinir.setOnClickListener(){
            val possuiErro:Boolean = validaFormulario()
            if (!possuiErro) {
                enviarEmail()
                voltarLogin()
            }
        }
    }

    private fun validaFormulario(): Boolean {
        var possuiErro: Boolean = false
        val regex = """[a-z0-9!#${'$'}%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#${'$'}%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?""".toRegex()

        if (editTxtEmail.text.toString().isNullOrEmpty()) {
            editTxtEmail.error = "O e-mail é obrigatório!"
            possuiErro = true
        }

        if (!regex.containsMatchIn(editTxtEmail.text.toString())) {
            editTxtEmail.error = "O e-mail é inválido!"
            //Toast.makeText(baseContext, "Email: "+ editTxtEmail.text.toString(), Toast.LENGTH_LONG).show()
            Toast.makeText(baseContext, regex.containsMatchIn(editTxtEmail.text.toString()).toString(), Toast.LENGTH_LONG).show()
            possuiErro = true
        }

        return possuiErro
    }

    private fun enviarEmail() {
        auth.sendPasswordResetEmail(editTxtEmail.text.toString()).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    baseContext,
                    "Foi enviado um e-mail para alterar a senha",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    baseContext,
                    "Erro ao enviar o e-mail: "+ task.exception,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun voltarLogin() {
        val intent = Intent(this@FormEsqueceuSenha, FormLogin::class.java)
        startActivity(intent)
    }

}