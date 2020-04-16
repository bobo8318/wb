const mainstat_url = "/data/brief";
const hotprod_url = "/data/prod/hot";
const hotshop_url = "/data/shop/hot";

const volume_url = "/data/cat/s-volume";
const value_url = "/data/cat/s-value";

const getnewstat_url = "/data/cat/new";

const tradeline_url = "/data/trend/sale";

const getcat_url = "/data/cat";

const watchpro_url = "/user/prod/watch";

const watchprolist_url = "/user/prod/watchs";
const watchshop_url = "/user/shop/watch";
const watchshoplist_url = "/user/shop/watchs";


const prolist_url = "/data/prod/list";
const shoplist_url = "/data/shop/list";


function checkResponse(response){

}

/*var mainstat = {
        totalProduct: 659,
        onSale: 869,
        mobileSale: 911,
        newProduct: 659,
        totalShop: 869,
        onlineShop: 911,
        mobileSaleShop: 659,
        newShop: 869
    }
module.exports = {mainstat}*/

function getLocalShopDatas(){
    return [
        {"id":1,'img':"2","name":"淘天然1","comp":"赣州市星橙电子商务有限公司","main":"生鲜-蔬菜-茄果瓜类","onsale":"17","day1":"3510","days3":"7019","days7":"45501","days30":"45502","allwatch":"122","iwatch":"0"},
        {"id":2,'img':"2","name":"淘天然2","comp":"赣州市星橙电子商务有限公司","main":"生鲜-蔬菜-茄果瓜类","onsale":"18","day1":"3511","days3":"7018","days7":"45502","days30":"45501","allwatch":"124","iwatch":"1"},
        {"id":1,'img':"2","name":"淘天然1","comp":"赣州市星橙电子商务有限公司","main":"生鲜-蔬菜-茄果瓜类","onsale":"17","day1":"3510","days3":"7019","days7":"45501","days30":"45502","allwatch":"122","iwatch":"0"},
        {"id":2,'img':"2","name":"淘天然2","comp":"赣州市星橙电子商务有限公司","main":"生鲜-蔬菜-茄果瓜类","onsale":"18","day1":"3511","days3":"7018","days7":"45502","days30":"45501","allwatch":"124","iwatch":"1"},
        {"id":1,'img':"2","name":"淘天然1","comp":"赣州市星橙电子商务有限公司","main":"生鲜-蔬菜-茄果瓜类","onsale":"17","day1":"3510","days3":"7019","days7":"45501","days30":"45502","allwatch":"122","iwatch":"0"},
        {"id":2,'img':"2","name":"淘天然2","comp":"赣州市星橙电子商务有限公司","main":"生鲜-蔬菜-茄果瓜类","onsale":"18","day1":"3511","days3":"7018","days7":"45502","days30":"45501","allwatch":"124","iwatch":"1"},
        {"id":1,'img':"2","name":"淘天然1","comp":"赣州市星橙电子商务有限公司","main":"生鲜-蔬菜-茄果瓜类","onsale":"17","day1":"3510","days3":"7019","days7":"45501","days30":"45502","allwatch":"122","iwatch":"0"},
        {"id":2,'img':"2","name":"淘天然2","comp":"赣州市星橙电子商务有限公司","main":"生鲜-蔬菜-茄果瓜类","onsale":"18","day1":"3511","days3":"7018","days7":"45502","days30":"45501","allwatch":"124","iwatch":"1"},
        {"id":1,'img':"2","name":"淘天然1","comp":"赣州市星橙电子商务有限公司","main":"生鲜-蔬菜-茄果瓜类","onsale":"17","day1":"3510","days3":"7019","days7":"45501","days30":"45502","allwatch":"122","iwatch":"0"},
        {"id":2,'img':"2","name":"淘天然2","comp":"赣州市星橙电子商务有限公司","main":"生鲜-蔬菜-茄果瓜类","onsale":"18","day1":"3511","days3":"7018","days7":"45502","days30":"45501","allwatch":"124","iwatch":"1"},
        {"id":1,'img':"2","name":"淘天然1","comp":"赣州市星橙电子商务有限公司","main":"生鲜-蔬菜-茄果瓜类","onsale":"17","day1":"3510","days3":"7019","days7":"45501","days30":"45502","allwatch":"122","iwatch":"0"},
        {"id":2,'img':"2","name":"淘天然2","comp":"赣州市星橙电子商务有限公司","main":"生鲜-蔬菜-茄果瓜类","onsale":"18","day1":"3511","days3":"7018","days7":"45502","days30":"45501","allwatch":"124","iwatch":"1"},
        {"id":1,'img':"2","name":"淘天然1","comp":"赣州市星橙电子商务有限公司","main":"生鲜-蔬菜-茄果瓜类","onsale":"17","day1":"3510","days3":"7019","days7":"45501","days30":"45502","allwatch":"122","iwatch":"0"},
        {"id":2,'img':"2","name":"淘天然2","comp":"赣州市星橙电子商务有限公司","main":"生鲜-蔬菜-茄果瓜类","onsale":"18","day1":"3511","days3":"7018","days7":"45502","days30":"45501","allwatch":"124","iwatch":"1"},
        {"id":1,'img':"2","name":"淘天然1","comp":"赣州市星橙电子商务有限公司","main":"生鲜-蔬菜-茄果瓜类","onsale":"17","day1":"3510","days3":"7019","days7":"45501","days30":"45502","allwatch":"122","iwatch":"0"},
        {"id":2,'img':"2","name":"淘天然2","comp":"赣州市星橙电子商务有限公司","main":"生鲜-蔬菜-茄果瓜类","onsale":"18","day1":"3511","days3":"7018","days7":"45502","days30":"45501","allwatch":"124","iwatch":"1"},
    ]
}

