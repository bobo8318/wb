/**
 * vue 父子组件数据双向绑定 双向绑定的时候 子组件激发父组件事件时 绑定的数据更新落后于点击事件 会造成数据更新不及时
 * 父组件
 * <children msg.sync="fathermsg" @childmethod="fathermethod"/>
 * 子组件 子组件中msg是不能更改的 需要取出来
 * props["msg"]
 * data{
 *  childmsg:"" 
 * },
 * create(){
 *  this.childmsg = this.msg;
 * }
 * 子数据更改回传父组件
 * this.$emit("update:msg", newvalue);
 */


Vue.component("sidebar",{
    template:`
    
        <ul class="nav" id="side-menu">
                    <li style="padding: 70px 0 0;">
                        <a href="/main" class="waves-effect"><i class="fa fa-home fa-fw" aria-hidden="true"></i>首页</a>
                    </li>
                    <li>
                        <a href="/commodity" class="waves-effect"><i class="fa fa-trophy fa-fw" aria-hidden="true"></i>热销商品</a>
                    </li>
                    <li>
                        <a href="/hotshop" class="waves-effect"><i class="fa fa-thumbs-up fa-fw" aria-hidden="true"></i>热门店铺</a>
                    </li>
                    <li>
                        <a href="/collection" class="waves-effect"><i class="fa fa-star fa-fw" aria-hidden="true"></i>我的收藏</a>
                    </li>

                </ul>
    `
});

Vue.component("v-header",{
    template:`
    
    <nav class="navbar navbar-default navbar-static-top m-b-0">
    <div class="navbar-header">
        <div class="top-left-part">
            <!-- Logo -->
            <a class="logo" href="dashboard.html">
                <!-- Logo icon image, you can use font-icon also -->
                <b>
                    <!--This is dark logo icon-->
                    <img src="plugins/images/admin-logo.png" alt="home" class="dark-logo" />
                    <!--This is light logo icon-->
                    <img src="plugins/images/admin-logo-dark.png" alt="home" class="light-logo" />
                </b>
                <!-- Logo text image you can use text also -->
                <span class="hidden-xs">
                    <!--This is dark logo text-->
                    <img src="plugins/images/admin-text.png" alt="home" class="dark-logo" />
                    <!--This is light logo text-->
                    <img src="plugins/images/admin-text-dark.png" alt="home" class="light-logo" />
                </span> 
            </a>
        </div>
        <!-- /Logo -->
        <ul class="nav navbar-top-links navbar-right pull-right">
            <li>
                <a class="nav-toggler open-close waves-effect waves-light hidden-md hidden-lg" href="javascript:void(0)"><i class="fa fa-bars"></i></a>
            </li>
            <!-- li>
                <form role="search" class="app-search hidden-sm hidden-xs m-r-10">
                    <input type="text" placeholder="Search..." class="form-control"> 
                    <a href="">
                        <i class="fa fa-search"></i>
                    </a> 
                </form>
            </li-->
            <li>
                <a class="profile-pic" href="#">
                 <b class="hidden-xs">
 
                    <span v-if="showphone!=null">
                    欢迎：{{showphone}} <span href="#" @click="exit">登出</span>
                    </span>
                    <span v-if="showphone==null">请登录</span>
                    
                </b></a>
            </li>
        </ul>
    </div>
    <!-- /.navbar-header -->
    <!-- /.navbar-top-links -->
    <!-- /.navbar-static-side -->
</nav>

    `,
    data(){
        
    },
    created(){
        this.checkLogin();
        
    },
    methods:{
        checkLogin(){
            let token = localStorage.getItem('token');

            /*axios.get("/user/validateToken/"+123).then(res => {
                console.info(res);
            });*/

            if(token == null){
                window.location.href = "/login";
            }else{
               
                axios.get("/user/validateToken/"+token).then(res => {
                    if(res.status == 200 && res.data.status == 200){
                        console.info("token 认证成功");
                    }else{
                        console.info("认证token失败！");
                        localStorage.removeItem("phonenno");
                        localStorage.removeItem("token");
                        window.location.href = "/login";
                    }
                });
            }
        },
        exit(){
            //console.log(localStorage.getItem('phonenno'));
            localStorage.removeItem("phonenno");
            localStorage.removeItem("token");
            axios.get("/user/exit").then(res => {
                console.info(res);
            });
            //console.log(localStorage.getItem('phonenno'));
            window.location.href = "/login";
        }

    },
    computed:{
        showphone(){
            let phone = localStorage.getItem("phonenno")
            return  encodePhone(phone);
        }
    }
});

