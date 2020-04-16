controller = new Vue({
    el:"#login-wrap",
    data(){
        return {
            timer: null,
            show: true,
            count: "", // 初始化次数
            ruleForm: {
                phone: '',
                code: ''
            },
            rules: {
                phone: [
                    { required: true, message: '请输入手机号', trigger: 'blur' }
                ],
                code: [
                    { required: true, message: '请输入验证码', trigger: 'blur' }
                ]
            }
        }
    
    },
    created() {
    },
    methods: {
        sendCoder(){
            console.info("sendcode");
              //校验手机号码是否正确
              if(checkPhone!="" && checkPhone(this.ruleForm.phone)){
                

                 //获取验证码
                axios.get('/user/getCode', {
                    params:{
                        'phoneno': this.ruleForm.phone
                    }
                })
                .then(function (response) {
                    if(response.status==200 && response.data.status == 200){
                        jQuery.toast({
                            heading: 'success',
                            text :"验证码已发送",
                            position : 'mid-center',
                            icon: 'success',
                        });
                    }else{
                        jQuery.toast({
                            heading: 'error',
                            text :response.data.msg,
                            position : 'mid-center',
                            icon: 'error',
                        });
                    }
                })
                .catch(function (error) {
                    console.log(error);
                });

                const TIME_COUNT = 60; //更改倒计时时间
                if(!this.timer){
                    this.count = TIME_COUNT;
                    this.show = false;
                    this.timer = setInterval(() => {
                        if (this.count > 0 && this.count <= TIME_COUNT) {
                            this.count--;
                        } else {
                            this.show = true;
                            clearInterval(this.timer); // 清除定时器
                            this.timer = null;
                        }
                    }, 1000);

                }



            }else{
                jQuery.toast({
                    heading: 'error',
                    text :"手机号码格式错误",
                    position : 'mid-center',
                    icon: 'error',
                });
            }
           
        },
        dologin() {
           console.info("login");
           this.$refs["ruleForm"].validate((valid) => {
                if (valid) {
                    
                    axios.post('/user/doLogin', {
                        phoneno: this.ruleForm.phone,
                        code: this.ruleForm.code
                    })
                    .then(response => {
                       if(response.status ==200 && response.data.status == 200){
                            let phone = response.data.param0;
                            let token = response.data.param1;
                            localStorage.setItem("phonenno", phone);
                            localStorage.setItem("token", token);
                            //跳转到
                            window.location.href="/main";
                       }else{
                           this.$message(response.data.msg);
                       }
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
                


                }
            });
        }
    },

});