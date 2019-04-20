package dialogflow

data class DialogFlowResponse(
    val responseId: String?= null,
    val queryResult: QueryResult?= null,
    val status: Status? = null
)
