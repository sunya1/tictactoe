package com.example.tictactoe

import android.util.Log

object PlayersObj {
    private val playerRecs by lazy { arrayListOf<Player>() }
    val playersRecords get() = playerRecs
    fun addPlayer(name: String?, winner: String?) {
        if(name == null ) return

        if(winner == ""){
            playersRecords.add(Player(name , 0 , 0))
            return
        }

        if(winner.equals(name)){
            playersRecords.add(Player(name , 1 , 0))
        }
        else{
            playersRecords.add(Player(name , 0 , 1))
        }
    }

    fun updateRecs(player: Player, winner: String?){
        if(winner.equals(player.name)) player.numOfWin++
        else player.numOfLose++
        Log.d("Player in upadate" , "${player.numOfWin}")
    }
    fun searchForPlayer(name: String? , winner: String?): Boolean {
        for(i in playersRecords){
            if(i.name.equals(name)){
                updateRecs(i ,winner )
                return true
            }
        }
        Log.d("Player in search" , "$name")
        return false
    }
}