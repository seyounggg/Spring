<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="https://unpkg.com/bootstrap/dist/css/bootstrap.min.css"/>
<link type="text/css" rel="stylesheet" href="https://unpkg.com/bootstrap-vue@latest/dist/bootstrap-vue.css"/>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>
<script src="https://unpkg.com/babel-polyfill@latest/dist/polyfill.min.js"></script>
<script src="https://unpkg.com/bootstrap-vue@latest/dist/bootstrap-vue.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 450px;
}
</style>
</head>
<body>
	<div class="container">
		<h3 class="text-center">로그인</h3>
		<div class="row">
			<table class="table">
				<tr>
					<th width=25% class="text-end">아이디</th>
					<td width=75%>
						<input type=text ref="id" size=15 class="input-sm" v-model="id">
																										<!-- newVue data에 있는 id와 연결 -->
					</td>
				</tr>
				<tr>
					<th width=25% class="text-end">비밀번호</th>
					<td width=75%>
						<input type=password ref="pwd" size=15 class="input-sm" v-model="pwd">
					</td>
				</tr>
				<tr>
					<td colspan=2 class="text-center">
						<input type=button value="로그인" class="btn btn-sm btn-danger" @click="login()">
						<a href="../member/join.do" class="btn btn-sm btn-primary">회원가입</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
<script>
	new Vue({
		el:'.container',
		data:{
			id:'',
			pwd:''
		},
		methods:{
			login:function(){
				if(this.id.trim()===""){
					// this.id => 문자열
					this.$refs.id.focus()
					// this.$refs.id태그!
					return;
				}
				if(this.pwd.trim()===""){
					this.$refs.pwd.focus()
					return;
				}
				axios.post("../member/login_ok.do",null,{
					params:{
						id:this.id,
						pwd:this.pwd
					}
				}).then(response=>{
					// 처리는 then에서!!
					// restcontroller에서 보내는 result 를 then에서 받는다
					let res=response.data
					if(res==="noid"){
						alert("아이디가 존재하지 않습니다.")
						this.id=''
						this.pwd=''
						this.$refs.id.focus()
					} else if(res==="nopwd"){
						alert("비밀번호가 틀립니다.")
						this.pwd=''
						this.$refs.pwd.focus()
					} else {
						location.href="../food/food_category.do";
					}
				})
			}
		}
	})
</script>
</body>
</html>