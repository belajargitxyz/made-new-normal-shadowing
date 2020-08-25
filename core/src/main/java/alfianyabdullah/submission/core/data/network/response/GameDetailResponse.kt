package alfianyabdullah.submission.core.data.network.response

import com.google.gson.annotations.SerializedName

data class GameDetailResponse(

	@field:SerializedName("publishers")
	val publishers: List<PublishersItem>,

	@field:SerializedName("description_raw")
	val descriptionRaw: String
)

data class PublishersItem(

	@field:SerializedName("name")
	val name: String
)
