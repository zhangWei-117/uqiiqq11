<%--
  Created by IntelliJ IDEA.
  User: ZhangWei
  Date: 2020/1/13
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="js/jquery.js"></script>
</head>
<body>
<form action="update" method="post" enctype="multipart/form-data">
    <input name="sid" value="${shop.sid}" type="hidden">
    商品名称：<input name="sname" value="${shop.sname}" ><br>
    出厂日期：<input name="outtime" value='<fmt:formatDate value="${shop.outtime}"></fmt:formatDate>
'><br>
    商品数量：<input name="num" value="${shop.num}"><br>
    商品图片：<input name="imgs" type="file"><br>
    <img src="${shop.img}">
    商品类型：<select id="se" name="lid" >
</select>
    <input type="submit" value="修改">
</form>

</body>
<script type="text/javascript">
    $.post(
        "getType",
        function (data) {
            for (var i = 0; i < data.length; i++) {
                $("#se").append("<option value="+data[i].lid+">"+data[i].lname+"</option>")
            }
        }
    );


</script>
</html>
