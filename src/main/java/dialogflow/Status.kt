package dialogflow

data class Status (
    val code: Int = 0,
    val message: String? = null,
    val details: Array<Any>? = null
)
