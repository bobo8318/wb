package pub.imba.model;

public class SmsResponseData {

    /**
     * Message : OK
     * RequestId : 1AEDD411-0330-4E30-A429-F66C9C9ED9F8
     * BizId : 176321977286518484^0
     * Code : OK
     */

    private String Message;
    private String RequestId;
    private String BizId;
    private String Code;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String RequestId) {
        this.RequestId = RequestId;
    }

    public String getBizId() {
        return BizId;
    }

    public void setBizId(String BizId) {
        this.BizId = BizId;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }
}
