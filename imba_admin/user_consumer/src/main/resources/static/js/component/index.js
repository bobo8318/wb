
Vue.component("dayspick", {
    data () {
        return {
            salePieDaysActivated: "昨日数据",
            salePieDays: 1
        }
    },
    methods:{
        salePieDaysPick(days){
            if(days == 1){
                this.salePieDaysActivated = "昨日数据";
                this.salePieDays = 1;
            }else{
                this.salePieDaysActivated = "前"+days+"天";
                this.salePieDays = days;
            }
            this.$emit('func',this.salePieDays)
        }
    },
    template: `
        <div class="dropdown">
            <span>{{salePieDaysActivated}}</span>
            <div class="dropdown-content" >
                <p @click="salePieDaysPick(1)">昨日数据</p>
                <p @click="salePieDaysPick(3)">前3天</p>
                <p @click="salePieDaysPick(7)">前7天</p>
                <p @click="salePieDaysPick(14)">前14天</p>
                <p @click="salePieDaysPick(30)">前30天</p>
                <p @click="salePieDaysPick(60)">前60天</p>
            </div>
        </div>
    `
});


Vue.component("toplist", {
    props: ["productlist"],
    data () {
        return {
            msg: "hello"
        }
    },
    mounted(){
    },
    watch:{
        /*productlist(newValue,oldValue){
            console.log(newValue);
        }*/
    },

    template: `
    <dl class="home_list_dl">
        <dt>
            <span style="width:20%;">商品图</span>
            <span style="width:60%;">商品名</span>
            <span style="width:20%;">昨日销量</span>
        </dt>
        <dd v-for="product in productlist">
            
            <div style="width:20%; float:left; text-align:center"><img style="width:60px;height:60px;" v-bind:src="product.image"></div>
            <div style="width:60%; float:left; text-align:center; line-height:30px;">{{ product.name }}</div>
            <div style="width:20%; float:left; text-align:center; line-height:60px;">{{ product.sell_num }}</div>
        </dd>
    </dl>
    `
});



