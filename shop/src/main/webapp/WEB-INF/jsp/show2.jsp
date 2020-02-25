<%--
  Created by IntelliJ IDEA.
  User: ZhangWei
  Date: 2020/1/13
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="js/jquery.js"></script>
</head>
<body>
<form action="add" method="post" enctype="multipart/form-data">
    商品名称：<input name="sname"><br>
    出厂日期：<input type="date" name="outtime"><br>
    商品数量：<input name="num"><br>
    商品图片：<input name="imgs" type="file"><br>
    商品类型：<select id="se" name="lid" >
</select><br>
    <input type="submit" value="添加">
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
