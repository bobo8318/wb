package pub.imba.model;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.imba.util.TextUtil;

@Data
public class MyWatchPo {

    private  final static Logger logger = LoggerFactory.getLogger(MyWatchPo.class);

    private int id;
    private String userid;
    private String proid;
    private String shopid;
    private String watchdate;
    public boolean watched;

    @Override
    public boolean equals(Object obj) {
        MyWatchPo po = (MyWatchPo)obj;
        if(!TextUtil.isEmpty(po.proid)){
            return  po.proid.equals(this.proid);
        }else if(!TextUtil.isEmpty(po.shopid)){
            return po.shopid.equals(this.shopid);
        }
        return false;
    }

    @Override
    public int hashCode() {
        if(!TextUtil.isEmpty(proid)){
            return proid.hashCode();
        }else{
            return shopid.hashCode();
        }
    }
}
