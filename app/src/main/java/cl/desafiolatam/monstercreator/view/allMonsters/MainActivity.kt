package cl.desafiolatam.monstercreator.view.allMonsters

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import cl.desafiolatam.monstercreator.R
import cl.desafiolatam.monstercreator.databinding.ActivityMainBinding
import cl.desafiolatam.monstercreator.view.monster.MonsterCreatorActivity
import cl.desafiolatam.monstercreator.viewmodel.AllMonsterViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: AllMonsterViewModel
    private val adapter = AllMonstersAdapter(mutableListOf())

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener {
            val intent = Intent(this, MonsterCreatorActivity::class.java)
            startActivity(intent)

        }

        initRecyclerView()
    }

    private fun initRecyclerView() {
        viewModel = ViewModelProvider(this).get(AllMonsterViewModel::class.java)
        binding.llContent.rvMonsters.layoutManager = LinearLayoutManager(this)
        binding.llContent.rvMonsters.adapter = adapter
        viewModel.getAllMonsters().observe(this, Observer { monsters ->
            monsters?.let {
                adapter.updateMonster(it)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_delete -> {
                viewModel.clearAllMonsters()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
