package br.com.xpeducacao.desafiomdulo1.ui.cadastro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.xpeducacao.desafiomdulo1.R
import br.com.xpeducacao.desafiomdulo1.databinding.ActivityNewItemBinding
import br.com.xpeducacao.desafiomdulo1.model.entities.Produto
import br.com.xpeducacao.desafiomdulo1.model.enum.UnitEnum
import br.com.xpeducacao.desafiomdulo1.ui.main.MainActivity

class NewItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareListeners()
    }

    private fun prepareListeners() {
        setBtSaveListener()
    }

    private fun setBtSaveListener() {
        binding.btSalvar.setOnClickListener {
            recordData()
        }
    }

    private fun recordData() {
        if (validateData()) {
            val productName = binding.etProduct.text.toString()
            val amount = binding.etAmount.text.toString().toDouble()
            val rbId = binding.rgAmount.checkedRadioButtonId
            val rb = findViewById<RadioButton>(rbId)
            val unit = binding.rgAmount.indexOfChild(rb)

            val item = Produto(
                quantidade = amount,
                nome = productName,
                unidade = UnitEnum.valueOf(unit),
            )
            Log.i("XPEInfo::", "Item registrado: $item")
            Intent().apply {
                putExtra(MainActivity.RETURN, item)
                setResult(RESULT_OK, this)
            }
            finish()
        } else {
            Toast.makeText(this, getString(R.string.msg_alerta), Toast.LENGTH_LONG).show()
        }
    }

    private fun validateData(): Boolean =
        binding.etAmount.text.isNotBlank()
                && binding.etProduct.text.isNotBlank()
                && (!binding.rgAmount.isSelected)
}