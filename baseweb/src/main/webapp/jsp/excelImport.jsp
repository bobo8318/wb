<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>情报一体化平台-数据导入</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="<%=basePath%>js/jquery.min.js"></script>
	<script src="<%=basePath%>js/jquery-form.js"></script>
	<script src="<%=basePath%>js/ajaxfileupload.js"></script>
	  <link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.css">

	  <style>
		.importTitle{
			text-align:center;
			font-size:18px;
		}
		.inputForm{
			margin-top:3px;
			margin-bottom:3px;
			border:1px solid #0099CC;
			width:250px;
		}
		.check{
			border:1px solid #0099CC;
		}
		.container{
			width:1000px;
			margin:0 auto;
			margin-top:10px;
			font-size:14px;
		}
		.container div{
			margin-top:3px;
		}
		.tableInfo{
			width:100%;
		}
		.tableInfo tr td{
		
			border:1px solid ;
			height:30px;
			line-height:30px;
		}
		.tableInfo input{
			border:none;
			width:99%;
			height:30px;
			line-height:30px;
		}
		.tableInfo select{
			border:none;
			width:99%;
			height:30px;
			line-height:30px;
		}
	</style>
  </head>
  
  <body>
  <div class="container">

 <div class="span12"> <h3>情报一体化平台-数据导入 </h3></div>
 <input type="hidden" id="msg" value="${msg}">
 <div class="span12">
   <form action="/haorop/hao/qb/upExcel" id="upForm" method="post" enctype="multipart/form-data"></form><p class="text-info">选择需要导入文件：
   	<input type="file" id="file" name='file'>
	 </form>
   	<button id="uploadFile" class="btn btn-info" >上传</button><span id="fileinfo"></span></p>

