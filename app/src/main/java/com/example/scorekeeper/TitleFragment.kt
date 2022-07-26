package com.example.scorekeeper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.scorekeeper.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {
    private lateinit var binding: FragmentTitleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_title, container, false)

        binding = FragmentTitleBinding.inflate(inflater)

        //Set click listeners
        binding.titleBtStart.setOnClickListener { nextFragment() }

        return binding.root
    }

    //Move to the next fragment passing the appropriate data along
    private fun nextFragment(){
        val team1 = binding.titleEtTeam1Name.text.toString()
        val team2 = binding.titleEtTeam2Name.text.toString()

        findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment(team1, team2))
    }
}