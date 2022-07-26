package com.example.scorekeeper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.scorekeeper.databinding.FragmentGameBinding
import kotlin.math.round

class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding
    private lateinit var args: GameFragmentArgs

    //Counter variables
    var team1TotalShots = 0
    var team1MadeShots = 0

    var team2TotalShots = 0
    var team2MadeShots = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_game, container, false)
        binding = FragmentGameBinding.inflate(inflater)

        //get data passed from previous frame
        args = GameFragmentArgs.fromBundle(requireArguments())
        binding.gameTvTeam1Name.text = args.team1Name
        binding.gameTvTeam2Name.text = args.team2Name

        //set click listeners
        binding.gameBtTeam1Ft.setOnClickListener { team1Shoot(1) }
        binding.gameBtTeam12pt.setOnClickListener { team1Shoot(2) }
        binding.gameBtTeam13pt.setOnClickListener { team1Shoot(3) }
        binding.gameBtTeam1Missed.setOnClickListener { team1Shoot(0) }
        binding.gameBtTeam2Ft.setOnClickListener { team2Shoot(1) }
        binding.gameBtTeam22pt.setOnClickListener { team2Shoot(2) }
        binding.gameBtTeam23pt.setOnClickListener { team2Shoot(3) }
        binding.gameBtTeam2Missed.setOnClickListener { team2Shoot(0) }
        binding.gameBtEnd.setOnClickListener { nextFragment() }

        return binding.root
    }

    //The first team has shot, update score and stats
    private fun team1Shoot(points: Int){
        if(points != 0){
            team1MadeShots++

            //A shot was made, update the score
            val newScore = binding.gameTvTeam1Score.text.toString().toInt() + points
            binding.gameTvTeam1Score.text = newScore.toString()

            //update the quarter score
            when{
                binding.gameRbQ1.isChecked -> {
                    val quarterScore = binding.gameTvTeam1Q1.text.toString().toInt() + points
                    binding.gameTvTeam1Q1.text = quarterScore.toString()
                }
                binding.gameRbQ2.isChecked -> {
                    val quarterScore = binding.gameTvTeam1Q2.text.toString().toInt() + points
                    binding.gameTvTeam1Q2.text = quarterScore.toString()
                }
                binding.gameRbQ3.isChecked -> {
                    val quarterScore = binding.gameTvTeam1Q3.text.toString().toInt() + points
                    binding.gameTvTeam1Q3.text = quarterScore.toString()
                }
                else -> {
                    val quarterScore = binding.gameTvTeam1Q4.text.toString().toInt() + points
                    binding.gameTvTeam1Q4.text = quarterScore.toString()
                }
            }
        }
        //Update shooting percentage
        team1TotalShots++
        val shootingPercentage = round(team1MadeShots.toFloat()/team1TotalShots * 10000)/100
        binding.gameTvTeam1Percentage.text = "$shootingPercentage %"
    }

    //The second team has shot, update score and stats
    private fun team2Shoot(points: Int){
        if(points != 0){
            team2MadeShots++

            //A shot was made, update the score
            val newScore = binding.gameTvTeam2Score.text.toString().toInt() + points
            binding.gameTvTeam2Score.text = newScore.toString()

            //update the quarter score
            when{
                binding.gameRbQ1.isChecked -> {
                    val quarterScore = binding.gameTvTeam2Q1.text.toString().toInt() + points
                    binding.gameTvTeam2Q1.text = quarterScore.toString()
                }
                binding.gameRbQ2.isChecked -> {
                    val quarterScore = binding.gameTvTeam2Q2.text.toString().toInt() + points
                    binding.gameTvTeam2Q2.text = quarterScore.toString()
                }
                binding.gameRbQ3.isChecked -> {
                    val quarterScore = binding.gameTvTeam2Q3.text.toString().toInt() + points
                    binding.gameTvTeam2Q3.text = quarterScore.toString()
                }
                else -> {
                    val quarterScore = binding.gameTvTeam2Q4.text.toString().toInt() + points
                    binding.gameTvTeam2Q4.text = quarterScore.toString()
                }
            }
        }
        //Update shooting percentage
        team2TotalShots++
        val shootingPercentage = round(team2MadeShots.toFloat()/team2TotalShots * 10000)/100
        binding.gameTvTeam2Percentage.text = "$shootingPercentage %"
    }

    //Move to the next fragment
    private fun nextFragment(){
        val team1Score = binding.gameTvTeam1Score.text.toString()
        val team2Score = binding.gameTvTeam2Score.text.toString()

        findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameOverFragment(args.team1Name, team1Score, args.team2Name, team2Score))
    }
}