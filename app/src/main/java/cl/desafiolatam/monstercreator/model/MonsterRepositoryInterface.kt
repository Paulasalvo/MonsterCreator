package cl.desafiolatam.monstercreator.model

import androidx.lifecycle.LiveData

interface MonsterRepositoryInterface {

    suspend fun saveMonster(monster: Monster)
    fun getAllMonsters(): LiveData<List<Monster>>
    suspend fun clearAllMonsters()

}