package com.example.catdigital.ui.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.catdigital_yardclub.R

class FeaturedImagesPagerAdapter(
    private val context: Context,
    private val imagesList: ArrayList<String>
) : RecyclerView.Adapter<FeaturedImagesPagerAdapter.ImageViewHolder>() {

    fun setImageList(featuredImagesList: ArrayList<String>) {
        imagesList.clear()
        imagesList.addAll(featuredImagesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageViewHolder {
        return ImageViewHolder(
            (LayoutInflater.from(context).inflate(R.layout.featured_images, parent, false))
        );
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.setImageView(imagesList[position])
    }

    override fun getItemCount(): Int = imagesList.size

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.featuredImages)
        private val progressBar: ProgressBar = itemView.findViewById(R.id.featuredImageProgressBar)
        fun setImageView(featuredImage: String?) {

            Glide.with(context)
                .load(featuredImage)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)
        }
    }
}