function getLocalProductDatas(){
    return [
        {"id":1,'img':"2","name":"【限时买一送一 领10元券】完美日记氨基酸卸妆水送卸妆湿巾1","protype":"化妆品","shop":"Perfect Diary完美日记旗舰店","resource":"鲁班","price":"¥138-¥198","day1":"36","days3":"1876","days7":"6803","salesvolume":"30000+","sales":"50万+","saledate":"2019-12-09","iwatch":"0"},
        {"id":2,'img':"2","name":"【限时买一送一 领10元券】完美日记氨基酸卸妆水送卸妆湿巾2","protype":"化妆品","shop":"Perfect Diary完美日记旗舰店","resource":"鲁班","price":"¥138-¥198","day1":"37","days3":"1875","days7":"6804","salesvolume":"20000+","sales":"51万+","saledate":"2019-12-10","iwatch":"1"},
    ]
}


function getLocalMainStat(){
    return  {
        totalProduct: 659,
        onSale: 869,
        mobileSale: 911,
        newProduct: 659,
        totalShop: 869,
        onlineShop: 911,
        mobileSaleShop: 659,
        newShop: 869
    };
}

function getLocalTopProduct(){
    return {
        yesterdayBest:[{"img":"/ceshi/1.jpg","desc":"【油污克星】3秒快速去除污渍，车内、卫生间、厨房，1厘米厚的老油垢,一喷净！","salesVolume":"6085"},
        {"img":"/ceshi/1.jpg","desc":"【油污克星】3秒快速去除污渍，车内、卫生间、厨房，1厘米厚的老油垢,一喷净！","salesVolume":"6085"},
        {"img":"/ceshi/1.jpg","desc":"【油污克星】3秒快速去除污渍，车内、卫生间、厨房，1厘米厚的老油垢,一喷净！","salesVolume":"6085"},
        {"img":"/ceshi/1.jpg","desc":"【油污克星】3秒快速去除污渍，车内、卫生间、厨房，1厘米厚的老油垢,一喷净！","salesVolume":"6085"},
        {"img":"/ceshi/1.jpg","desc":"【油污克星】3秒快速去除污渍，车内、卫生间、厨房，1厘米厚的老油垢,一喷净！","salesVolume":"6085"},    
                    ],
        newBest:[{"img":"/ceshi/1.jpg","desc":"【油污克星】3秒快速去除污渍，车内、卫生间、厨房，1厘米厚的老油垢,一喷净！","salesVolume":"6085"},
        {"img":"/ceshi/1.jpg","desc":"【油污克星】3秒快速去除污渍，车内、卫生间、厨房，1厘米厚的老油垢,一喷净！","salesVolume":"6085"},
        {"img":"/ceshi/1.jpg","desc":"【油污克星】3秒快速去除污渍，车内、卫生间、厨房，1厘米厚的老油垢,一喷净！","salesVolume":"6085"},
        {"img":"/ceshi/1.jpg","desc":"【油污克星】3秒快速去除污渍，车内、卫生间、厨房，1厘米厚的老油垢,一喷净！","salesVolume":"6085"},
        {"img":"/ceshi/1.jpg","desc":"【油污克星】3秒快速去除污渍，车内、卫生间、厨房，1厘米厚的老油垢,一喷净！","salesVolume":"6085"},    
                    ],
        everbest:[{"img":"/ceshi/1.jpg","desc":"【油污克星】3秒快速去除污渍，车内、卫生间、厨房，1厘米厚的老油垢,一喷净！","salesVolume":"6085"},
        {"img":"/ceshi/1.jpg","desc":"【油污克星】3秒快速去除污渍，车内、卫生间、厨房，1厘米厚的老油垢,一喷净！","salesVolume":"6085"},
        {"img":"/ceshi/1.jpg","desc":"【油污克星】3秒快速去除污渍，车内、卫生间、厨房，1厘米厚的老油垢,一喷净！","salesVolume":"6085"},
        {"img":"/ceshi/1.jpg","desc":"【油污克星】3秒快速去除污渍，车内、卫生间、厨房，1厘米厚的老油垢,一喷净！","salesVolume":"6085"},
        {"img":"/ceshi/1.jpg","desc":"【油污克星】3秒快速去除污渍，车内、卫生间、厨房，1厘米厚的老油垢,一喷净！","salesVolume":"6085"},    
                    ]
    }
}

