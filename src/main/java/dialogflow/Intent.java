package dialogflow;

import lombok.Data;

@Data
public class Intent {
    String name;
    String displayName;
    Boolean isFallback;
}
