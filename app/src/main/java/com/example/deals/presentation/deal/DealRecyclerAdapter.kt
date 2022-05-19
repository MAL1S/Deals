package com.example.deals.presentation.deal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.deals.R
import com.example.deals.databinding.DealItemBinding
import com.example.deals.domain.model.Deal

class DealRecyclerAdapter(
    private val deals: List<Deal>,
    private val onDealClickedListener: OnDealClickedListener
) : RecyclerView.Adapter<DealRecyclerAdapter.DealRecyclerViewHolder>() {

    class DealRecyclerViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        private val onDealClickedListener: OnDealClickedListener
    ) : RecyclerView.ViewHolder(inflater.inflate(R.layout.deal_item, parent, false)) {

        private val binding by viewBinding(DealItemBinding::bind)

        fun bind(deal: Deal) {
            binding.tvDealName.text = deal.name
            itemView.setOnClickListener {
                onDealClickedListener.onDealClicked(deal = deal)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealRecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DealRecyclerViewHolder(inflater, parent, onDealClickedListener)
    }

    override fun onBindViewHolder(holder: DealRecyclerViewHolder, position: Int) {
        val deal: Deal = deals[position]
        holder.bind(deal)
    }

    override fun getItemCount(): Int = deals.size
}

