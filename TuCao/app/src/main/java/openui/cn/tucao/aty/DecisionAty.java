package openui.cn.tucao.aty;

import android.app.Activity;
import android.os.Bundle;

import cn.openui.www.caodian.R;
import cn.openui.www.caodian.service.MainService;

/**
 * Created by My on 2016/11/8.
 */
public class DecisionAty extends Activity {

    private MainService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.decision_index_layout);
        service = new MainService(this);

        int result = service.getRandom(2);
    }
}
