package com.yonis.retrofit_restapi.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yonis.retrofit_restapi.R
import com.yonis.retrofit_restapi.modal.CyrptoModal
import kotlinx.android.synthetic.main.row_layout.view.*

class RecyclerViewAdapter(private val CyrptoList:ArrayList<CyrptoModal>, private val listener:Listener) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {

    interface Listener {
        fun onItemClick(cryptoModel: CyrptoModal)
    }
    private val colors: Array<String> = arrayOf("#13bd27","#29c1e1","#b129e1","#d3df13","#f6bd0c","#a1fb93","#0d9de3","#ffe48f")

    class RowHolder(view:View) : RecyclerView.ViewHolder(view) {

        fun bind(cyrptoModal:CyrptoModal, colors: Array<String>, position:Int, listener: Listener){
            itemView.setOnClickListener{
                listener.onItemClick(cyrptoModal)
            }
            itemView.setBackgroundColor(Color.parseColor(colors[position%8]))
            itemView.text_name.text = cyrptoModal.currency
            itemView.text_price.text = cyrptoModal.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)

        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(CyrptoList[position],colors,position,listener)
    }

    override fun getItemCount(): Int {
       return CyrptoList.size
    }
}