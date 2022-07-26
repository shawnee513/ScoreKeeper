package com.example.scorekeeper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.scorekeeper.databinding.FragmentGameOverBinding

class GameOverFragment : Fragment() {
    private lateinit var binding: FragmentGameOverBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_game_over, container, false)
        binding = FragmentGameOverBinding.inflate(inflater)

        //get data from previous fragment
        val args = GameOverFragmentArgs.fromBundle(requireArguments())
        binding.gameOverTvTeam1Score.text = "${args.team1Score} Points"
        binding.gameOverTvTeam2Score.text = "${args.team2Score} Points"

        if(args.team1Score.toInt() > args.team2Score.toInt()){
            binding.gameOverTvWinner.text = "${args.team1Name} Wins!!!"
            binding.gameOverTvWinner.setTextColor(ContextCompat.getColor(requireContext().applicationContext, R.color.team1))
        } else{
            binding.gameOverTvWinner.text = "${args.team2Name} Wins!!!"
            binding.gameOverTvWinner.setTextColor(ContextCompat.getColor(requireContext().applicationContext, R.color.team2))
        }

        //set on click listeners
        binding.gameOverBtNewGame.setOnClickListener {
            findNavController().navigate(GameOverFragmentDirections.actionGameOverFragmentToTitleFragment())
        }

       return binding.root
    }
}