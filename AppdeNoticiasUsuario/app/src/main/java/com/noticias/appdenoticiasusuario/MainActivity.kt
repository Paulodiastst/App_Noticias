package com.noticias.appdenoticiasusuario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import com.noticias.appdenoticiasusuario.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val db = FirebaseFirestore.getInstance()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        recuperarNoticia()
    }

    private fun recuperarNoticia(){

        db.collection("noticias").document("noticia").get()
            .addOnCompleteListener{ documento ->
                if(documento.isSuccessful){

                    val titulo = documento.result.get("titulo").toString()
                    val noticia = documento.result.get("noticia").toString()
                    val data = documento.result.get("data").toString()
                    val autor = documento.result.get("autor").toString()


                    binding.txtTituloNoticia.text = titulo
                    binding.txtNoticia.text = noticia
                    binding.txtDataNoticia.text = data
                    binding.txtAutorNoticia.text = autor

                }

            }

    }
}