function getLocalTopShop(){
    return {
        yesterdayBest:[{"img":"/ceshi/1.jpg","desc":"【油污克星】3秒快速去除污渍，车内、卫生间、厨房，1厘米厚的老油垢,一喷净！","salesVolume":"6085"},
        {"img":"/ceshi/1.jpg","desc":"【油污克星】3秒快速去除污渍，车内、卫生间、厨房，1厘米厚的老油垢,一喷净！","salesVolume":"6085"},
        {"img":"/ceshi/1.jpg","desc":"【油污克星】3秒快速去除污渍，车内、卫生间、厨房，1厘米厚的老油垢,一喷净！","salesVolume":"6085"},
        {"img":"/ceshi/1.jpg","desc":"【油污克星】3秒快速去除污渍，车内、卫生间、厨房，1厘米厚的老油垢,一喷净！","salesVolume":"6085"},
        {"img":"/ceshi/1.jpg","desc":"【油污克星】3秒快速去除污渍，车内、卫生间、厨房，1厘米厚的老油垢,一喷净！","salesVolume":"6085"},    
                    ],
        newBest:[{"img":"/ceshi/1.jpg","desc":"【油污克星】3秒快速去除污渍，车内、卫生间、厨房，1厘米厚的老油垢,一喷净！","salesVolume":"6085"},
        {"img":"/ceshi/1.jpg","desc":"【油污克星】3秒快速去除污渍，车内、卫生间、厨房，1厘米厚的老油垢,一喷净！","salesVolume":"6085"},
        {"img":"/ceshi/1.jpg","desc":"【油污克星】3秒快速去除污渍，车内、卫生间、厨房，1厘米厚的老油垢,一喷净！","salesVolume":"6085"},
        {"img":"/ceshi/1.jpg","desc":"【油污克星】3秒快速去除污渍，车内、卫生间、厨房，1厘米厚的老油垢,一喷净！","salesVolume":"6085"},
        {"img":"/ceshi/1.jpg","desc":"【油污克星】3秒快速去除污渍，车内、卫生间、厨房，1厘米厚的老油垢,一喷净！","salesVolume":"6085"},    
                    ],
        everbest:[{"img":"/ceshi/1.jpg","desc":"【油污克星】3秒快速去除污渍，车内、卫生间、厨房，1厘米厚的老油垢,一喷净！","salesVolume":"6085"},
        {"img":"/ceshi/1.jpg","desc":"【油污克星】3秒快速去除污渍，车内、卫生间、厨房，1厘米厚的老油垢,一喷净！","salesVolume":"6085"},
        {"img":"/ceshi/1.jpg","desc":"【油污克星】3秒快速去除污渍，车内、卫生间、厨房，1厘米厚的老油垢,一喷净！","salesVolume":"6085"},
        {"img":"/ceshi/1.jpg","desc":"【油污克星】3秒快速去除污渍，车内、卫生间、厨房，1厘米厚的老油垢,一喷净！","salesVolume":"6085"},
        {"img":"/ceshi/1.jpg","desc":"【油污克星】3秒快速去除污渍，车内、卫生间、厨房，1厘米厚的老油垢,一喷净！","salesVolume":"6085"},    
                    ]
    }
}