Vue.component("v-footer", {
    data(){
        return {

        }
    },
    created() {
    },
    methods:{

    },
    template:`
    <footer class="footer text-center">
        版权所有 非凡引力（北京）科技有限公司 
    </footer>
    `
});



Vue.component("searchtypepick", {
    data(){
        return {
            level1datas:[],
            level2datas:[],
            level3datas:[],

            level1pick:0,
            level2pick:0,
            level3pick:0,
        }
    },
    created() {
        this.getLevelDatas();
    },
    methods:{
        getLevelDatas(){
            //this.level1datas = getLevel1Data();
            let that = this;
            axios.get(getcat_url,{
                params: {
                    p:""
                }
              }).then((res)=>{
                console.info(res);
                if(res.status == 200 && res.data.code >=0){
                    //that.$set(that.level1datas,res.data.d);
                    that.level1datas = res.data.d;
                }
            });
        },
        selectlevel1(){        

            this.level2datas = [];
            this.level3datas = [];
            this.level2pick = "";
            this.level3pick = "";
        
            this.$emit('func',this.level1pick, 1);
            let that = this;
            axios.get(getcat_url,{
                params: {
                    p:that.level1pick
                }
            }).then((res)=>{
                //console.info(res);
                if(res.status == 200 && res.data.code >=0){
                    //that.$set(that.level1datas,res.data.d);
                    that.level2datas = res.data.d;
                }
            });
           
        },
        selectlevel2(){

            this.level3datas = [];
            this.level3pick = "";

            //this.level3datas = getLevel3Data();
            this.$emit('func',this.level2pick, 2);
            let that = this;
            axios.get(getcat_url,{
                params: {
                    p:that.level2pick
                }
            }).then((res)=>{
                console.info(res);
                if(res.status == 200 && res.data.code >=0){
                    //that.$set(that.level1datas,res.data.d);
                    that.level3datas = res.data.d;
                }
            });
        },
        selectlevel3(){
            if(this.level3pick != ""){
                this.$emit('func',this.level3pick, 3)
            }
        }
    },
    template:`
    <span>
        <select name="select"   v-model="level1pick" @change="selectlevel1()" class="form-control" data-type="theme" style=" background:url(ceshi/icon_sanjiao.jpg) no-repeat right;">
            <option v-for="(value, key) in level1datas" :key="key" :label="value" :value="key">
                {{value}}
            </option>
        </select>
        <select name="select"    v-model="level2pick" @change="selectlevel2()" class="form-control" data-type="theme" style=" background:url(ceshi/icon_sanjiao.jpg) no-repeat right;">
            <option v-for="(value, key) in level2datas" :key="key" :label="value" :value="key">
                {{value}}
            </option>
        </select>
        <select name="select"   v-model="level3pick"  @change="selectlevel3()" class="form-control" data-type="theme" style=" background:url(ceshi/icon_sanjiao.jpg) no-repeat right;">
            <option v-for="(value, key) in level3datas" :key="key" :label="value" :value="key">
                {{value}}
            </option>
        </select>
    <span>
    `
});


