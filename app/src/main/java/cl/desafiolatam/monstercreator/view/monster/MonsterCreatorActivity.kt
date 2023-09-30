package cl.desafiolatam.monstercreator.view.monster

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import cl.desafiolatam.monstercreator.databinding.ActivityMonsterCreatorBinding
import cl.desafiolatam.monstercreator.model.AttributeValue
import cl.desafiolatam.monstercreator.model.db.AttributeStore
import cl.desafiolatam.monstercreator.viewmodel.MonsterCreatorViewModel


class MonsterCreatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMonsterCreatorBinding
    private lateinit var viewModel: MonsterCreatorViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMonsterCreatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureSpinnerAdapters()
    }

    private fun configureSpinnerAdapters() {
        val newList = listOf("A", "B", "C")
        var emptyList: List<String> = listOf()
        val list =  AttributeStore.INTELLIGENCE.toList()
        this.let {
            val adapter = ArrayAdapter<AttributeValue>(it, R.layout.simple_spinner_dropdown_item, newList)
            binding.intelligence.adapter = adapter
        }

/*        binding.strength.adapter = ArrayAdapter<AttributeValue>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            AttributeStore.UGLINESS
        )
        binding.endurance.adapter = ArrayAdapter<AttributeValue>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            AttributeStore.EVILNESS
        )*/
    }
}
