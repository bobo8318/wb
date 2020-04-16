package pub.imba.model;

import lombok.Data;

@Data
public class MyRet<T> {

    private String msg;
    private T d;
    private int code;
}
