package com.example.catdigital_yardclub.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.catdigital_yardclub.R
import com.example.catdigital_yardclub.model.CatalogItems
import kotlinx.android.synthetic.main.catalog_list_item.view.*
import kotlinx.android.synthetic.main.subcategory_list_item.view.*

class SubcategoriesAdapter(
    private val catalogItemsList : ArrayList<CatalogItems>,
    private val subcategoryEventListener: OnSubcategoryEventListener
) : RecyclerView.Adapter<SubcategoriesAdapter.CatalogItemsViewHolder>() {

    interface OnSubcategoryEventListener {
        fun onSubcategorySelected(catalogItems: CatalogItems)
    }

    fun addAll(subcategoryList: List<CatalogItems>) {
        val size = catalogItemsList.size
        catalogItemsList.clear()
        catalogItemsList.addAll(subcategoryList);
        notifyItemRangeRemoved(0, size);
        notifyItemRangeInserted(0, subcategoryList.size);
    }

    fun reset() {
        catalogItemsList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogItemsViewHolder {
        return CatalogItemsViewHolder(
            (LayoutInflater.from(parent.context).inflate(R.layout.subcategory_list_item, parent, false))
        )
    }

    override fun onBindViewHolder(holder: CatalogItemsViewHolder, position: Int) {
        holder.bind(catalogItemsList[position])
    }

    override fun getItemCount(): Int {
        return catalogItemsList.size
    }

    inner class CatalogItemsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(catalogItems: CatalogItems) {
            with(itemView) {
                displayName.text =
                    context.getString(R.string.display_name).plus(catalogItems.display_name)
                displayDetails.text =
                    context.getString(R.string.display_details).plus(catalogItems.detail)
                setOnClickListener {
                    subcategoryEventListener.onSubcategorySelected(catalogItems)
                }
            }
        }
    }
}