Vue.component("productlist", {
    props:["showdatas", "showoptions", "totaldata", "sortby"],
    data() {
        return {
            ordertype: 0,
            sortopt: "",

            child_showoptions:{
                size:10,
                page:1,
                ordertype: 0,
                sort: 0,
                lastId: ""
            }

        }
    },
    created() {
        
        this.child_showoptions.size = this.showoptions.size;
        this.child_showoptions.page = this.showoptions.page;
        this.child_showoptions.sort = this.showoptions.sort;
    },
    watch:{
       /* child_showoptions:{handler(value){
            console.info("change product list data:",value);
            this.$emit('update:showoptions', value);
           
            //this.$emit('getproductdata');

        },
            deep:true,
            immediate:true
        }      */ 
       

    },
    methods: {
        showinfo(){
            //console.info(this.totaldata);
            //console.info(this.child_showoptions.size);
            console.info(this.child_showoptions);
        },
        setSortPro(sorttype){
            /*if(this.child_showoptions.ordertype == 0){
                this.child_showoptions.ordertype = 1;
            }else{
                this.child_showoptions.ordertype = this.child_showoptions.ordertype%2+1;
            }*/

            if(sorttype){
                this.child_showoptions.sort = sorttype;
              /*  switch(sorttype){
                    case 1: this.child_showoptions.sort="day1";break;
                    case 2: this.child_showoptions.sort="days3";break;
                    case 3: this.child_showoptions.sort="days7";break;
                    case 4: this.child_showoptions.sort="salesvolume";break;
                    case 5: this.child_showoptions.sort="sales";break;
                    case 6: this.child_showoptions.sort="saledate";break;
                    default:break;
                }*/
                this.child_showoptions.page = 1;
                this.turn_page();
            }

        

        },
        watchaction(index, id, c_watchstatus){
            if(Number(c_watchstatus) !== NaN){
                this.$emit("watchact",index, id, 1^c_watchstatus);
            }
        },
        turn_page(value){
            //console.info(value);
            this.$emit('getdatas', {
                'page':this.child_showoptions.page,
                'size':this.child_showoptions.size,
                'sort':this.child_showoptions.sort
            });
        }
    },
    computed:{
        sortPro(){

            

            const {showdatas, showoptions, sortby} = this;
            
            this.child_showoptions.size = showoptions.size;

            this.showinfo();

            let datas;
            datas = showdatas;
            if(sortby == "server"){//服务端排序
                if(showdatas && showdatas.length >0 ){
                    this.child_showoptions.lastId = showdatas[showdatas.length-1].tid;
                }
                //console.info(showdatas[showdatas.length-1]);
                return datas;
            }else{//前端排序
                
                if (this.child_showoptions.ordertype !== 0 && this.child_showoptions.sortopt !== ""){
                    datas.sort(function (p1,p2) { //如果返回负数，p1在前，返回正数，p2在前
                        //1代表升序，2代表降序
    
                        let p2n = 'p2.'+this.child_showoptions.sortopt;
                        let p1n = 'p1.'+this.child_showoptions.sortopt;
    
                        let p2v = eval(p2n);
                        let p1v = eval(p1n);
    
                        if(checkDate(p2v) || checkDate(p1v)){//日期格式
                            p2v = p2v.replace(/-/g, "");
                            p1v = p1v.replace(/-/g, "");
                        }
    
                        if (this.child_showoptions.ordertype === 2 ){
          
                            //console.info("--2--"+ (eval(p2n)-eval(p1n)));
                            return parseInt(p2v) - parseInt(p1v);
                        } else {
                            //console.info("--1--"+ (p1.sortopt-p2.sortopt));
                            return parseInt(p1v) - parseInt(p2v);
                        }
                        
    
                    })
                }
            }//前端排序 end

            

            return datas;
        }

    },
    filters:{
        watchFilter: function(value){
            if(value){
                return "已收藏";
            }else{
                return "收藏";
            }
        }
    },
    template: `
    <div>
        <table border="0" cellspacing="0" cellpadding="0" width="100%;" style="background:#f8f8f8; line-height:70px;">
        <tr>
        <td style="width:80px; text-align:center">商品主图</td>
        <td style=" text-align:center">商品名</td>
        <td style="width:70px; text-align:center">类目</td>
        <td style="width:140px; text-align:center">店铺</td>
        <td style="width:140px; text-align:center">来源</td>
        <td style="width:100px; text-align:center">价格</td>
        <td style="width:120px; text-align:center" @click="setSortPro(0)">一日销量 <i v-show="child_showoptions.sort == 0" class="el-icon-caret-bottom"></i> </td>
        <!-- td style="width:120px; text-align:center" >三日销量</td-->
        <td style="width:120px; text-align:center" @click="setSortPro(1)">七日销量 <i v-show="child_showoptions.sort == 1" class="el-icon-caret-bottom"></i></td>
        <td style="width:120px; text-align:center" @click="setSortPro(2)">累计销量 <i v-show="child_showoptions.sort == 2" class="el-icon-caret-bottom"></i></td>
        <td style="width:120px; text-align:center" >销售额 </td>
        <td style="width:120px; text-align:center" v-if="sortby != 'server'">收藏时间</td>
        
        <td style="width:120px; text-align:center">操作</td>
        </tr>
    </table>
    <table border="0" cellspacing="0" cellpadding="0" width="100%;" style="background:#fff;">
        <tr style="height:90px;" v-for="(product, index) in sortPro">
        <td style="width:80px; text-align:center"><img v-bind:src="product.image"></td>
        <td style="text-align:center">{{product.name}}</td>
        <td style="width:70px; text-align:center">{{product.cat}}</td>
        <td style="width:140px; text-align:center">{{product.shop_name}}</td>
        <td style="width:140px; text-align:center">{{product.source}}</td>
        <td style="width:100px; text-align:center">{{product.discount_price}}</td>
        <td style="width:120px; text-align:center">{{product.salesVolume1}}</td>
        <!--td style="width:120px; text-align:center"></td-->
        <td style="width:120px; text-align:center">{{product.salesVolume7}}</td>
        <td style="width:120px; text-align:center">{{product.salesVolume}}</td>
        <td style="width:120px; text-align:center">{{product.salesValue}}</td>
        <td style="width:120px; text-align:center" v-if="sortby != 'server'">{{product.favdate}}</td>
        <td style="width:120px; text-align:center">
        <span @click="watchaction(index, product.tid, product.fav)" :style="(product.fav)?'background:#09F;padding:10px; color:#fff;border-radius:5px; cursor:pointer;':'background:#F00;padding:10px; color:#fff;border-radius:5px; cursor:pointer;' ">
        {{product.fav | watchFilter}}</span></td>
        </tr>
       
       
    </table>
    <my-pagination @turnpage="turn_page" :currentpage.sync="child_showoptions.page" :limit.sync="child_showoptions.size" :totalCount="totaldata" ></my-pagination>

</div>
    `
});


