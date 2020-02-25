<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'easyUI-show.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!--jquery的js-->
        <script type="text/javascript" src="jquery-easyui-1.7.0/jquery.min.js"></script>
         <!--jquery的easyUI主js文件  这个顺序不能颠倒-->
        <script type="text/javascript" src="jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
         <script type="text/javascript" src="jquery-easyui-1.7.0/locale/easyui-lang-zh_CN.js"></script>
        <!--easyUI的主样式文件-->
        <link rel="stylesheet" type="text/css" href="jquery-easyui-1.7.0/themes/default/easyui.css">
        <!--easyUI的图标样式文件-->
        <link rel="stylesheet" type="text/css" href="jquery-easyui-1.7.0/themes/icon.css">
  </head>
  <script type="text/javascript">
  		$(function(){
  			function dateFormat(value){
	             var date = new Date(value);
	             var year = date.getFullYear();
	             var month = date.getMonth()+1;
	             var day = date.getDate();
	             var result = year+"-"+month+"-"+day;
	             return result;
           }
  			$("#dg").datagrid({
    		url:"page",
    		toolbar:"#div1",
    		striped:true,//一行一色
    		fitColumns:true,
    		pagination:true,
    		pageSize:5,
    		pageList:[2,3,4,5,10],
    		remoteSort:false,
			sortName:'sid',
			sortOrder:'asc',
   			 columns:[[
        	//{field:"序号",title:"序号",width:100,formatter:function(vlaue,row,index){return index+1;}},
   			//{field:'check',checkbox:true,title:'',width:100},
        	{field:"sid",title:"编号",width:100},
        	{field:"sname",title:"商品名",width:100},
              {field:"outtime",title:"出厂日期",width:100,formatter:function(value,row,index){
                         var date = dateFormat(value);
                         return date;
                     }},
                 {field:"num",title:"商品数量",width:100},
                 {field:"type",title:"类型",width:100,formatter:function(value){return value.lname}},
                  {field:"img",title:"图片",width:100,formatter:function (value,row) {
                             return "<img src='"+value+"' style='width: 70px; height: 70px;'/>";

            }},
        	{field:"aa",title:"操作",width:100,formatter:function(value,row){
        	    if (row.num==0){
                    return "<a href='buhuo?id="+row.sid+"'>补货</a>"+"    "+"<a href='goupdate?id="+row.sid+"'>修改</a>";
                }else{
                    return "<a href='goupdate?id="+row.sid+"'>修改</a>";
                }
        	}},
   /*      	{field:"restrictday",title:"限用天数",width:100},    
        	{field:"overplusday",title:"剩余天数",width:100},    
        	{field:"restrictnum",title:"限用次数",width:100},    
        	{field:"overplusnum",title:"剩余限用次数",width:100},    
        	{field:"uaszdcs",title:"操作",width:100,formatter:function(value,row){
        	return "<a href='goAdd?cid="+row.cid+"'>刷卡</a>      "
        	+"<a href='goQuery?cid="+row.cid+"'>详情</a>";
        	}
        	},   */  
        	/* {field:"image",title:'头像',width:100,formatter:function(value){
				        	return "<img src='"+value+"' style='width: 70px; height: 70px;'/>";
				        }}, 
        	//{field:"birth",title:"生日",width:100,formatter:function(value){var date=new Date(value); return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate(); }},    
        	{field:'birth',title:'生日',width:100,formatter:function(value,row,index){
                        var date = dateFormat(value);
                        return date;
                        }},
        	{field:"depat",title:"部门",width:100,formatter:function(value){return value.dname;}} */    
        	
   			]]    
    	});
    	var p = $('#dg').datagrid('getPager');
			$(p).pagination({
				layout:['list','first','prev','links','last','sep','next','refresh','manual'],
		});
            //搜索
            $("#search").click(function(){
                $('#dg').datagrid("load",{
                    start:$("#in1").val(),
                    end:$("#in2").val()
                });
            });
            $("#search1").click(function(){
                location = "showAvgAge";
            });
            //添加
            $("#add").click(function(){
            	location = "goadd";
            });
             $("#update").click(function(){
            	var checkedItems = $('#dg').datagrid("getChecked");
            	var ids= [];
            	$.each(checkedItems,function(index,item){
            		ids.push(item.id);
            	});
            	location = "selectById?id="+ids[0];
            });
             $("#delete").click(function(){
            	var checkedItems = $('#dg').datagrid("getChecked");
            	var ids= [];
            	$.each(checkedItems,function(index,item){
            		ids.push(item.id);
            	});
            	location = "delete?ids="+ids.join(",");
            });
  	});
  </script>
  <body>
  <div id="div1">
 输入价格取件：<input id="in1" type="text" name="start" />
 -<input id="in2" type="text" name="end" />
  		<a id="search" class="easyui-linkbutton" 
  		data-options="iconCls:'icon-search'">搜索</a>
  </div>
  		
  		 <a id="add" class="easyui-linkbutton" 
  		data-options="iconCls:'icon-add'">添加</a>

<!--<a id="delete" class="easyui-linkbutton"
   data-options="iconCls:'icon-remove'">删除</a>
 <a id="search" class="easyui-linkbutton"
  		data-options="iconCls:'icon-search'">方向学生最大年龄统计</a>
        <a id="search1" class="easyui-linkbutton"
        data-options="iconCls:'icon-search'">方向学生平均年龄统计</a> -->
  		</div>
<!--   	<div>
  		用户名查询<input id="key" type="text" name="key" />
  		<a id="search" class="easyui-linkbutton" 
  		data-options="iconCls:'icon-search'">搜索</a>
  		<a id="add" class="easyui-linkbutton" 
  		data-options="iconCls:'icon-add'">添加</a>  
  		<a id="update" class="easyui-linkbutton" 
  		data-options="iconCls:'icon-edit'">修改</a>  
  		<a id="delete" class="easyui-linkbutton" 
  		data-options="iconCls:'icon-remove'">删除</a>    
  	</div> -->
    <table id="dg">
    	<p align="center">积云教育学生信息查询</p>
    </table>  
  </body>
</html>