// vue
const vm  = new Vue({
    el: "#wrapper", //选择器
    data: {
        mainStat:{
            prodCount: "",
            hProdCount: 2,
            nProdCount: 3,
            shopCount: 4,
            hShopCount: 5,
            nShopCount: 6,
        },

        sales_pie_type:'1',
        sales_pie_title: "大盘数据-各类占比",
        salePieDaysActivated: "昨日数据",
        salePieDays: 1,
        sales_pie_data:[],


        new_pie_title: "上新动态 - 各类占比",
        newPieDays: 1,
        new_pie_data:[],
        pieChartLevel:{
            c1:"",
            c2:"",
            c3:""
        },

        line_chart_data:{},
        lineChartDays: 3,
        lineChartType: 1,
        lineChartLevelDatas:{},

        lineChartLevel:{
            c1:"",
            c2:"",
            c3:""
        },

        top_product:{},
        top_shop:{}
    },
    created() {
    },
    mounted() {
        this.getSales_pie_data();
        this.getNew_pie_data();
        this.getLine_chart_data();
        this.getMainStat();
        this.getTopProduct();
        this.getTopShop();
    },
    methods: {
        getMainStat(){
             //this.mainStat =  getLocalMainStat();

             let that = this;
             axios.get(mainstat_url)
                 .then(function (response) {
                     //console.info(response);
                     if(response.status == 200 && response.data.code >= 0 ){
                         //that.mainStat =  response.data.d;
                         vm.$set(that.mainStat,'prodCount',response.data.d.prodCount);
                         vm.$set(that.mainStat,'hProdCount',response.data.d.hProdCount);
                         vm.$set(that.mainStat,'nProdCount',response.data.d.nProdCount);
                         vm.$set(that.mainStat,'shopCount',response.data.d.shopCount);
                         vm.$set(that.mainStat,'hShopCount',response.data.d.hShopCount);
                         vm.$set(that.mainStat,'nShopCount',response.data.d.nShopCount);
                         //console.info(that.mainStat);
                     }else{
                         console.info(response.data.msg);
                     }
                 })
                 .catch(function (error) {
                     console.log(error);
                 });
        },

        getSales_pie_data(){// 大盘数据
            //this.sales_pie_data = getLocalSalePieData_volume(this.sales_pie_type, this.salePieDays);
            let dataurl = "";
            if(this.sales_pie_type === '1' ){
                dataurl = volume_url;
            } else if(this.sales_pie_type === '2' ){
                dataurl = value_url
            }
            axios.get(dataurl,{
                params:{
                    n:this.salePieDays
                }
            }).then( res =>{
               if(res.status == 200 && res.data.code >=0 ){

                this.sales_pie_data = formatPieData(res.data.d);
                this.showPieChart(this.sales_pie_title, this.sales_pie_data , "chengjiao")

               }
            });
        },
        salePieTypePick(pietype){
            this.sales_pie_type = pietype;
            this.getSales_pie_data();
        },
        salePieDaysFromChild(days){
            this.salePieDays = days;
            this.getSales_pie_data();
        },

        new_stat_typeSelect(protype, level){

            if(level == 1){
                this.pieChartLevel.c1 = protype;
                this.pieChartLevel.c2 = "";
                this.pieChartLevel.c3 = "";
            }
            if(level == 2){
                this.pieChartLevel.c2 = protype;
                this.pieChartLevel.c1 = "";
                this.pieChartLevel.c3 = "";
            }
            if(level == 3){
                this.pieChartLevel.c3 = protype;
                this.pieChartLevel.c2 = "";
                this.pieChartLevel.c1 = "";
            }

            this.getNew_pie_data();
        },

        getNew_pie_data(){
            //this.new_pie_data = getLocalLevelPieData();
            axios.get(getnewstat_url,{
                params: {
                    c1: this.pieChartLevel.c1,
                    c2: this.pieChartLevel.c2,
                    c3: this.pieChartLevel.c3,
                    n:this.newPieDays
                }
            }).then((res)=>{
                if(res.status == 200 && res.data.code >=0 ){

                    this.new_pie_data = formatPieData(res.data.d);
                    this.showPieChart(this.new_pie_title, this.new_pie_data, "shangxin")
                   }
            });
        },
        newPieDaysFromChild(days){
            this.newPieDays = days;
            this.getNew_pie_data();
        },

        getLine_chart_data(){
            //this.line_chart_data = getLocalLineChartData(this.lineChartType, this.lineChartLevel, this.lineChartDays);
            axios.get(tradeline_url+"/"+this.lineChartDays,{
                params:{
                    c1: this.lineChartLevel.c1,
                    c2: this.lineChartLevel.c2,
                    c3: this.lineChartLevel.c3
                }
            }).then((res)=>{

                //that.showLinechart(this.line_chart_data.labels, this.line_chart_data.series, "ct-visits")
                if(res.status==200 && res.data.code >= 0){
                    if(res.status == 200 && res.data.code >=0){
                        this.line_chart_data = formatLineChartData(res.data.d);
                        //console.info( this.line_chart_data);
                        this.showLinechart(this.line_chart_data.labels, this.line_chart_data.series, "ct-visits");
                    }
                }
            });
            //this.showLinechart(this.line_chart_data.labels, this.line_chart_data.series, "ct-visits")
        },
        lineChartTypePick(type){
            switch(type){
                case 1:this.lineChartType = "1"; break;//交易量
                case 2:this.lineChartType = "2"; break;//供应商
                case 3:this.lineChartType = "3"; break;//在售商品均价
                default:this.lineChartType = "1";break;
            }

            this.getLine_chart_data();
        },
        lineChartDaysPick(days){
          
            this.lineChartDays = days;
            this.getLine_chart_data();
        },
        lineChartLevelPick(leveldata, level){
            if(level == 1){
                this.lineChartLevel.c1 = leveldata;
                this.lineChartLevel.c2 = "";
                this.lineChartLevel.c3 = "";
            }
            if(level == 2){
                this.lineChartLevel.c2 = leveldata;
                this.lineChartLevel.c1 = "";
                this.lineChartLevel.c3 = "";
            }
            if(level == 3){
                this.lineChartLevel.c3 = leveldata;
                this.lineChartLevel.c2 = "";
                this.lineChartLevel.c1 = "";
            }
            //this.lineChartLevel = leveldata;
            console.info(this.lineChartLevel);
            this.getLine_chart_data();
        },

        getTopProduct(){
            let that = this;
            axios.all([
                axios.get(hotprod_url+'1').then((res)=>{
                    if(res.status == 200 && res.data.code >= 0 ){
                        //top_product.yesterdayBest
                        vm.$set(that.top_product, "yesterdayBest", res.data.d);
                    }
                })
                , axios.get(hotprod_url+'3').then((res)=>{
                    if(res.status == 200 && res.data.code >= 0 ){
                        //top_product.yesterdayBest
                        vm.$set(that.top_product, "newBest", res.data.d);
                    }
                })
                , axios.get(hotprod_url+'30').then((res)=>{
                    if(res.status == 200 && res.data.code >= 0 ){
                        //top_product.yesterdayBest
                        vm.$set(that.top_product, "everbest", res.data.d);
                    }
                })
            ])
            .then(
                axios.spread((hot1, hot3, hot30) => {
                  
                   //console.info("product all finish");
       
              })
            )
            .catch(err => {
              console.log(err.response)
            });
            //this.top_product = getLocalTopProduct(); hotprod_url
        },
        getTopShop(){
            let that = this;
            //this.top_shop = getLocalTopShop(); hotshop_url
            axios.all([
                axios.get(hotshop_url+'1').then((res)=>{
                    if(res.status == 200 && res.data.code >= 0 ){
                        //top_product.yesterdayBest
                        vm.$set(that.top_shop, "yesterdayBest", res.data.d);
                    }
                }), 
                axios.get(hotshop_url+'3').then((res)=>{
                    if(res.status == 200 && res.data.code >= 0 ){
                        //top_product.yesterdayBest
                        vm.$set(that.top_shop, "newBest", res.data.d);
                    }
                }), 
                axios.get(hotshop_url+'30').then((res)=>{
                    if(res.status == 200 && res.data.code >= 0 ){
                        //top_product.yesterdayBest
                        vm.$set(that.top_shop, "everbest", res.data.d);
                    }
                })
            ])
            .then(
                axios.spread((hot1, hot3, hot30) => {
                    //console.info("shop all finish");
       
              })
            )
            .catch(err => {
              console.log(err.response)
            });
        },
        // 图表显示部分
        showPieChart(pietitle, piedata, id){
            var chart = {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            };
            var title = {
                text: pietitle
            };      
            var tooltip = {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            };
            var plotOptions = {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}%</b>: {point.percentage:.1f} %',
                        style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                        }
                    }
                }
            };
            var json = {}; 
            //配置 
            json.chart = chart; 
            json.title = title;     
            json.tooltip = tooltip;  
            json.plotOptions = plotOptions;
            //数据
            var series = [{
                type: 'pie',
                name: 'Browser share',
                data:   piedata
            }];
            json.series = series;
            //
            $('#'+id).highcharts(json);  
        },
        showLinechart(labelsdata, seriesdata, id){
            new Chartist.Line('#'+id, {
                labels: labelsdata,
                series: seriesdata
                }, {
                top: 0,
                low: 1,
                
                showPoint: true,
                fullWidth: true,
                plugins: [
                Chartist.plugins.tooltip({

                }),
                Chartist.plugins.ctPointLabels({
                    textAnchor: 'middle',
                    labelInterpolationFnc: function(value) {return '$' + value.toFixed(2)}
                  })
            ],
                axisY: {
                    labelInterpolationFnc: function (value) {
                        return (value / 1) + 'k';
                    }
                },
                showArea: true
            });
        }//showLinechart end
    }
        
        
 });
    
