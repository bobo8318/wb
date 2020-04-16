controller = new Vue({
    el:"#wrapper",
    data:{
        searchForm:{

            size: 10,
            sort: 0,
            q: "",
            c1: "",
            c2: "",
            c3: "",
            lastValue: "",
            lastId: "",
            page:1,
            ids:[]

        },
        sortby: "server",
        showDatas:[],
        totalData: 0,
        recentDate: "",

    },
    created() {
        this.getDatas();
    },
    mounted() {
    },
    methods:{
        typeSelect(protype, level){
            if(level == 1){
                this.searchForm.c1 = protype;
                this.searchForm.c2 = "";
                this.searchForm.c3 = "";
            }
            if(level == 2){
                this.searchForm.c2 = protype;
                this.searchForm.c1 = "";
                this.searchForm.c3 = "";
            }
            if(level == 3){
                this.searchForm.c3 = protype;
                this.searchForm.c2 = "";
                this.searchForm.c1 = "";
            }
        },
        turnPage(value){
            //console.info("get data:",value);

            this.searchForm.page = value.page;
            this.searchForm.sort = value.sort;
            this.searchForm.size = value.limit;

            this.getDatas();
        },
        getDatas(){
            axios.post(shoplist_url, this.searchForm).then( res =>{
                if(res.status==200 && res.data.code >=0){
                    this.showDatas = res.data.d;
                    this.recentDate = res.data.msg;
                    this.totalData = res.data.code;
                }
            });
        },
        doSearch(){
            this.searchForm.page = 1;
            this.getDatas();
            //this.showDatas = getLocalShopDatas();
            //this.totalData = 16;
        },
        watchAction(index, id, watchstatus){
            //console.info(index+" "+id+" "+watchstatus);
           
            //console.info(this.showDatas);

            axios.post(watchshop_url,{
                    shopid: id,
                    watched: watchstatus
            }).then(res=>{
                if(res == 200 && res.data.code >=0 ){
                    let tempdata = this.showDatas[index];
                    tempdata.fav = watchstatus;
                    this.$set(this.showDatas,index, tempdata);
                }
            });
        }

       
    }
});