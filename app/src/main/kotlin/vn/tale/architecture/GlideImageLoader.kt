package vn.tale.architecture

import android.widget.ImageView
import com.bumptech.glide.Glide
import vn.tale.architecture.common.ImageLoader

internal class GlideImageLoader : ImageLoader {
  override fun downloadInto(url: String, imageView: ImageView) {
    Glide.with(imageView.context)
        .load(url)
        .placeholder(R.drawable.ic_placeholder_24dp)
        .into(imageView)
  }

  override fun cancel(imageView: ImageView) {
    Glide.clear(imageView)
  }
}