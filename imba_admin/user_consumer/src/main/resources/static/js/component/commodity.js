controller = new Vue({
    el:"#wrapper",
    data:{
        searchForm: {
            c1: "",
            c2: "",
            c3: "",
           
            status: 0,

            size: 10,
            page:1,
            sort: 0,
            lastvalue: "",
            lastId: "",
            priceFrom: 0,
            priceTo: 0,
            q: "",
            saleDateFrom: "",
            saleDateTo: "",
           
            saleVolumeFrom: 0,
            saleVolumeTo: 0,

        },
        totalData: 0,
        recentDate: "",
        sortby: "server",
        showdatas: [],


    },
    created() {
        this.getdatas();
        
    },
    mounted() {

    },
    computed: {

    },
    methods:{
        watchAction(index, id, watchstatus){
            //console.info(index+" "+id+" "+watchstatus);
           
           axios.post(watchpro_url,{
                proid: id,
                watched: watchstatus
            }).then(res=>{


                if(res.status == 200 && res.data.code >=0 ){

                    let tempdata = this.showdatas[index];
                    tempdata.fav = watchstatus;
                    this.$set(this.showdatas,index, tempdata);
                    
                }
            });
        },
        typeSelect(protype, level){
            //console.info(protype, level);
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
        getdatas(){
            axios.post(prolist_url,this.searchForm)
            .then(res=>{
                if(res.status==200 && res.data.code >=0){
                    this.showdatas = res.data.d;
                    this.recentDate = res.data.msg;
                    this.totalData = res.data.code;
                }
            }).catch(err=>{
                console.info(err);
            });
        },
        doSearch(){
            //console.info("do search:");
            //console.info(this.searchForm);
            //this.showdatas = getLocalProductDatas();
            this.searchForm.page = 1;
            this.getdatas();
           
        },

        searchPriceFrom(add){
            if(Number(add) != NaN){
                if(this.searchForm.priceFrom + Number(add) >=0){
                    this.searchForm.priceFrom += Number(add);
                }
            }
        },
        searchPriceTo(add){
            if(Number(add) != NaN){
                if(this.searchForm.priceTo + Number(add) >=0){
                    this.searchForm.priceTo += Number(add);
                }
            }
        },
        searchsaleVolumeFrom(add){
            if(Number(add) != NaN){
                if(this.searchForm.saleVolumeFrom + Number(add) >=0){
                    this.searchForm.saleVolumeFrom += Number(add);
                }
            }
        },
        searchsaleVolumeTo(add){
            if(Number(add) != NaN){
                if(this.searchForm.saleVolumeTo + Number(add) >=0){
                    this.searchForm.saleVolume += Number(add);
                }
            }
        },
        selectClick(){
            console.info("v"+this.searchForm.saleDateFrom);
        },
        turnpage(value){
            this.searchForm.page = value.page;
            this.searchForm.size = value.size;
            this.searchForm.sort = value.sort;
            this.getdatas();
        }
    }
});