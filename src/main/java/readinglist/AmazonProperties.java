package readinglist;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("amazon")
public class AmazonProperties {

    private String associateID;

    void setAssociateID(String associateID) {
        this.associateID = associateID;
    }

    String getAssociateID() {
        return this.associateID;
    }
}
