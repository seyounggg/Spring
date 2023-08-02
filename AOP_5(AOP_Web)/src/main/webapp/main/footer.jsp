<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="wrapper row4">
  <footer id="footer" class="clear"> 
    <div class="one_third first">
      <h6 class="title">맛집 Top7</h6>
      <ul class="nospace">
        <c:forEach var="fvo" items="${foodList }">
        <li><a href="#">${fvo.name }</a></li>
        </c:forEach>
      </ul>
    </div>
   <div class="one_third">
      <h6 class="title">서울여행 Top7</h6>
      <ul class="nospace">
        <c:forEach var="svo" items="${seoulList }">
        <li><a href="#">${svo.title }</a></li>
        </c:forEach>
      </ul>
    </div>
    <div class="one_third">
      <h6 class="title">로그인</h6>
      <ul class="faico clear">
        <li><a class="faicon-facebook" href="#"><i class="fa fa-facebook"></i></a></li>
        <li><a class="faicon-twitter" href="#"><i class="fa fa-twitter"></i></a></li>
        <li><a class="faicon-linkedin" href="#"><i class="fa fa-linkedin"></i></a></li>
        <li><a class="faicon-google-plus" href="#"><i class="fa fa-google-plus"></i></a></li>
        <li><a class="faicon-instagram" href="#"><i class="fa fa-instagram"></i></a></li>
        <li><a class="faicon-tumblr" href="#"><i class="fa fa-tumblr"></i></a></li>
      </ul>
    </div>
  </footer>
</div>
<div class="wrapper row5">
  <div id="copyright" class="clear"> 
    <p class="fl_left">쌍용 강북 교육 센터<a href="https://github.com/seyounggg">D 강의실</a></p>
    <p class="fl_right">Template by <a href="" title="Free Website Templates">홍길동</a></p>
  </div>
</div>
</body>
</html>