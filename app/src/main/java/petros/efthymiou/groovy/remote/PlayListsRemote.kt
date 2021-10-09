package petros.efthymiou.groovy.remote

import com.google.gson.annotations.SerializedName
import petros.efthymiou.groovy.domain.PlayList

data class PlayListsRemote(

	@field:SerializedName("PlayListsRemote")
	val playListsRemote: List<PlayListsRemoteItem?>? = null
)

data class PlayListsRemoteItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("category")
	val category: String? = null
)

 fun List<PlayListsRemoteItem?>?.toDomain():List<PlayList>
 {
	 return this?.map {

	 	PlayList(category = it?.category!!,name = it.name!!,id = it.id!!)
	 }!!
 }
