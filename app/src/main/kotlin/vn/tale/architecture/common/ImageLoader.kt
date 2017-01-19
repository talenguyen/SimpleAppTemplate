package vn.tale.architecture.common

import android.widget.ImageView

interface ImageLoader {

  fun downloadInto(url: String, imageView: ImageView)

  fun cancel(imageView: ImageView)
}