Vue.component("shoplist", {
    props:["showdatas","showoptions","totaldata","sortby"],
    data() {
        return {
            child_showoptions:{
                limit:10,
                page:1,
                sort: 0,
            },
        }
    },

    methods: {
        setSortPro(sorttype){

            if(this.sortby == "server"){
                this.child_showoptions.sort = sorttype;
                this.child_showoptions.page = 1;
                this.$emit('getdatas', this.child_showoptions);
                
            }else{
               
                this.child_showoptions.sort = sorttype;
                
            }

            /*if(sorttype){
                switch(sorttype){
                    case 1: this.changeSortOpt("onsale");break;
                    case 2: this.changeSortOpt("day1");break;
                    case 3: this.changeSortOpt("days3");break;
                    case 4: this.changeSortOpt("days7");break;
                    case 5: this.changeSortOpt("days30");break;
                    case 6: this.changeSortOpt("allwatch");break;
                    default:break;
                }
            }*/

            //if(this.sortby == "server"){//服务端排序
                //this.$emit('update:showoptions', this.child_showoptions);//同步数据

                //this.$emit('getdata');//拉取新数据
            //}

        },
        /*changeSortOpt(value){
            if(this.sortby == "server"){
                if(this.child_showoptions.sortopt != value){
                    this.child_showoptions.sortopt = value;
                    this.child_showoptions.page = 1;
                }
            }else{
                if(this.child_showoptions.sortopt != value){
                    this.child_showoptions.sortopt = value;
                }
            }
           
        },*/
        watchaction(index, id,c_watchstatus){
            
            //if(Number(c_watchstatus) !== NaN){
                console.info(!c_watchstatus);
                this.$emit("watchact",index, id, !c_watchstatus);
                //this.shopdatas//删除不收藏元素
           // }
            //this.$forceUpdate();
        },
        turnpage(value){
            //console.info(value);
            this.child_showoptions.page = value.page;
            this.child_showoptions.limit = value.limit;
            this.$emit('getdatas', this.child_showoptions);
        }
    },
    mounted() {
        this.child_showoptions = this.showoptions;//props 来的值需要存一下
        //this.gopage = this.currentpage;
        //this.totalCount = this.total;
        //console.info(this.child_showoptions);
    },
    watch:{
        /*hild_showoptions:{handler(value){
                //this.$emit('update:shopdatas', value);
                this.$emit('getdatas', value);

                //this.$emit('getshopdata');
            },  
            deep: true,
            immediate: true
        }, */
    },
    computed:{
        sortPro(){
            //const {shopdatas, orderType, sortopt} = this;
            const {showdatas,showoptions,totaldata,sortby} = this;
            let datas;
            datas = showdatas;
            //console.info("shop watch datas");
            //console.info(showdatas);
            if (this.child_showoptions.ordertype !== 0 && this.child_showoptions.sort !== ""){
                if(sortby == "server"){//服务端排序
                    console.info("server sort");
                }else{//前端排序
                    console.info("client sort");
                    datas.sort(function (p1,p2) { //如果返回负数，p1在前，返回正数，p2在前
                        //1代表升序，2代表降序
                        let p2n = 'p2.' + this.sort;
                        let p1n = 'p1.' + this.sort;
    
                        //console.info(p2n);
                        //console.info(eval(p2n));
    
                        if (this.child_showoptions.ordertype === 2 ){
          
                            //console.info("--2--"+ (eval(p2n)-eval(p1n)));
                            return parseInt(eval(p2n)) - parseInt(eval(p1n));
                        } else {
                            //console.info("--1--"+ (p1.sortopt-p2.sortopt));
                            return parseInt(eval(p1n)) - parseInt(eval(p2n));
                        }
                        
    
                    })
                }
                
            }
            return datas;
        },
    },
    filters:{
        watchFilter: function(value){
            if(value){
                return "已收藏";
            }else{
                return "收藏";
            }
        }
    },
    template: `
    <div>
                            
<table border="0" cellspacing="0" cellpadding="0" width="100%" style="background:#f8f8f8; line-height:70px;">
<tr>
  <td style="width:80px; text-align:center">店标</td>
  <td style="text-align:center">店铺</td>
  <td style="width:200px; text-align:center">所属公司</td>
  <td style="width:120px; text-align:center">主营类目</td>
  <td style="width:120px; text-align:center" >在售商品 </td>
  <td style="width:120px; text-align:center" @click="setSortPro(0)">一日销量 <i v-show="child_showoptions.sort == 0" class="el-icon-caret-bottom"></i></td>
  <!--d style="width:120px; text-align:center" >三日销量 <i v-show="child_showoptions.sort == 3" class="el-icon-caret-bottom"></i></td-->
  <td style="width:120px; text-align:center" @click="setSortPro(1)">七日销量 <i v-show="child_showoptions.sort == 1" class="el-icon-caret-bottom"></i></td>
  <td style="width:120px; text-align:center" @click="setSortPro(2)">三十天销量 <i v-show="child_showoptions.sort == 2" class="el-icon-caret-bottom"></i></td>
  <!--td style="width:100px; text-align:center" @click="setSortPro(6)">关注数</td-->
  <td style="width:100px; text-align:center" v-if="sortby != 'server' ">收藏日期</td>
  <td style="width:120px; text-align:center" >操作</td>
</tr>
</table>
<table border="0" cellspacing="0" cellpadding="0" width="100%;" style="background:#fff;">
<tr style="height:90px;" v-for="(shop, index) in sortPro">
  <td style="width:80px; text-align:center"><img v-bind:src="shop.image"></td>
  <td style="text-align:center">{{shop.name}}</td>
  <td style="width:200px; text-align:center">{{shop.company_name}}</td>
  <td style="width:120px; text-align:center">{{shop.main}}</td>
  <td style="width:120px; text-align:center">{{shop.online_prods}}</td>
  <td style="width:120px; text-align:center">{{shop.salesVolume1}}</td>
  <!--td style="width:120px; text-align:center">{{shop.salesVolume3}}</td-->
  <td style="width:120px; text-align:center">{{shop.salesVolume7}}</td>
  <td style="width:120px; text-align:center">{{shop.salesVolume30}}</td>
  <!--td style="width:100px; text-align:center">{{shop.allwatch}}</td-->
  <td style="width:100px; text-align:center" v-if="sortby != 'server'"> 
  {{shop.favdate}}
  </td>
  <td style="width:120px; text-align:center">
  <span @click="watchaction(index, shop.tid, shop.fav)" :style="(shop.fav)?'background:#09F;padding:10px; color:#fff;border-radius:5px; cursor:pointer;':'background:#F00;padding:10px; color:#fff;border-radius:5px; cursor:pointer;' ">
  {{shop.fav | watchFilter}}</span></td>
</tr>


</table>

<my-pagination :currentpage="child_showoptions.page" :limit="child_showoptions.limit" :totalCount="totaldata" @turnpage="turnpage"></my-pagination>
</div>
    `
});

