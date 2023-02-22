package com.junchi.coinandroid.ui.common

import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.junchi.coinandroid.R
import java.text.DecimalFormat
import kotlin.math.roundToInt

@BindingAdapter("withdrawal", "deposition", requireAll = true)
fun statusBackground(view: TextView, withdrawal: Int, deposition:Int) {

    when(setOf(withdrawal, deposition)){
        setOf(0, 0) -> view.background = ColorDrawable(view.context.getColor(R.color.red_900))
        setOf(0, 1), setOf(1, 0) -> view.background = ColorDrawable(view.context.getColor(R.color.yellow_a700))
        setOf(1, 1) -> view.background = ColorDrawable(view.context.getColor(R.color.green_900))
        else -> view.background = ColorDrawable(Color.WHITE)
    }
}

@BindingAdapter("priceAmount")
fun applyPriceFormat(view: TextView, price: String){

    val index = price.indexOf(".")
    val longPrice: Long = if(index == -1)
        price.toLong()
    else
        price.substring(0, index).toLong()


    val decimalFormat = DecimalFormat("#,###")
    view.text = view.context.getString(R.string.unit_discount_currency, decimalFormat.format(longPrice))
}

@BindingAdapter("fluctuations")
fun applyFluctations(view: TextView, rate: String){
    val floatFlutuat: Float = rate.toFloat()

    if (floatFlutuat > 0){
        view.text = view.context.getString(R.string.raise)
        view.setTextColor(view.context.getColor(R.color.red_900))
    } else {
        view.text = view.context.getString(R.string.decline)
        view.setTextColor(view.context.getColor(R.color.blue_900))
    }
}