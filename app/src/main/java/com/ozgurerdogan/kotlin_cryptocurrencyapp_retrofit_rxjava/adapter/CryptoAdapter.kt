package com.ozgurerdogan.kotlin_cryptocurrencyapp_retrofit_rxjava.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ozgurerdogan.kotlin_cryptocurrencyapp_retrofit_rxjava.databinding.ActivityMainBinding
import com.ozgurerdogan.kotlin_cryptocurrencyapp_retrofit_rxjava.databinding.CryptoRecyclerRowBinding
import com.ozgurerdogan.kotlin_cryptocurrencyapp_retrofit_rxjava.model.CryptoModel

class CryptoAdapter(val cryptList:ArrayList<CryptoModel>): RecyclerView.Adapter<CryptoAdapter.CryptoHolder>() {

    class CryptoHolder(val binding: CryptoRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
        val view=CryptoRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CryptoHolder(view)
    }


    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {
        holder.binding.curenncyTxt.text=cryptList[position].currency
        holder.binding.priceTxt.text=cryptList[position].price
    }


    override fun getItemCount(): Int {
        return cryptList.size
    }
}