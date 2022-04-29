package com.example.testapplication.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.R
import com.example.testapplication.databinding.CurrencyItemBinding
import com.example.testapplication.models.Currency
import com.example.testapplication.models.Rates

class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {
    private val data = mutableListOf<Rates>()

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val binding = CurrencyItemBinding.bind(item)

        @SuppressLint("SetTextI18n")
        fun bind(item: Rates) {
            //base currency
            //
            with(binding) {
                one.text = "AUD " + item.AUD.toString()
                two.text = "CAD " + item.CAD.toString()
                three.text = "MXN " + item.MXN.toString()
                four.text = "PLN " + item.PLN.toString()
                five.text = "USD " + item.USD.toString()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item =
            LayoutInflater.from(parent.context).inflate(R.layout.currency_item, parent, false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount() = data.size


    @SuppressLint("NotifyDataSetChanged")
    fun setItems(list: List<Rates>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }
}