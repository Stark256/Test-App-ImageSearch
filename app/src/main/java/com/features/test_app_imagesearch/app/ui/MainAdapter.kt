package com.features.test_app_imagesearch.app.ui

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.features.test_app_imagesearch.R
import com.features.test_app_imagesearch.db.models.ImageDBModel
import kotlinx.android.synthetic.main.item_image.view.*
import java.io.ByteArrayOutputStream

class MainAdapter(private val callback: AdapterCallback): RecyclerView.Adapter<MainAdapter.ImageViewHolder>() {

    private lateinit var context: Context
    private val dataArr = ArrayList<ImageDBModel>()

    private var imageObjectID : Int? = null

    fun addSingleObject(imageDBModel: ImageDBModel) {
        imageObjectID = imageDBModel.id
        dataArr.add(imageDBModel)
        dataArr.sortByDescending { it.id }
        notifyDataSetChanged()
    }

    fun replaceAll(arr: ArrayList<ImageDBModel>) {
        dataArr.clear()
        if(arr.isNotEmpty()) {
            dataArr.addAll(arr)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        this.context = parent.context
        return ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_image, parent, false))
    }

    override fun getItemCount(): Int {
        return dataArr.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = dataArr[position]

        Glide.with(context)
            .load(item.image)
            .centerCrop()
            .into(holder.image)

        if(imageObjectID != null && item.id == imageObjectID){

            Glide.with(context)
                .asBitmap()
                .load(item.imageLink)
                .centerCrop()
                .listener(object : RequestListener<Bitmap> {
                    override fun onLoadFailed(e: GlideException?, model: Any?,
                        target: Target<Bitmap>?, isFirstResource: Boolean): Boolean { return false }

                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        resource?.let {
                            val baos = ByteArrayOutputStream()
                            it.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                            callback.onImageLoaded(baos.toByteArray())
                        }
                        return false
                    }
                }).into(holder.image)
        } else {
            Glide.with(context)
                .load(item.image)
                .centerCrop()
                .into(holder.image)
        }


        holder.name.text = item.imageName
    }

    class ImageViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val image: ImageView = v.iv_saved_image
        val name: TextView = v.tv_image_name
    }

    interface AdapterCallback{
        fun onImageLoaded(byteArray: ByteArray)
    }
}