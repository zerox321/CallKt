package com.eslam.callkt.util


import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.eslam.callkt.R


@BindingAdapter("loadImage")
fun loadImage(target: ImageView, url: String?) {
    val requestOptions = RequestOptions()
        .placeholder(R.mipmap.ic_launcher_round)
        .error(R.mipmap.ic_launcher_round)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .circleCrop()

    Glide.with(target)
        .applyDefaultRequestOptions(requestOptions)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(target)
}


