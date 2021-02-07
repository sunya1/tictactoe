package com.example.tictactoe

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private var playersRecords: ArrayList<Player> ) :  RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_view , parent , false )
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var player = playersRecords[position]

        holder.username.text = player.name
        holder.win_view.text = "Wins : " + player.numOfWin.toString()
        holder.lose_view.text = "Loses : " + player.numOfLose.toString()
    }

    override fun getItemCount(): Int {
        return playersRecords.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var win_view:TextView = view.findViewById(R.id.num_of_wins_view)
        var lose_view:TextView = view.findViewById(R.id.num_of_loses_view)
        var username: TextView = view.findViewById(R.id.user_name)
    }



}


