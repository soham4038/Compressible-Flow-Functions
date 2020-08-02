package com.astrodev.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

class OSR : Fragment() {


    private lateinit var layoutosr: ConstraintLayout

    private var gamma = 1.4
    private var m2 = 0.0

    private lateinit var INPUT: EditText
    private lateinit var GAMMA: EditText
    private var input: Double = 0.0
    private var m1 = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_osr, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutosr = view.findViewById(R.id.osr_container)

        var M1:EditText = layoutosr.findViewById(R.id.m1)
        var GAMMA:EditText= layoutosr.findViewById(R.id.gamma)
        var INPUT:EditText= layoutosr.findViewById(R.id.Input)

        var M2:TextView = layoutosr.findViewById(R.id.m2)
        var P2P1:TextView=layoutosr.findViewById(R.id.p2p1)
        var R2R1:TextView = layoutosr.findViewById(R.id.r2r1)
        var T2T1:TextView=layoutosr.findViewById(R.id.t2t1)
        var W_ANG:TextView = layoutosr.findViewById(R.id.wave_ang)
        var T_ANG:TextView=layoutosr.findViewById(R.id.turn_ang)
        var P02P01:TextView = layoutosr.findViewById(R.id.p02p01)
        var M1n:TextView=layoutosr.findViewById(R.id.mn1)
        var M2n:TextView = layoutosr.findViewById(R.id.mn2)

        var  calc:FloatingActionButton = layoutosr.findViewById(R.id.floatingActionButton)

        val spinner: Spinner = layoutosr.findViewById(R.id.spinner)
        var selectedOption = 0
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, pos: Int, id: Long) {
                if (parent != null) {
                    selectedOption = parent.selectedItemPosition
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        calc.setOnClickListener {
            calculate(selectedOption)
        }

    }

    private fun calculate(selectedOption: Int) {

        gamma = (GAMMA.text.toString()).toDouble()
        input = (INPUT.text.toString()).toDouble()
    }
}