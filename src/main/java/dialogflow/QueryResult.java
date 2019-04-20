package dialogflow;


import lombok.Data;

@Data
public class QueryResult {
    String queryText;
    String action;
    Message[] fulfillmentMessages;
    Parameters parameters;
    Boolean allRequiredParamsPresent;
    String fulfillmentText;
    Integer intentDetectionConfidence;
    String languageCode;
    Intent intent;

}
