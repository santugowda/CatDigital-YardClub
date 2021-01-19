package com.example.catdigital_yardclub.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catdigital_yardclub.R
import com.example.catdigital_yardclub.model.Catalog
import kotlinx.android.synthetic.main.catalog_list_item.view.*

class CatalogAdapter(
    private val catalogNameList: ArrayList<Catalog>,
    private val clickListener: OnCatalogEventListener
) : RecyclerView.Adapter<CatalogAdapter.CatalogItemsViewHolder>() {

    interface OnCatalogEventListener {
        fun onCatalogSelected(catalog: Catalog)
    }

    fun addAll(catalogList: List<Catalog>) {
        val size = catalogNameList.size
        catalogNameList.clear()
        catalogNameList.addAll(catalogList);
        notifyItemRangeRemoved(0, size);
        notifyItemRangeInserted(0, catalogList.size);
    }

    fun reset() {
        catalogNameList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogItemsViewHolder {
        return CatalogItemsViewHolder(
            (LayoutInflater.from(parent.context).inflate(R.layout.catalog_list_item, parent, false))
        )
    }

    override fun onBindViewHolder(holder: CatalogItemsViewHolder, position: Int) {
        holder.bind(catalogNameList[position])
    }

    override fun getItemCount(): Int {
        return catalogNameList.size
    }

    inner class CatalogItemsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(catalog: Catalog) {
            with(itemView) {
                catalogName.text =
                    catalog.name
                setOnClickListener {
                    clickListener.onCatalogSelected(catalog)
                }
            }
        }
    }
}