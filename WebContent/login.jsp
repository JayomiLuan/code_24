<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 输入用户名密码进行登录的页面 -->
	<form action="${pageContext.request.contextPath }/loginServlet" method="post">
		<table align="center">
			<tr>
				<td colspan="2" style="color: red;font-size: small">${errMsg }</td>
			</tr>
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="password"/></td>
			</tr>
			<tr>
				<td colspan="2">
					<!-- 用户如果勾选此项，则提交到服务器后，如果用户名密码正确，则用户名和密码要保存到Cookie，
					便于实现实自动登录 -->
					<input type="checkbox" name="autoLogin" value="true" id="auto" />
					<label for="auto">一天内免登录</label>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="登录" />
					<input type="reset" />
				</td>
			</tr>
		</table>
	</form>
	
</body>
</html>