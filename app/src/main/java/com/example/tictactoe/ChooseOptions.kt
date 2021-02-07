package com.example.tictactoe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class ChooseOptions: Fragment() , View.OnClickListener {
    private lateinit var navController : NavController
    private lateinit var start_game_btn: Button
    private lateinit var view_score_btn: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.choose_options, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        start_game_btn = view.findViewById(R.id.start_game_btn)
        start_game_btn.setOnClickListener(this)
        view_score_btn = view.findViewById(R.id.view_score_btn)
        view_score_btn.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.start_game_btn -> navController.navigate(R.id.action_chooseOptions_to_enterUserNames)
                R.id.view_score_btn -> findNavController().navigate(ChooseOptionsDirections.actionChooseOptionsToViewScore())

            }
        }
    }
}