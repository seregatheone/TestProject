package com.example.testapplication.ui.jokes

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.R
import com.example.testapplication.data.retrofitrequest.RequestModel

class RecViewAdapter : RecyclerView.Adapter<RecViewAdapter.MyViewHolder>() {

    private var allData : List<RequestModel> = emptyList()

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = allData[position]

        val id = holder.itemView.findViewById<TextView>(R.id.id)
        id.text = position.toString()


        val joke = holder.itemView.findViewById<TextView>(R.id.jokeTextView)
        joke.text = currentItem.body

    }

    override fun getItemCount(): Int {
        return allData.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setRequestsData(requestsPool: List<RequestModel>){
        allData = requestsPool
        notifyDataSetChanged()
    }
}