package com.example.tictactoe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController


class EnterUserNames : Fragment()  {
    private lateinit var user1EditText: EditText
    private lateinit var user2EditText: EditText
    private lateinit var play_btn: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_enter_user_names, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        user1EditText = view.findViewById(R.id.user1_nickname)
        user2EditText = view.findViewById(R.id.user2_nickname)
        play_btn = view.findViewById(R.id.play_btn)

        play_btn.setOnClickListener {
            if(user1EditText.text.isEmpty() && user2EditText.text.isEmpty()){
                Toast.makeText(context, "Please Enter Nickname for User1 and User2", Toast.LENGTH_SHORT).show()
            }
            else if(user1EditText.text.isEmpty()){
                Toast.makeText(context, "Please Enter Nickname for User1", Toast.LENGTH_SHORT).show()
            }
            else if(user2EditText.text.isEmpty()){
                Toast.makeText(context, "Please Enter Nickname for User2", Toast.LENGTH_SHORT).show()
            }
            else{
                val action = EnterUserNamesDirections.actionEnterUserNamesToGame(user1EditText.text.toString() , user2EditText.text.toString())
                findNavController().navigate(action)
            }
        }
//        while(user1EditText.text.isEmpty() || user2EditText.text.isEmpty()){
//            if(user1EditText.text.isEmpty()){
//                Toast.makeText(context, "Please Enter the Nickname", Toast.LENGTH_SHORT).show()
//            }
//        }



    }



}