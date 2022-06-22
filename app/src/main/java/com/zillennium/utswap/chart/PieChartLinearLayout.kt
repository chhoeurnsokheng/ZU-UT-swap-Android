package com.zillennium.utswap.chart

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.core.content.ContextCompat
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import java.math.RoundingMode
import java.text.DecimalFormat


class PieChartLinearLayout : LinearLayout {

    private var txtUtProject: TextView? = null
    private var txtTrading: TextView? = null
    private var imageView: ImageView? =null

    @SuppressLint("ComposableNaming")
    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int = 0) : super(
        context,
        attrs,
        defStyleAttr
    ) {
       init()
    }

    private fun init(){
        val parentView: View =
            LayoutInflater.from(context).inflate(R.layout.layout_portfolio_chart_pie, this, true)
        txtUtProject = parentView.findViewById(R.id.txt_percent_ut_projects)
        txtTrading = parentView.findViewById(R.id.txt_percent_trading_balance)
        imageView = parentView.findViewById(R.id.imageView)
    }

    fun setDataOfChart(listData: ArrayList<Double>?){
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.DOWN



        txtTrading?.text = String.format("%.2f",listData?.get(1))
        txtUtProject?.text = String.format("%.2f",listData?.get(0))

        val totalData = listData?.get(1)?.plus(listData[0])
        Log.d("test", totalData.toString())

        val bitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        val paintUTProject = Paint()

        paintUTProject.color = ContextCompat.getColor(UTSwapApp.instance, R.color.yellow)

        paintUTProject.style = Paint.Style.FILL_AND_STROKE

        val paintTradingBalance = Paint()

        paintTradingBalance.color = ContextCompat.getColor(UTSwapApp.instance, R.color.blue)

        paintTradingBalance.style = Paint.Style.FILL_AND_STROKE

        val pi = RectF()
        pi.left = 0F
        pi.top = 0F
        pi.right = 1000F
        pi.bottom= 1000F

        val utProjectSweepAngle = (listData?.get(0)?.times(360))?.div(totalData!!)!!.toFloat()
        val tradingBalanceSweepAngle = (listData[1].times(360)).div(totalData!!).toFloat()

        canvas.drawArc(pi, 270f, tradingBalanceSweepAngle, true, paintTradingBalance)

        //start angle next = 270 + sweep of the last angle
        val startAngleUtProject = 270 + tradingBalanceSweepAngle
        canvas.drawArc(pi, startAngleUtProject,  utProjectSweepAngle, true, paintUTProject)

        imageView?.setImageBitmap(bitmap)
    }
}