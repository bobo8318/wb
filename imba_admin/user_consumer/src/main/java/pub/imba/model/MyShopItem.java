package pub.imba.model;

import com.luo.erlei.comm.out.ShopItem;
import lombok.Data;

@Data
public class MyShopItem extends ShopItem {

    protected int iwatch;

    public int getIwatch() {
        return iwatch;
    }

    public void setIwatch(int iwatch) {
        this.iwatch = iwatch;
    }

    public void clone(ShopItem item) {
        this.company_name = item.company_name;
        this.image = item.image;
        this.name = item.name;
        this.online_prods = item.online_prods;
        this.salesVolume1 = item.salesVolume1;
        this.salesVolume3 = item.salesVolume3;
        this.salesVolume7 = item.salesVolume7;
        this.salesVolume30 = item.salesVolume30;
        this.tid = item.tid;
    }
}
