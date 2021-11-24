package com.hyrule.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hyrule.app.data.HyruleEntity
import com.hyrule.app.databinding.HyruleEntityBinding

class HyruleListAdapter(
    private val hyruleList: List<HyruleEntity>,
    private val listener: HyruleEntityListener
) :
    RecyclerView.Adapter<HyruleListAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val binding = HyruleEntityBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.hyrule_entity, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entity = hyruleList[position]
        with(holder.binding) {
            entityText.text = entity.text
            root.setOnClickListener{
                listener.onEntityClick(entity.id)
            }
        }
    }

    override fun getItemCount() = hyruleList.size

    interface HyruleEntityListener {
        fun onEntityClick(entityId: Int)
    }

}