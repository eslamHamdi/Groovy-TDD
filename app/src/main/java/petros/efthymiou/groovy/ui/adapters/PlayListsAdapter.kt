package petros.efthymiou.groovy.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import petros.efthymiou.groovy.R
import petros.efthymiou.groovy.databinding.PlaylistItemBinding
import petros.efthymiou.groovy.domain.PlayList

class PlayListsAdapter: ListAdapter<PlayList, PlayListsAdapter.PlayListsViewHolder>(DiffCallBack) {

private lateinit var binding: PlaylistItemBinding

    class PlayListsViewHolder(private val binding: PlaylistItemBinding):RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item:PlayList)
        {
            binding.playListImage.setImageResource(item.image)
            binding.playListCategory.text = item.category
            binding.playListName.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayListsViewHolder {
        binding = PlaylistItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return PlayListsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayListsViewHolder, position: Int) {
        val playList = getItem(position)
        holder.bind(playList)
        binding.itemContainer.setOnClickListener {
            clickListener?.clicked(playList.id)
        }
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

    var clickListener:ItemClickListener? = null

    interface ItemClickListener
    {
        fun clicked(id:String)
    }
}