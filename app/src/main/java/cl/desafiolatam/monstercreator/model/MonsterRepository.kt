package cl.desafiolatam.monstercreator.model

import androidx.lifecycle.LiveData
import cl.desafiolatam.monstercreator.app.MonsterCreatorApplication
import cl.desafiolatam.monstercreator.model.db.MonsterDao

/**
 * Created by Cristian Vidal on 2019-09-26.
 */

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class MonsterRepository(): MonsterRepositoryInterface {

    private val monsterDao: MonsterDao = MonsterCreatorApplication.database.monsterDao()

    override suspend fun saveMonster(monster: Monster) {
        monsterDao.insertMonster(monster)
    }

    override fun getAllMonsters(): LiveData<List<Monster>> {
        return monsterDao.getAllMonsters()
    }

    override suspend fun clearAllMonsters() {
        monsterDao.deleteAllMonsters()
    }

}