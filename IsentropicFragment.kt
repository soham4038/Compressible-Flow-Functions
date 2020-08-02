package com.astrodev.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.isentropic.*
import kotlin.math.asin
import kotlin.math.sqrt


class IsentropicFragment : Fragment() {


    private lateinit var layout: TableLayout
    private var mach = 0.0
    private var gamma = 1.4
    private var p_p0 = 0.0
    private var t_t0 = 0.0
    private var pstar = 0.0
    private var tstar = 0.0
    private var astar = 0.0
    private var pmangle = 0.0
    private var machangle = 0.0
    private var rhostar = 0.0
    private var rho_0 = 0.0
    private lateinit var calc: FloatingActionButton
    private lateinit var MACH: TextView
    private lateinit var GAMMA: EditText
    private lateinit var P_P0: TextView
    private lateinit var T_T0:TextView
    private lateinit var PSTAR: TextView
    private lateinit var TSTAR:TextView
    private lateinit var ASTAR: TextView
    private lateinit var PMANGLE: TextView
    private lateinit var MANGLE: TextView
    private lateinit var RHOSTAR: TextView
    private lateinit var RHO_0: TextView
    private lateinit var INPUT: EditText
    private var input: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.isentropic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layout = view.findViewById(R.id.isentropic_container) as TableLayout
        MACH = layout.findViewById(R.id.mach)
        GAMMA = layout.findViewById(R.id.gamma)
        P_P0 = layout.findViewById(R.id.p_p0)
        T_T0 = layout.findViewById(R.id.t_t0)
        PSTAR = layout.findViewById(R.id.p_pstar)
        TSTAR = layout.findViewById(R.id.t_tstar)
        ASTAR = layout.findViewById(R.id.a_astar)
        PMANGLE = layout.findViewById(R.id.pm_ang)
        MANGLE = layout.findViewById(R.id.m_ang)
        RHOSTAR = layout.findViewById(R.id.rho_star)
        RHO_0 = layout.findViewById(R.id.rho0)
        INPUT = layout.findViewById(R.id.Input)
        val spinner: Spinner = layout.findViewById(R.id.spinner)
        val spinner_mode = layout.findViewById<Spinner>(R.id.spinner2)

        var selectedOption= 0

        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, pos: Int, id: Long) {
                if (parent != null) {
                    selectedOption = parent.selectedItemPosition
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        spinner_mode.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, pos: Int, id: Long) {
                if (parent != null) {
                  var  selectedMode = parent.selectedItemPosition
                    if (selectedMode==1){
                        val fragmentManager = childFragmentManager
                        val fragmentTransaction = fragmentManager.beginTransaction()
                        val fragment = NSR()
                        fragmentTransaction.replace(R.id.fragment_container, fragment)
                        fragmentTransaction.commit()
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        calc =layout.findViewById(R.id.floatingActionButton)

        calc.setOnClickListener {

            calculate(selectedOption)
        }


    }

    private fun calculate(selectedOption: Int) {

        val obj = Functions()
        gamma = (GAMMA.text.toString()).toDouble()
        input = (INPUT.text.toString()).toDouble()


        when (selectedOption) {
            0 -> mach = input

            1 -> {
                p_p0 = input
                mach = Math.sqrt(2 * ((1 / Math.pow(input, (gamma - 1) / gamma)) - 1) / (gamma - 1))
            }
            2 -> {
                t_t0 = input
                mach = Math.sqrt(2 * ((1 / input) - 1) / (gamma - 1))
            }


            3 -> {
                rho_0 = input
                mach = Math.sqrt(2 * ((1 / Math.pow(input, (gamma - 1))) - 1) / (gamma - 1))
            }
            4 -> {

                machangle = input
                mach = 1 / Math.sin(input * 3.14159265359 / 180)
            }
            5 -> {
                pmangle = input
                var numax = (Math.sqrt((gamma + 1) / (gamma - 1)) - 1) * 90
                if (input <= 0.0 || input >= numax) {

                    return
                }
                var mnew = 2.0
                mach = 0.0
                while (Math.abs(mnew - mach) > 0.00001) {
                    mach = mnew
                    var fm = (obj.nu(gamma, mach) - input) * 3.14159265359 / 180
                    var fdm =
                        Math.sqrt(mach * mach - 1) / (1 + 0.5 * (gamma - 1) * mach * mach) / mach
                    mnew = mach - fm / fdm
                }
            }
            6 -> {
                astar = input
                var mnew = 2.0
                mach = 0.0
                while (Math.abs(mnew - mach) > 0.000001) {
                    mach = mnew
                    var phi = obj.aas(gamma, mach)
                    var s = (3 - gamma) / (1 + gamma)
                    mnew = mach - (phi - input) / (Math.pow(phi * mach, s) - phi / mach)
                }
            }
            7 -> {
                astar = input
                var mnew = 0.00001
                mach = 0.0
                while (Math.abs(mnew - mach) > 0.000001) {
                    mach = mnew
                    var phi = obj.aas(gamma, mach)
                    var s = (3 - gamma) / (1 + gamma)
                    mnew = mach - (phi - input) / (Math.pow(phi * mach, s) - phi / mach)
                }
            }
        }
        machangle = asin(1.0/mach) *180/3.14159265359
        pmangle = obj.nu(gamma,mach)
        t_t0 = obj.tt0(gamma, mach)
        p_p0 = obj.pp0(gamma, mach)
        rho_0 = obj.rr0(gamma, mach)
        tstar = obj.tts(gamma, mach)
        pstar = obj.pps(gamma, mach)
        rhostar = obj.rrs(gamma, mach)
        astar = obj.aas(gamma, mach)

        MACH.text = mach.toString()
        T_T0.text = t_t0.toString()
        TSTAR.text = tstar.toString()
        P_P0.text = p_p0.toString()
        PSTAR.text = pstar.toString()
        RHO_0.text = rho_0.toString()
        RHOSTAR.text = rhostar.toString()
        ASTAR.text = astar.toString()
        MANGLE.text = machangle.toString()
        PMANGLE.text = pmangle.toString()
        GAMMA.setText(gamma.toString())
        INPUT.setText(input.toString())

    }




}
