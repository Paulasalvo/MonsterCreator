package cl.desafiolatam.monstercreator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.desafiolatam.monstercreator.R
import cl.desafiolatam.monstercreator.model.Monster
import cl.desafiolatam.monstercreator.model.MonsterAttributes
import cl.desafiolatam.monstercreator.model.MonsterGenerator
import cl.desafiolatam.monstercreator.model.MonsterRepository
import cl.desafiolatam.monstercreator.model.MonsterRepositoryInterface
import kotlinx.coroutines.launch

/**
 * Created by Cristian Vidal on 2019-10-02.
 */
class AllMonsterViewModel(
    private val monsterRepository: MonsterRepositoryInterface = MonsterRepository()
): ViewModel() {

    init {
        viewModelScope.launch {
            val monsterGenerator = MonsterGenerator()

            monsterRepository.saveMonster(
                monsterGenerator.generateMonster(
                    attributes = MonsterAttributes(10, 10, 10),
                    name = "Hola",
                    drawable = R.drawable.asset01
                )
            )
        }

    }

    fun getAllMonsters(): LiveData<List<Monster>> = monsterRepository.getAllMonsters()

    fun clearAllMonsters() {
        viewModelScope.launch {
            monsterRepository.clearAllMonsters()
        }
    }


}