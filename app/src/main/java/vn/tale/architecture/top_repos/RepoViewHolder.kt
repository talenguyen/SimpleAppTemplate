package vn.tale.architecture.top_repos

import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_repo_list.view.*
import vn.tale.architecture.R
import vn.tale.architecture.common.ImageLoader
import vn.tale.architecture.data.model.Repo
import vn.tale.architecture.util.inflate
import vn.tiki.noadapter2.AbsViewHolder

/**
 * Created by Giang Nguyen on 1/15/17.
 */
class RepoViewHolder(itemView: View, private val imageLoader: ImageLoader) : AbsViewHolder(
    itemView) {
  init {
    registerOnClickOn(itemView)
  }

  override fun bind(item: Any?) = with(itemView) {
    super.bind(item)
    if (item !is Repo) return

    val repo = item
    imageLoader.downloadInto(repo.owner.avatarUrl, ivOwnerAvatar)
    tvName.text = repo.name
    tvDescription.text = repo.description
    tvStars.text = "${repo.stargazersCount}"
    tvForks.text = "${repo.forksCount}"
  }

  override fun unbind() = with(itemView) {
    super.unbind()
    imageLoader.cancel(ivOwnerAvatar)
  }

  companion object {
    fun create(parent: ViewGroup, imageLoader: ImageLoader): RepoViewHolder {
      val view = parent.inflate(R.layout.item_repo_list)
      return RepoViewHolder(view, imageLoader)
    }
  }
}
