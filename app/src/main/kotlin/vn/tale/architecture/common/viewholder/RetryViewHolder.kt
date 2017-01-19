package vn.tale.architecture.common.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import vn.tale.architecture.R
import vn.tiki.noadapter2.AbsViewHolder

/**
 * Created by Giang Nguyen on 1/2/17.
 */
class RetryViewHolder private constructor(itemView: View) : AbsViewHolder(itemView) {
  companion object {
    fun create(parent: ViewGroup): RetryViewHolder {
      val inflater = LayoutInflater.from(parent.context)
      val view = inflater.inflate(R.layout.item_retry, parent, false)
      return RetryViewHolder(view)
    }
  }
}
