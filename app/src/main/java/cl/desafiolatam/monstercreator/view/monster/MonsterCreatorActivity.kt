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

        binding.intelligence.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            AttributeStore.INTELLIGENCE
        )
        binding.strength.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            AttributeStore.UGLINESS
        )
        binding.endurance.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            AttributeStore.EVILNESS
        )
    }
}
