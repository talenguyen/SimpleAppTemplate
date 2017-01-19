package vn.tale.architecture.util

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Giang Nguyen on 1/16/17.
 */
fun ViewGroup.inflate(@LayoutRes layoutId: Int): View = LayoutInflater.from(this.context).inflate(
    layoutId, this, false)
