package com.example.catdigital_yardclub.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catdigital_yardclub.R
import com.example.catdigital_yardclub.model.Results
import kotlinx.android.synthetic.main.result_list_item.view.*

class ResultAdapter(
    private val catalogItemsList : ArrayList<Results>,
) : RecyclerView.Adapter<ResultAdapter.CatalogItemsViewHolder>() {

    fun addAll(results: List<Results>) {
        val size = catalogItemsList.size
        catalogItemsList.clear()
        catalogItemsList.addAll(results);
        notifyItemRangeRemoved(0, size);
        notifyItemRangeInserted(0, results.size);
    }

    fun reset() {
        catalogItemsList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogItemsViewHolder {
        return CatalogItemsViewHolder(
            (LayoutInflater.from(parent.context).inflate(R.layout.result_list_item, parent, false))
        )
    }

    override fun onBindViewHolder(holder: CatalogItemsViewHolder, position: Int) {
        holder.bind(catalogItemsList[position])
    }

    override fun getItemCount(): Int {
        return catalogItemsList.size
    }

    inner class CatalogItemsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(results: Results) {
            with(itemView) {
                resultName.text = context.getString(R.string.display_name).plus(results.name)
                resultDesc.text = context.getString(R.string.description_details).plus(results.description)
                monthlyPrice.text = context.getString(R.string.monthly_price).plus(results.monthlyRate)
            }
        }
    }
}