/**
 * 分页组件
 */

Vue.component("my-pagination",{
    props: {
        currentPage: {//当前页
            type: Number,
            default: 1,
        },
        limit:{//每页显示条数
            type: Number,
            default: 10,
        },
        totalCount:{//总条数
            type: Number,
            required: true
        },
        small:{
            type:Boolean,
            default: false,
        },
        all:{
            type:Boolean,
            default: false,
        }
    },
    data() {
        return {
            goPage:'',//跳转页面
            limitNum:'',//每页显示数量
            limitNums:[5,10,15,20,25,30],
            toPrev:false,
            toNext:false,
        }
    },
    created: function () {
        this.initLimitNums();
    },
    computed: {
      
        totalPage(){
            return Math.ceil(this.totalCount / this.limit)
        },
        showPageBtn() {
            let page_li_count = 10;//显示页码最大数量

            let show_page_start = 1;
            let show_page_end;

            let pageNum = Number(this.totalPage),
                index = Number(this.goPage),
                arr = [];
            if (pageNum <= 10) {
                for(let i = 1; i <= pageNum; i++) {
                    arr.push(i)
                }
                return arr
            }
            // 如果
            if(index <= 5){
                show_page_end = page_li_count;
            }else{
                show_page_start = index - 4;
            if(index+4 <= pageNum ){
                show_page_end = index+4;
            }else{
                show_page_end = pageNum;
            }
            
            }
            for(let i=show_page_start;i<=show_page_end;i++){
                arr.push(i);
            }
            return arr;
                /*if (index < 5) return [1,2,3,4,5,6,7,'+',pageNum]
            if((index )
                if (index >= pageNum -1) return [1,2,'-', pageNum -5,pageNum -4,pageNum -3, pageNum -2, pageNum -1, pageNum]
                if (index === pageNum -2) return [1,2,'-', pageNum -5,pageNum -4,pageNum-3, pageNum-2, pageNum-1, pageNum]
                return [1,'-', index-2,index-1, index, index + 1,index + 2, '+', pageNum]*/
        }
    },
    methods: {
        // 初始化每页显示数据列表
        initLimitNums() {
            this.limitNum = this.limit;
            this.goPage = this.currentPage;
            //if (this.limitNums.indexOf(this.limit) == -1) {
            if (!this.limitNums.includes(this.limit)) {
                this.limitNums.push(this.limit);
                this.limitNums.sort(function (a, b) {
                    return a - b;
                });
            }
        },
        //翻页，显示条数变化
        turn(page) {
            let i = parseInt(Number(page));
            this.goPage = i;
            if (i < 1) {
                i = 1;
            } else if (i > this.totalPage) {
                i = this.totalPage;
            }

            if (this.limitNum !== this.limit && this.limitNum !== '') {//每页显示条数改变
                let pages = Math.ceil(this.totalCount / this.limitNum);
                if (page > pages) {
                    i = pages;
                }
                this.$emit('update:limit', this.limitNum);
            }
            this.$emit('update:currentpage', i);

            this.goPage = i;
            this.$emit('turnpage',{'page':this.goPage, 'limit':this.limitNum});
        }
    },
    template:`
    <div class="col-sm-12 page" v-if="totalPage>1" style="width:100%; background:#fff; height:50px; margin-top:10px;">
            
    <ul>
        <li :class="{'disabled':goPage<=1}" @click="goPage<=1?'':turn(goPage-1)">&lt; 上一页</li>
           <li v-for="i in showPageBtn" :class="{'active':i==goPage}" v-if="i>0" @click="turn(i)">{{i}}</li>
        <li :class="{'disabled':goPage>=totalPage}"  @click="goPage>=totalPage?'':turn(goPage+1)">下一页 &gt;</li>
    </ul>


    <div>
            <select v-if="!small" v-model="limitNum" @change="turn(goPage)">
                <option v-for="item in limitNums" :value="item">{{item}}条/页</option>
             </select>
         <span  class="text" v-if="!small"  class='message'>共{{totalCount}}条记录 第{{goPage}}/{{totalPage}}页</span>  
         <span  v-if="all" class="jump">前往<input type="number" autocomplete="off" min="1" :max="totalPage" v-model="goPage" @keyup.enter="turn(goPage)">页</span>
    </div>
</div>
    `
})

/**
 * 判断日期格式
 */

function checkDate(dateStr){
    var a = /^(\d{4})-(\d{2})-(\d{2})$/
    if (!a.test(dateStr)) {
        return false
    }else{
        return true;
    }
}