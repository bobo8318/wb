package pub.imba.model;

import com.luo.erlei.comm.out.ProdItem;
import lombok.Data;

@Data
public class MyProdItem extends ProdItem {

    protected int iwatch;

    public int getIwatch() {
        return iwatch;
    }

    public void setIwatch(int iwatch) {
        this.iwatch = iwatch;
    }

    public void clone(ProdItem item) {

        this.cat = item.cat;
        this.discount_price = item.discount_price;
        this.image = item.image;
    }
}
