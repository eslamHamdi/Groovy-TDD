package petros.efthymiou.groovy.ui.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import petros.efthymiou.groovy.domain.PlayList

class PlayListsAdapter: ListAdapter<PlayList, PlayListsAdapter.PlayListsViewHolder>(DiffCallBack) {



    class PlayListsViewHolder(val view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayListsViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: PlayListsViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    companion object  DiffCallBack: DiffUtil.ItemCallback<PlayList>()
    {
        override fun areItemsTheSame(oldItem: PlayList, newItem: PlayList): Boolean {

            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PlayList, newItem: PlayList): Boolean {
            return oldItem == newItem
        }

    }
}