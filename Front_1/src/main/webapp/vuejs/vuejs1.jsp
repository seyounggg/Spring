<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width:100%
}
</style>
<!-- <script>
$(function(){
	$('#msg').keyup(function(){
		let m=%('#msg').val();
		$('#print').text(m)
	})
})
</script> -->
</head>
<body>
<div class="container">
  <div class="row">
  <!-- 양방향 통신 : 입력값을 받아서 출력을 하는 역할을 수행 -->
  입력:<input type="text" v-model="msg" id="msg" size=30 class="input-sm">
  <!-- v-model => 멤버변수값을 변경 -->
    <div id="print"><!-- 출력 : msg에 저장된 내용을 출력 -->
  	{{msg}}
    </div>
  </div>
</div>
<script>
	new Vue({
		el:'.container',
		data:{
			msg:''
		}
	})
</script>
</body>
</html>