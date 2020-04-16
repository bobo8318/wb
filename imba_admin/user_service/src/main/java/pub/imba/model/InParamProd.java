package pub.imba.model;

import lombok.Data;

@Data
public class InParamProd extends InParam {

    public String saleDateFrom;
    public String saleDateTo;
    public String priceFrom;
    public String priceTo;
    public String saleVolumeFrom;
    public String saleVolumeTo;

}
