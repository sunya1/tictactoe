package com.example.tictactoe

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs


class Game : Fragment() , View.OnClickListener{
    private lateinit var navController: NavController
    private val args: GameArgs by navArgs()
    private lateinit var square2: ImageView
    private lateinit var username: TextView
    private lateinit var continue_btn: Button
    private  val squares = Array(3) { arrayOfNulls<String>(3) }
    private var map: MutableMap<ImageView , Pair<Int , Int>>  = HashMap()
    private var  user1Turn = true ;
    private var moves = 0
    private var playerWinner: String = ""



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        username = view.findViewById(R.id.users_turn)
        continue_btn = view.findViewById(R.id.continue_btn)
        username.text = args.user1Name


        var k = 1 ;
        for(i in 0..2){
            for(j in 0..2){
                var id = resources.getIdentifier("square$k" , "id" , context?.getPackageName())
                var imageView = view.findViewById<ImageView>(id)
                imageView.setOnClickListener(this)
                k++
                map.put(imageView , Pair(i , j))


            }

        }

        continue_btn.setOnClickListener{
            navController = Navigation.findNavController(view)
            navController.navigate(R.id.action_game_to_viewScore , bundleOf(Pair("user1Name" , args.user1Name) ,  Pair("user2Name" , args.user2Name) , Pair("winner" , playerWinner)) )
        }




    }

    override fun onClick(v: View?) {
        if(moves == 9 ) return

        var squarePressed = v as ImageView
        var row = map.get(squarePressed)?.first!!
        var column = map.get(squarePressed)?.second!!
        if(user1Turn && (!squares[row][column].equals("x") && !squares[row][column].equals("o"))){
            squarePressed.setImageResource(R.drawable.ic_x)
            squares[row][column] = "x"
            user1Turn = false ;
            username.text = args.user2Name
        }
        else if(!user1Turn && (!squares[row][column].equals("o")  && !squares[row][column].equals("x"))){
            squarePressed.setImageResource(R.drawable.ic_o)
            squares[row][column] = "o"
            user1Turn = true ;
            username.text = args.user1Name
        }
        moves++
        if(moves >= 5 ){
            checkForWinner()
        }

    }
    private fun checkForWinner(){
        for(i in 0..2){
            if( squares[i][0].equals(squares[i][1]) &&
                    squares[i][0].equals(squares[i][2]) && squares[i][0] != null ){

                moves = 9
                changeBackground(Pair(i , 0) , Pair(i , 1) , Pair(i , 2))
                continue_btn.visibility = View.VISIBLE

                playerWinner = if(squares[i][0].equals("x")) args.user1Name else args.user2Name



            }
        }
        for(i in 0..2){
            if( squares[0][i].equals(squares[1][i]) &&
                    squares[0][i].equals(squares[2][i]) && squares[0][i] != null  ){

                moves = 9
                changeBackground(Pair(0 , i) , Pair(1 , i) , Pair(2 , i))
                continue_btn.visibility = View.VISIBLE


                playerWinner = if(squares[0][i].equals("x")) args.user1Name else args.user2Name



            }
        }
        if(squares[0][0].equals(squares[1][1]) && squares[0][0].equals(squares[2][2])){
            if(squares[0][0] == "o"){
                moves = 9
                changeBackground(Pair(0 , 0) , Pair(1 , 1) , Pair(2 , 2))
                continue_btn.visibility = View.VISIBLE


                playerWinner = args.user2Name

            }
            else if(squares[0][0] == "x"){
                moves = 9
                changeBackground(Pair(0 , 0) , Pair(1 , 1) , Pair(2 , 2))
                continue_btn.visibility = View.VISIBLE
                playerWinner = args.user1Name

            }
        }
        if(squares[0][2].equals(squares[1][1]) && squares[0][2].equals(squares[2][0])){
            if(squares[0][2] == "o"){
                moves = 9
                changeBackground(Pair(0 , 2) , Pair(1 , 1) , Pair(2 , 0))
                continue_btn.visibility = View.VISIBLE
                playerWinner = args.user2Name
            }
            else if(squares[0][2] == "x"){
                moves = 9
                changeBackground(Pair(0 , 2) , Pair(1 , 1) , Pair(2 , 0))
                continue_btn.visibility = View.VISIBLE
                playerWinner = args.user1Name
            }
        }
        if(moves == 9 && playerWinner == ""){
            Toast.makeText(context, "tie", Toast.LENGTH_SHORT).show()
            continue_btn.visibility = View.VISIBLE
        }

        Toast.makeText(context, "$playerWinner", Toast.LENGTH_SHORT).show()
    }

    private fun changeBackground(pair: Pair<Int, Int>, pair1: Pair<Int, Int>, pair2: Pair<Int, Int>) {
        for(i in map.keys){
            if(map[i] == pair){
                i.setBackgroundColor(Color.parseColor("#00FF00"))
            }else if(map[i] == pair1){
                i.setBackgroundColor(Color.parseColor("#00FF00"))

            }else if(map[i] == pair2){
                i.setBackgroundColor(Color.parseColor("#00FF00"))
            }
        }
    }

}
//if(users.containsKey(args.winnerName) ){
//    var player = users[args.winnerName]
//    player?.numOfWin = player?.numOfWin?.plus(1)!!
//    recyclerView.adapter?.notifyDataSetChanged()
//
//}
//else if(users.containsKey(args.loserName)){
//    var player = users[args.winnerName]
//    player?.numOfLose = player?.numOfLose?.plus(1)!!
//
//}
//else if(users.containsKey(args.winnerName) && !users.containsKey(args.loserName)){
//    var player = users[args.winnerName]
//    player?.numOfWin = player?.numOfWin?.plus(1)!!
//    var loser = Player(args.loserName , 0 , 1)
//    users.put(args.loserName , loser)
////            arr_users.add(loser)
//    players.add(loser)
//
//
//}
//else{
//    var winner = Player(args.winnerName , 1 , 0)
//    users.put(args.winnerName , winner)
//    var loser = Player(args.loserName , 0 , 1)
//    users.put(args.loserName , loser)
////            arr_users.add(winner)
////            arr_users.add(loser)
//    players.add(winner)
//    players.add(loser)
//}