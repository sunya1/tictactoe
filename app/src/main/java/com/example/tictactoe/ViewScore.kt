package com.example.tictactoe

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ViewScore : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var go_to_main_page_btn: Button
    private var playerRep = PlayersObj



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view_score, container, false)
        // Inflate the layout for this fragment

        val args = arguments
        var name1 = args?.getString("user1Name")
        var winner = args?.getString("winner")
        var name2 = args?.getString("user2Name")

        var firstFound = playerRep.searchForPlayer(name1 , winner)
        var secondFound = playerRep.searchForPlayer(name2 , winner)


        if(!firstFound && !secondFound){
            playerRep.addPlayer(name1,winner )
            playerRep.addPlayer( name2 , winner)

        }
        else if(!firstFound){
            playerRep.addPlayer(name1 , winner)
            Log.d("search of name2 " , "$name2 $winner")
        }
        else if(!secondFound){
            playerRep.addPlayer(name2 , winner)
            Log.d("add 1 " , "$name1 $winner")
            Log.d("add 2 " , "$name2 $winner")
        }


        recyclerView = view.findViewById(R.id.list_of_users)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = RecyclerAdapter(playerRep.playersRecords)
        recyclerView.setHasFixedSize(true)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        go_to_main_page_btn = view.findViewById(R.id.go_to_main_page_btn)
        go_to_main_page_btn.setOnClickListener {
            val action = ViewScoreDirections.actionViewScoreToChooseOptions()
            findNavController().navigate(action)
        }


    }

}