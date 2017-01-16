package vn.tale.architecture.top_repos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import vn.tale.architecture.R
import vn.tale.architecture.data.model.Repo
import vn.tiki.noadapter2.AbsViewHolder

/**
 * Created by Giang Nguyen on 1/15/17.
 */
class RepoViewHolder(itemView: View) : AbsViewHolder(itemView) {
  init {
    registerOnClickOn(itemView)
  }

  override fun bind(item: Any?) {
    super.bind(item)
    if (item !is Repo) return

    val repo = item as Repo
  }
  companion object {
    fun create(parent: ViewGroup): RepoViewHolder {
      val inflater = LayoutInflater.from(parent.context)
      val view = inflater.inflate(R.layout.item_repo_list, parent, false)
      return RepoViewHolder(view)
    }
  }
}
