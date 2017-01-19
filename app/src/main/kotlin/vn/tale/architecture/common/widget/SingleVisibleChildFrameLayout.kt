package vn.tale.architecture.common.widget

import android.content.Context
import android.support.annotation.IdRes
import android.support.v4.util.ArrayMap
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.widget.FrameLayout

/**
 * Created by Giang Nguyen on 12/27/16.
 */

class SingleVisibleChildFrameLayout(context: Context, attrs: AttributeSet) : FrameLayout(context,
    attrs) {

  private var showing: View? = null
  private val inflatedViews = ArrayMap<Int, View>()

  override fun addView(child: View, params: ViewGroup.LayoutParams) {
    super.addView(child, params)
    cacheView(child)
  }

  override fun addView(child: View) {
    super.addView(child)
    cacheView(child)
  }

  fun show(@IdRes id: Int) {
    var view: View
    if (inflatedViews.containsKey(id)) {
      view = inflatedViews[id]!!
    } else {
      view = findViewById(id)
      if (view is ViewStub) {
        view = view.inflate()
      }
      inflatedViews.put(id, view)
    }
    if (view == showing) {
      return
    }
    hideShowing()
    show(view)
  }

  private fun cacheView(child: View) {
    child.visibility = View.GONE
    if (child.id > 0) {
      inflatedViews.put(child.id, child)
    }
  }

  private fun show(view: View) {
    view.visibility = View.VISIBLE
    showing = view
  }

  private fun hideShowing() {
    showing?.visibility = View.GONE
  }
}
