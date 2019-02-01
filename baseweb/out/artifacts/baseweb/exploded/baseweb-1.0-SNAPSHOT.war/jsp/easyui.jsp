<%--
  Created by IntelliJ IDEA.
  User: My
  Date: 2016/11/27
  Time: 23:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>easyui Test</title>
    <link rel="stylesheet" type="text/css" href="../../themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../../themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../../css/demo.css">
    <script type="text/javascript" src="../../js/jquery.min.js"></script>
    <script type="text/javascript" src="../../js/jquery.easyui.min.js"></script>

</head>
<body>

<div id="magazineGrid">
</div>


<table id="tt" class="easyui-datagrid" style="width:100%;height:250px"
       url="ga/login/json"
       title="Load Data" iconCls="icon-save"
       rownumbers="true" pagination="true"
       toolbar="#tb"
>
    <thead>
    <tr>
        <th field="itemid" width="80">Item ID</th>
        <th field="productid" width="80">Product ID</th>
        <th field="listprice" width="80" align="right">List Price</th>
        <th field="unitcost" width="80" align="right">Unit Cost</th>
        <th field="attr1" width="150">Attribute</th>
        <th field="status" width="60" align="center">Stauts</th>
    </tr>
    </thead>
</table>

<div id="tb" style="padding:3px">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:alert('Add')">Add</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="javascript:alert('Cut')">Cut</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:alert('Save')">Save</a>

    <span>Item ID:</span>
    <input id="itemid" style="line-height:26px;border:1px solid #ccc">
    <span>Product ID:</span>
    <input id="productid" style="line-height:26px;border:1px solid #ccc">
    <a href="#" class="easyui-linkbutton" plain="true" onclick="doSearch()">Search</a>
</div>
<script>
    $(function(){
       /* $('#magazineGrid').datagrid({
            height: 340,
            url: 'url',
            method: 'POST',
            //queryParams: { 'id': id },
            idField: '产品ID',
            striped: true,
            fitColumns: true,
            singleSelect: false,
            rownumbers: true,
            pagination: true,
            nowrap: false,
            pageSize: 10,
            pageList: [10, 20, 50, 100, 150, 200],
            showFooter: true,
            columns: [[
                { field: 'ck', checkbox: true },
                { field: '刊名', title: '刊名', width: 180, align: 'left' },
                { field: '类别', title: '类别', width: 150, align: 'left' },
                { field: '月份', title: '月份', width: 100, align: 'left' },
                { field: '期次', title: '期次', width: 100, align: 'left' },
                { field: '价格', title: '价格', width: 100, align: 'right' },
                { field: '订阅数', title: '订阅数', width: 100, align: 'right' },
                { field: '库存数', title: '库存数', width: 100, align: 'right' },
                { field: '邮寄方式', title: '邮寄方式', width: 80, align: 'left' },
                { field: '数量', title: '数量', width: 80, align: 'left',
                    editor: {
                        type: 'numberbox',
                        options: {
                            min: 0,
                            precision: 0
                        }
                    }
                }
            ]],
            onBeforeLoad: function (param) {
            },
            onLoadSuccess: function (data) {

            },
            onLoadError: function () {

            },
            onClickCell: function (rowIndex, field, value) {

            }
        });*/



    });
    function doSearch() {
        $('#tt').datagrid('load', {
            itemid: $('#itemid').val(),
            productid: $('#productid').val()
        });
    }

</script>
</body>
</html>
