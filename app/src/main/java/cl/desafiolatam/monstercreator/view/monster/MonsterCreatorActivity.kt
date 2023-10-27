package cl.desafiolatam.monstercreator.view.monster

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import cl.desafiolatam.monstercreator.databinding.ActivityMonsterCreatorBinding
import cl.desafiolatam.monstercreator.model.AttributeStore
import cl.desafiolatam.monstercreator.model.AttributeType
import cl.desafiolatam.monstercreator.model.AttributeValue
import cl.desafiolatam.monstercreator.model.MonsterImage
import cl.desafiolatam.monstercreator.view.monsteravatars.MonsterAdapter
import cl.desafiolatam.monstercreator.view.monsteravatars.MonsterBottomDialogFragment
import cl.desafiolatam.monstercreator.viewmodel.MonsterCreatorViewModel


class MonsterCreatorActivity : AppCompatActivity(), MonsterAdapter.MonsterListener {

    private lateinit var binding: ActivityMonsterCreatorBinding
    private lateinit var viewModel: MonsterCreatorViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMonsterCreatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProviders.of(this).get(MonsterCreatorViewModel::class.java)
        binding.viewmodel = viewModel
        configureUI()
        configureSpinnerAdapters()
        configureSpinnerListeners()
        configureClickListeners()
        configureLiveDataObservers()
    }

    private fun configureUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Añade una Monstruo"
        if (viewModel.drawable != 0) hideTapLabel()

        binding.saveButton.setOnClickListener {
            viewModel.saveCreature()
        }
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

    private fun configureSpinnerListeners() {
        binding.intelligence.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.attributeSelected(
                    AttributeType.INTELLIGENCE,
                    position
                )
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        binding.strength.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.attributeSelected(AttributeType.UGLINESS, position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        binding.endurance.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.attributeSelected(AttributeType.EVILNESS, position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
    private fun configureClickListeners() {
        binding.avatarImageView.setOnClickListener {
            val bottomDialogFragment =
                MonsterBottomDialogFragment.newInstance()
            bottomDialogFragment.show(supportFragmentManager,
                "AvatarBottomDialogFragment")
        }
    }

    private fun configureLiveDataObservers() {
        viewModel.getCreatureLiveData().observe(this, Observer { creature ->
            creature.let {
                binding.hitPoints.text = it.monsterPoints.toString()
                binding.avatarImageView.setImageResource(it.drawable)
                binding.nameEditText.setText(creature.name)
            }
        })

        viewModel.getSaveLiveData().observe(this, Observer { creature ->
            creature.let {
                if(it) {
                    Toast.makeText(this,"Monstruo guardado correctamente", Toast.LENGTH_SHORT).show()
                    finish()
                }
                else {
                    Toast.makeText(this,"Que ha ocurrido un problemita", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
    private fun hideTapLabel() {
        binding.tapLabel.visibility = View.INVISIBLE
    }
    override fun monsterClicked(monsterImage: MonsterImage) {
        viewModel.drawableSelected(monsterImage.drawable)
        hideTapLabel()
    }

}