</div>

    <div class="span12">
		<form action="/haorop/hao/qb/import" method="post" id="importForm">
			<p class="text-info">选择需导入到的库：

	    <select  class="form-control input-large" name="importTable" id="importTable">
	    	<option value="0">==无==</option>
	    	<c:forEach items="${tableList}" var="lib">
	    			<option value="${lib.libid}">${lib.tableNameCN}</option>
	    	</c:forEach>
	    </select>
			</p>

	    
	    
	    
    
    <div id="htm" >
    	
    	
    	<table class="table table-hover" width="100%" id="tableinfo">
	    	<tr class="success">
	    		<td width="5%">序号</td><td width="20%">数据库英文名</td><td>数据类型</td><td>长度</td><td>对应中文名</td>
	    	</tr>
	    </table>
	    <input type="hidden" name="columnCount" id="columnCount" value="0">


		<p class="text-info">库中文名：<input type="text" id="tableNameCn" class="input-xlarge" name="tableNameCn"></p>

		<p class="text-info">库英文名：<input type="text" id="tableName" class="input-xlarge" name="tableName"></p>


	    <div><input type="radio" name="tableType" checked value="1">创建实体表
		<input type="radio" name="tableType" value="0">创建维度表</div>
		<div><input type="radio" name="importDataOnly" checked value="0">仅导入数据
		<input type="radio" name="importDataOnly" value="1">创建表格并导入数据</div>
		<div></div>
		<input type="submit" id="store" value="导入" class="btn btn-primary btn-lg btn-block">
		</div>
    	</form>

    </div>


  </div>
    <script>
    
    $(function(){
    	var resultMsg = $("#msg").val();
    	if(resultMsg>0){
    		alert("导入成功，导入数据："+resultMsg+"条");
    	}
    	
    	$("[name=newTable]:checkbox").click(function(){
    		if($(this).attr('checked')){
    			//alert(1);
    		}else{
    			//alert(0);
    		}
    		
    	});
    	$("#importTable").change(function(){
    		var value = $(this).children('option:selected').val();
    		$.get("/haorop/hao/qb/columnInfo/"+value,function(msg){
    			//alert(msg);
    			$("#tableinfo").html("");
    			var json = JSON.parse(msg);
    			//alert(json);
    			if(json.msg=="success"){
    					
					 	$("#tableinfo").append(showTableInfo(json));
    			}
    		});
    		
    	});
    	$("input[name=tableNameCn]").blur(function(){
    		var str = encodeURI(encodeURI($(this).val()));
    		if(str!=""&&str!=undefined){
	    		$.get('/haorop/hao/qb/py/'+str,function(msg){
	    			
	    			$("input[name=tableName]").val(msg);
	    		});
    		}
    	});
    	
    	var importForm = $("#importForm");//导入数据
    	
    	var upform = $("#upForm");//上传文件
    	jQuery.ajaxSettings.traditional = true;
    	$("#uploadFile").click(function(){
    		var fileName = $("input[type=file]").val();
    		 
    		$.ajaxFileUpload({
    			url:'/haorop/hao/qb/upExcel',
    			secureuri:false,
    			fileElementId:'file',
    			type:'post',
    			dataType:'json',
    			async:false,
    			error:function(data,status,e){
    				alert('上传失败!'+status);
    			},
    			success:function(json){
    				var tableSelected = $("#importTable").children('option:selected').val();
    				$("#fileinfo").html("已上传文件："+json.file);
    				if(json.msg=="success"&&tableSelected==0){
    						$("#tableinfo").html("");
       					 	$("#tableinfo").append(showTableInfo(json));
    				}else{
    					alert('上传失败，请确认excel格式是否正确');
    				}
    				
    			}
    			
    			
    		});
    		
    		//var fileTrue = document.getElementById("fileNameInput");
    		//fileTrue.select();
    	    //var realPath =  document.selection.createRange().text;
    	    
    		/*var fileName = $("input[type=file]").val();
    		alert(fileName);
    		var tableName = $("inut[name=tableName]").val();
    		/*$.ajax({
    			type:"post",
    			url:"/hao/qb/upExcel",
    			enctype:"multipart/form-data",
    			data:{
    				file:fileName
    			},
    			success:function(msg){
    				alert(msg);
    			}
    			
    		});*/
    		/*var options = {
        			dataType:"json",
        			data:{'file':fileName},
        			beforeSubmit:function(){
        				alert("上传中......");
        			},
        			success:function(result){
        				alert("上传成功");
        			},
        			error:function(result){
        				alert(result);
        			}
        			
        	};
    		 upform.ajaxSubmit(options);*/
    		
    	});
    	
    	
    });
    
    function showTableInfo(json){
    	var htmlContent = "";
    	if(json.titlsCN!=undefined&&json.titlsEN!=undefined){
			var titlesCN = json.titlsCN;
			 	var titlesEN = json.titlsEN;
			 	//生成对应关系
			 	var arrayCn = titlesCN.split(",");
			    var arrayEn = titlesEN.split(",");
			 	
			 	for(var i=0;i<arrayCn.length&&i<arrayEn.length;i++){
			 	  htmlContent +="<tr><td>"+i+"</td><td><input name='columnName_"+i+"' value='"+
			 	  arrayEn[i]+"'/></td><td><select name='columnType_"+i
			 	  +"'><option value='2' selected>文本</option><option value='1'>数字</option><option value='3'>日期</option><option value='4'>大文本</option></select></td><td><input name='columnLength_"+i
			 	  +"' value='50'></td><td><input name='columnCnName_"+i+"' value='"+arrayCn[i]+"'/></td></tr>";
			 	  
			 	  $("#columnCount").val(i+1);
			 	}
			 	
		} 
    	return htmlContent;
    }
    
    function getPath(obj) {
        if(obj)
    {  
        if (window.navigator.userAgent.indexOf("MSIE")>=1)
       {
           obj.select();
           return document.selection.createRange().text;
       }   
        else if(window.navigator.userAgent.indexOf("Firefox")>=1)
        {
           if(obj.files)
            {
                   return obj.files.item(0).getAsDataURL();
             }
             return obj.value;
        }
       return obj.value;
   }
}
   
    </script>
  </body>
</html>
