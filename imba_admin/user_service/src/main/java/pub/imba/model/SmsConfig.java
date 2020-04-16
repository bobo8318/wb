package pub.imba.model;

import lombok.Data;

@Data
public class SmsConfig {

    private String accessKeyId;
    private String  accessSecret;
    private String   signName;
    private String templateCode;
}
