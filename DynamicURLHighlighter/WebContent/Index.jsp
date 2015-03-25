<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>Hello!</h1>
<p> Welcome to our dynamic word <mark>highlighter</mark>. Please
	enter a web page address below to locate and highlight
	your chosen words! </p>

<c:if test="${message != null}">
    <p><i>${message}</i></p>
</c:if>

<form action="emailList" method="get">
    <input type="hidden" name="action" value="add">        
    <label class="pad_top">Please enter valid URL:</label>
    <input type="email" name="email" value="${user.email}"><br>
          
    <label>&nbsp;</label>
    <input type="submit" value="Let's Highlight!" class="margin_left">
</form>

</body>
</html>