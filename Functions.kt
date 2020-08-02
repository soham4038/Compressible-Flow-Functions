package com.astrodev.myapplication

import kotlin.math.sqrt

class Functions{

    fun TtTtSR(g:Double,m:Double): Double {
        return 2*(1+g)*Math.pow(m, 2.0)/Math.pow(1+g*Math.pow(m, 2.0), 2.0)*(1+((g-1)/2)*Math.pow(m,2.0))}

    fun TTSR(g:Double,m:Double): Double {
        return Math.pow(m, 2.0)*Math.pow(1+g, 2.0)/Math.pow((1+g*Math.pow(m, 2.0)), 2.0)}

    fun PPSR(g:Double,m:Double): Double {
        return (1+g)/(1+g*Math.pow(m, 2.0))}

    fun PtPtSR(g:Double,m:Double): Double {
        return (1+g)/(1+g*Math.pow(m, 2.0))*Math.pow((1+(g-1)/2*Math.pow(m, 2.0))/((g+1)/2),g/(g-1))}

    fun VVSR(g:Double,m:Double): Double {
        return (1+g)*Math.pow(m, 2.0)/(1+g*Math.pow(m, 2.0))}

    fun SmaxRR(g:Double,m:Double): Double {
        return -g/(g-1)*Math.log(Math.pow(m, 2.0)*Math.pow((g+1)/(1+g*Math.pow(m, 2.0)),(g+1)/g))}

    fun TTSF(g:Double,m:Double): Double {
        return ((g+1)/2)/(1+((g-1)/2)*Math.pow(m, 2.0))}

    fun PPSF(g:Double,m:Double): Double {
        return (1/m)*Math.sqrt(((g+1)/2)/(1+((g-1)/2)*Math.pow(m, 2.0)))}

    fun PTPTSF(g:Double,m:Double): Double {
        return (1.0/m)*Math.pow((1.0+((g-1.0)/2.0)*Math.pow(m,2.0))/((g+1.0)/2.0),((g+1.0)/(2.0*(g-1.0))))}

    fun VVSF(g:Double,m:Double): Double {
        return m*Math.sqrt(((g+1.0)/2.0)/(1.0+((g-1.0)/2.0)*Math.pow(m,2.0)))}

    fun fLmaxD(g:Double,m:Double): Double {
        return ((g+1)/(2*g))*Math.log(((g+1)/2)*Math.pow(m, 2.0)/(1+((g-1)/2)*Math.pow(m, 2.0)))+(1/g)*(1/Math.pow(m,2.0)-1)}

    fun SmaxRF(g:Double,m:Double): Double {
        return Math.log((1/m)*Math.pow((1.0+((g-1)/2)*Math.pow(m,2.0))/(1+((g-1)/2)),(g+1)/(2*(g-1))))}


    fun tt0(g: Double, m: Double): Double {
        return Math.pow(((1 + (g - 1) / 2 * m * m)), (-1.0))
    }

    fun pp0(g: Double, m: Double): Double {
        return Math.pow(((1 + (g - 1) / 2 * m * m)), (-g / (g - 1)))
    }

    fun rr0(g: Double, m: Double): Double {
        return Math.pow(((1 + (g - 1) / 2 * m * m)), (-1 / (g - 1)))
    }


    fun tts(g: Double, m: Double): Double {
        return tt0(g, m) * (g / 2 + 0.5)
    }

    fun pps(g: Double, m: Double): Double {
        return pp0(g, m) * Math.pow((g / 2 + .5), (g / (g - 1)))
    }

    fun rrs(g: Double, m: Double): Double {
        return rr0(g, m) * Math.pow((g / 2 + .5), (1 / (g - 1)))
    }

    fun aas(g: Double, m: Double): Double {
        return 1 / rrs(g, m) * sqrt(1 / tts(g, m)) / m
    }
    fun m2(g:Double,m1:Double): Double {
        return Math.sqrt((1 + 0.5 * (g - 1.0) * m1 * m1) / (g * m1 * m1 -0.5 * (g - 1)))}


    fun nu(g: Double, m: Double): Double {
        var n =
            Math.sqrt(((g + 1) / (g - 1))) * Math.atan(Math.sqrt(((g - 1) / (g + 1) * (m * m - 1))))
        n -= Math.atan(Math.sqrt((m * m - 1)))
        n = n * 180 / 3.14159265359
        return n
    }
}