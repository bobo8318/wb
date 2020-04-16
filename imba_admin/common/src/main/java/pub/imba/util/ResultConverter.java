package pub.imba.util;

import com.luo.erlei.comm.out.Ret;

public class ResultConverter {

    public <T> ResultEntry<T> ret2ResultEntry(Ret<T> ret){
        if(ret!=null){
            if(ret.ok()){
                ResultEntry resultEntry = ResultEntryFactory.getSuccessEntry(ret.msg);
                resultEntry.setData(ret.d);
                resultEntry.setParam0(""+ret.getCode());

                return resultEntry;
            }else{
                ResultEntry resultEntry = ResultEntryFactory.getInstance();
                resultEntry.setStatus(ret.getCode());
                resultEntry.setData(ret.d);

                return resultEntry;
            }
        }
        return null;
    }
}
