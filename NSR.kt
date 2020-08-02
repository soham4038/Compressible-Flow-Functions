package com.astrodev.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton



class NSR : Fragment() {


    private lateinit var layoutnsr: ConstraintLayout

    private var gamma = 1.4
    private var m2 = 0.0
    private var p2p1 = 0.0
    private var r2r1 = 0.0
    private var t2t1 = 0.0
    private var p02p01 = 0.0
    private var p1p02 = 0.0
    private lateinit var calc: FloatingActionButton
    private lateinit var M1: TextView
    private lateinit var M2: TextView
    private lateinit var P02P01: TextView
    private lateinit var T2T1: TextView
    private lateinit var P2P1: TextView
    private lateinit var P1P02: TextView
    private lateinit var INPUT: EditText
    private lateinit var GAMMA: EditText
    private lateinit var R2R1: TextView
    private var input: Double = 0.0
    private var m1 = 0.0

      override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nsr, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        layoutnsr = view.findViewById(R.id.nsr_container) as ConstraintLayout

        val spinner: Spinner = layoutnsr.findViewById(R.id.spinner)
        var selectedOption = 1
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, pos: Int, id: Long) {
                if (parent != null) {
                    selectedOption = parent.selectedItemPosition
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        calc = layoutnsr.findViewById(R.id.floatingActionButton)
        INPUT = layoutnsr.findViewById(R.id.Input)
        GAMMA = layoutnsr.findViewById(R.id.gamma)
        P02P01 =layoutnsr.findViewById(R.id.p02p01)
        P1P02 = layoutnsr.findViewById(R.id.p1p02)
        T2T1 = layoutnsr.findViewById(R.id.t2t1)
        P2P1 =layoutnsr.findViewById(R.id.p2p1)
        R2R1 = layoutnsr.findViewById((R.id.r2r1))

        calc.setOnClickListener {
            calculate(selectedOption)
        }
    }

    private fun calculate(selectedOption: Int) {

        gamma = (GAMMA.text.toString()).toDouble()
        input = (INPUT.text.toString()).toDouble()
        var obj = Functions()
        var g = gamma
            //if(i==1) {
            //    if(v>=1.0 || v<=Math.sqrt((g-1.)/2./g)) {
            //      alert("M2 must be between "+ format(""+Math.sqrt((g-1.)/2./g))+" and 1")
            //      return}

        when (selectedOption) {

            1 -> m1 = obj.m2(gamma, input)

            2 -> m1 = Math.sqrt((input - 1.0) * (gamma + 1.0) / 2.0 / gamma + 1)

            3 -> m1 = Math.sqrt(2.0 * input / (gamma + 1 - input * (gamma - 1.0)))

            4 -> {
                var aa = 2.0 * gamma * (gamma - 1.0)
                var bb =
                    4.0 * gamma - (gamma - 1.0) * (gamma - 1.0) - input * (gamma + 1.0) * (gamma + 1.0)
                var cc = -2.0 * (gamma - 1.0)
                m1 = Math.sqrt((-bb + Math.sqrt(bb * bb - 4 * aa * cc)) / 2 / aa)
            }
            5 -> {
                var mnew = 2.0
                m1 = 0.0
                while (Math.abs(mnew - m1) > 0.00001) {
                    m1 = mnew
                    var al = (gamma + 1) * m1 * m1 / ((gamma - 1) * m1 * m1 + 2)
                    var be = (gamma + 1.0) / (2.0 * gamma * m1 * m1 - (gamma - 1))
                    var daldm1 = (2 / m1 - 2 * m1 * (gamma - 1) / ((gamma - 1) * m1 * m1 + 2)) * al
                    var dbedm1 = -4.0 * gamma * m1 * be / (2 * gamma * m1 * m1 - (gamma - 1))
                    var fm =
                        Math.pow(al, gamma / (gamma - 1)) * Math.pow(be, 1 / (gamma - 1)) - input
                    var fdm =
                        gamma / (gamma - 1) * Math.pow(al, 1 / (gamma - 1)) * daldm1 * Math.pow(
                            be,
                            1 / (gamma - 1)
                        ) + Math.pow(al, gamma / (gamma - 1)) / (gamma - 1) * Math.pow(
                            be,
                            (2 - gamma) / (gamma - 1)
                        ) * dbedm1
                    mnew = m1 - fm / fdm
                }
            }

            6 -> {
                var vmax = Math.pow((g + 1) / 2, -g / (g - 1.0))
                if (input >= vmax || input <= 0.0) {
                    return
                }
                var mnew = 2.0
                m1 = 0.0
                while (Math.abs(mnew - m1) > 0.00001) {
                    m1 = mnew
                    var al = (g + 1) * m1 * m1 / 2
                    var be = (g + 1) / (2 * g * m1 * m1 - (g - 1))
                    var daldm1 = m1 * (g + 1)
                    var dbedm1 = -4 * g * m1 * be / (2 * g * m1 * m1 - (g - 1))
                    var fm = Math.pow(al, g / (g - 1)) * Math.pow(be, 1 / (g - 1)) - 1 / input
                    var fdm = g / (g - 1) * Math.pow(al, 1 / (g - 1)) * daldm1 * Math.pow(
                        be,
                        1 / (g - 1)
                    ) + Math.pow(al, g / (g - 1)) / (g - 1) * Math.pow(
                        be,
                        (2 - g) / (g - 1)
                    ) * dbedm1
                    mnew = m1 - fm / fdm
                }
            }


            0 -> {
                m1 = input
            }
        }

        m2=obj.m2(g,m1)

        p2p1=1.0+2.0*g/(g+1.0)*(m1*m1-1.0)

        p02p01=obj.pp0(g,m1)/obj.pp0(g,obj.m2(g,m1))*p2p1

        r2r1=obj.rr0(g,obj.m2(g,m1))/obj.rr0(g,m1)*p02p01

        t2t1=obj.tt0(g,obj.m2(g,m1))/obj.tt0(g,m1)

        p1p02=obj.pp0(g,m1)/p02p01

        M1.text = m1.toString()
        M2.text = m2.toString()
        GAMMA.setText(gamma.toString())
        P2P1.text = p2p1.toString()
        P02P01.text = p02p01.toString()
        P1P02.text = p1p02.toString()
        R2R1.text = r2r1.toString()
        T2T1.text = t2t1.toString()

    }

}