function getLocalSalePieData_volume(){
    return [
        ['Firefox',   45.0],
        ['IE',       26.8],
        ['Chrome', 12.8],
        ['Safari',    8.5],
        ['Opera',     6.2],
        ['Others',   0.7]
     ]
}

function getLocalLevelPieData(){
    return [
        ['Firefox',   45.0],
        ['IE',       26.8],
        ['Chrome', 12.8],
        ['Safari',    8.5],
        ['Opera',     6.2],
        ['Others',   0.7]
     ]
}


function getLocalLineChartData(){
    return {
        labels: ['2008', '2009', '2010', '2011', '2012', '2013', '2014', '2015'],
        series: [[5, 2, 7, 4, 5, 3, 5, 4]]
    }
}

function getLevel1Data(){
    return [
        {'id':1, 'name':'level1_01'},
        {'id':2, 'name':'level1_02'},
        {'id':3, 'name':'level1_03'}
    ]
    
}

function getLevel2Data(){

        return [
            {'id':4, 'name':'level2_01'},
            {'id':5, 'name':'level2_02'},
            {'id':6, 'name':'level2_03'}
        ]
}

function getLevel3Data(){
    return [
        {'id':7, 'name':'level3_01'},
        {'id':8, 'name':'level3_02'},
        {'id':9, 'name':'level3_03'}
    ]
}


function encodePhone(phone){
    if(checkPhone(phone)){
        let encodephone = phone.substring(0,5)+"****"+phone.substring(9,11);
        return encodephone;
    }else{
        return "";
    }
}

function checkPhone(phoneNo){
    if (!/^1[3456789]\d{9}$/.test(phoneNo)) {
        return false;
    }else
        return true;
    }


function formatLineChartData(datas){
    let labels = [];
    let series = [];
    if(datas){
        for(let i in datas) {
            console.info(datas[i].d);

            labels.push(datas[i].d);
            series.push(datas[i].v);
        }
    }

    return {
        'labels': labels,
        'series': [series]
    }
}



function formatPieData(datas){
    let resultdata = [];
    if(datas){
        for(let i in datas) {
            let data = [];
            data.push(i),
            data.push(datas[i]),
            resultdata.push(data);
        }
    }
    return resultdata;
    
}