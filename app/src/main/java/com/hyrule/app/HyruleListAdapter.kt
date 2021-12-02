package com.hyrule.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hyrule.app.data.HyruleEntity
import com.hyrule.app.databinding.ListItemBinding

class EntitiesListAdapter(
    private val entitiesList: List<HyruleEntity>,
    private val listener: ListItemListener
) :
    RecyclerView.Adapter<EntitiesListAdapter.ViewHolder>() {

    val selectedEntities = arrayListOf<HyruleEntity>()

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val binding = ListItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount() = entitiesList.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entity = entitiesList[position]
        with(holder.binding) {
            entityName.text = entity.name
            root.setOnClickListener {
                listener.onItemClick(entity)
            }

        }

    }

    interface ListItemListener {
        fun onItemClick(entities: HyruleEntity)
    }
}