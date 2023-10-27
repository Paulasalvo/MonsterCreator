package cl.desafiolatam.monstercreator.view.allMonsters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cl.desafiolatam.monstercreator.R
import cl.desafiolatam.monstercreator.model.Monster

class AllMonstersAdapter(private val monsters: MutableList<Monster>) :
    RecyclerView.Adapter<AllMonstersAdapter.ViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AllMonstersAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.monster_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AllMonstersAdapter.ViewHolder, position: Int) {
        holder.onBind(monsters[position])
    }

    override fun getItemCount(): Int {
        return monsters.size
    }

    fun updateMonster(monsters: List<Monster>){
        this.monsters.clear()
        this.monsters.addAll(monsters)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val monsterImageView: ImageView = view.findViewById(R.id.monsterImageView)
        val nameTextView: TextView = view.findViewById(R.id.name)
        val monsterPoint: TextView = view.findViewById(R.id.MonsterPoints)
        fun onBind(monster: Monster) {
            with(monster) {
                monsterImageView.setImageResource(drawable)
                nameTextView.text = name
                monsterPoint.text = monsterPoints.toString()
            }
        }

    }


}