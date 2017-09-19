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
	<!-- 当Session中没有currentUser对象时，为未登录状态，提示登录  -->
	<c:if test="${ empty currentUser }">
		您还没有登录，<a href="${pageContext.request.contextPath }/login.jsp">请登录</a>
	</c:if>
	<!-- 当Session中有currentUser对象时，为登录状态，提示欢迎和当前用户的姓名  -->
	<c:if test="${ not empty currentUser }">
		登录成功！欢迎：${currentUser.name }
	</c:if>
	
</body>
</html>