package com.example.fuelcalculator

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar

class DistanciaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_distancia)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layoutDistancia)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar_distancia)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val precoCombustivel = intent.getDoubleExtra("PRECO_LITRO", 0.0)
        val consumoPorLitro = intent.getIntExtra("CONSUMO_LITRO", 0)

        val edtDistancia = findViewById<EditText>(R.id.edtDistancia)
        val btnDistanciaCalcular = findViewById<Button>(R.id.btnDistanciaCalcular)

        btnDistanciaCalcular.setOnClickListener {

            val distanciaValor = edtDistancia.text.toString().toInt()
            val consumo = consumoPorLitro
            val precoLitro = precoCombustivel

            //Calculo de litros necessarios
            val litrosNecessarios = distanciaValor / consumo

            //Calculo de custo
            val custoTotal = litrosNecessarios * precoLitro

            println("Distancia: $distanciaValor")
            println("Consumo: $consumo")


            print("Preco por Litro")
            println(precoLitro)

            print("Litros Necessarios: ")
            println(litrosNecessarios)

            print("Custo da viagem R$ $custoTotal")

            val intent = Intent(this, ResultadoActivity::class.java)
            intent.putExtra("DISTANCIA_PRECO_LITRO", precoLitro)
            intent.putExtra("DISTANCIA_CONSUMO_LITRO", consumo)
            intent.putExtra("DISTANCIA_VALOR", distanciaValor)
            intent.putExtra("DISTANCIA_CUSTO_TOTAL", custoTotal)

            startActivity(intent)
        }

        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}