package br.com.ufmt.simpat

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CadastrarBem : AppCompatActivity() {

    private lateinit var txtListaBemVoltarLista: TextView
    private lateinit var txtListaBemDeslogar: TextView
    private lateinit var auth: FirebaseAuth

    //Formulario
    private lateinit var txtNomeBem: EditText
    private lateinit var txtCodigoBem: EditText
    private lateinit var txtLocalizacao: EditText
    private lateinit var txtValor: EditText
    private lateinit var btnCadastrarBem: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_bem)
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        auth = Firebase.auth
        val currentUser = auth.currentUser

        if(currentUser != null){
            reload();
        }

        txtListaBemVoltarLista = findViewById(R.id.textVoltar)
        txtListaBemDeslogar = findViewById(R.id.textLogout)

        //Formulario
        txtNomeBem = findViewById(R.id.inputnomebem)
        txtCodigoBem = findViewById(R.id.inputcodigobem)
        txtLocalizacao = findViewById(R.id.inputlocalizacao)
        txtValor = findViewById(R.id.inputvalorbem)
        btnCadastrarBem = findViewById(R.id.btncadastrarbem)

        txtListaBemVoltarLista.setOnClickListener() {
            val intent = Intent(this@CadastrarBem, ListaBem::class.java)
            startActivity(intent)
        }

        txtListaBemDeslogar.setOnClickListener() {
            voltarLogin()
        }

        btnCadastrarBem.setOnClickListener(){
            val possuiErro:Boolean = validaFormulario()
            if (!possuiErro) {
                cadastraBem()
                voltarLogin()
            }
        }
    }

    private fun cadastraBem() {
        TODO("Not yet implemented")
    }

    private fun validaFormulario(): Boolean {
        var possuiErro:Boolean = false

        if (txtNomeBem.text.isNullOrEmpty()) {
            txtNomeBem.error = "O nome do Bem é obrigatório!"
            possuiErro = true
        }
        if (txtNomeBem.text.length < 10) {
            txtNomeBem.error = "O nome do Bem deve possuir no mínimo 10 caracteres!"
            possuiErro = true
        }
        if (txtCodigoBem.text.isNullOrEmpty()) {
            txtCodigoBem.error = "O código do bem é obrigatório!"
            possuiErro = true
        }
        if (txtLocalizacao.text.isNullOrEmpty()) {
            txtLocalizacao.error = "A localização do bem é obrigatória!"
            possuiErro = true
        }
        if (txtLocalizacao.text.length < 10) {
            txtLocalizacao.error = "A localização do bem deve possuir no mínimo 10 caracteres!"
            possuiErro = true
        }
        if (txtValor.text.toString().isNullOrEmpty()) {
            txtValor.error = "O valor do bem é obrigatório!"
            possuiErro = true
        }
        if (txtValor.text.toString().toFloat() < 0 ) {
            txtValor.error = "O valor não pode ser negativo!"
            possuiErro = true
        }

        return possuiErro
    }

    private fun reload() {

    }

    private fun voltarLogin(){
        val intent = Intent(this@CadastrarBem, FormLogin::class.java)
        Firebase.auth.signOut()
        startActivity(intent)
    }

}