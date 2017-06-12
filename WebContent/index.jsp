<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td>编号</td>
			<td>名称</td>
			<td>状态</td>
		</tr>
		<c:forEach items="${tags }" var="t">
			<tr>
				<td>${t.tagid }</td>
				<td>${t.tagname }</td>
				<td>${t.tagstate }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>