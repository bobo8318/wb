controller = new Vue({
    el: "#wrapper",
    data() {
        return {
            shopform:{

                size: 10,
                sort: "",
                q: "",
                c1: "",
                c2: "",
                c3: "",
                lastValue: "",
                lastId: "",
                page:1,
                ids:[],


                ordertype:0

            },
            proform:{

                c1: "",
                c2: "",
                c3: "",
               
                status: 0,
    
                size: 10,
                page:1,
                sort: "",
                lastvalue: "",
                lastId: "",
                priceFrom: 0,
                priceTo: 0,
                q: "",
                saleDateFrom: "",
                saleDateTo: "",
               
                saleVolumeFrom: 0,
                saleVolumeTo: 0,
                ids:[],


                ordertype:0

            },
            shopdatas: [],
            productdatas: [],
            sortby: "client",

        }
    },
    created() {
        this.getShopWatchDatas();
        this.getProductWatchDatas();
    },
    methods: {
        shopwatchact(index, shopid, watchstatus){
            //console.info(index+" "+ shopid+ " "+ watchstatus);
            axios.post(watchshop_url,{
                'shopid': shopid,
                'watched': false
            }).then(res => {
                if(res.status == 200 && res.data.code >=0){
                    this.shopdatas.splice(index,1);
                }
            });
            this.shopdatas[index].iwatch = watchstatus;
        },
        productwatchact(index, prouctid, watchstatus){
            //console.info(index+" "+ prouctid+ " "+ watchstatus);
            
            this.$confirm('确定要取消收藏吗?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(() => {

                axios.post(watchpro_url, {
                    'proid': prouctid,
                    'watched': false
                }).then(res =>{
                    this.productdatas.splice(index,1);
                });
                this.productdatas[index].iwatch = watchstatus;
                
            }).catch(() => {
                console.info("cancle");
            });
            
            
        },
        getShopWatchDatas(){
            axios.post(watchshoplist_url,this.shopform).then(res => {
               
                if(res.status == 200 && res.data.code >= 0){
                    
                    this.shopdatas = res.data.d;
                    this.shopform.size = res.data.code;
                    
                }
            });
        },
        getProductWatchDatas(){
            axios.post(watchprolist_url,this.proform).then(res => {
               
                if(res.status == 200 && res.data.code >= 0){
                    this.productdatas = res.data.d;
                    this.proform.size = res.data.code;
                }
            });
        },
        shopturnPage(){

        },
        proturnPage(){

        },
       
       
    },

    computed: {
        shopdatastotal(){
            //console.info(this.shopdatas.length);
            return this.shopdatas.length;
        },
        prodatastotal(){
            //console.info(this.productdatas.length);
            return this.productdatas.length;
        }
    },
});