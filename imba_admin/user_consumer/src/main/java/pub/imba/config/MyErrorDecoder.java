package pub.imba.config;

import feign.Response;
import feign.codec.ErrorDecoder;

public class MyErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        return null;
    }
}
