package petros.efthymiou.groovy.remote

import com.google.gson.annotations.SerializedName
import petros.efthymiou.groovy.domain.DomainListDetails

data class ListDetails(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("details")
	val details: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)


 fun ListDetails.toDomain():DomainListDetails
 {
 	return DomainListDetails(this.id,this.name,this.details)
 }
