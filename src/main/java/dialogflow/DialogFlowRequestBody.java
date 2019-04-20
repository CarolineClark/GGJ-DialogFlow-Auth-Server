package dialogflow;

public class DialogFlowRequestBody {
    public QueryInput queryInput;
    public static class QueryInput {
        public TextInput text;
        //public InputAudioConfig audioConfig;
        public static class TextInput {
            public String text;
            public String languageCode;
        }
    }
}
