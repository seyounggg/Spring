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
	width: 900px;
}
</style>
</head>
<body>
	<div class="container">
		<h3 class="text-center">회원가입</h3>
		<div class="row">
			<table class="table">
				<tr>
					<td width=20% class="text-end">아이디</td>
					<td width=80%>
						<input type=text ref="id" size=15 class="input-sm" v-model="id">
					</td>
				</tr>
				<tr>
					<td width=20% class="text-end">비밀번호</td>
					<td width=80%>
						<input type=password ref="pwd" size=15 class="input-sm" v-model="pwd">
					</td>
				</tr>
				<tr>
					<td width=20% class="text-end">이름</td>
					<td width=80%>
						<input type=text ref="name" size=15 class="input-sm" v-model="name">
					</td>
				</tr>
				<tr>
					<td width=20% class="text-end">성별</td>
					<td width=80%>
						<!-- name => 그룹? -->
						<input type=radio value="남자" checked ref="sex" v-model="sex">남자
						<input type=radio value="여자" ref="sex" name="sex" v-model="sex">여자
					</td>
				</tr>
				<tr>
					<td colspan=2 class="text-center">
						<input type=button value="회원가입" class="btn btn-sm btn-danger" @click="join()">
						<input type=button value="취소" class="btn btn-sm btn-danger" onclick="javascript:history.back()">
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
			pwd:'',
			name:'',
			sex:'남자'
		},
		methods:{
			join:function(){
				let id=this.$refs.id.value;
				if(id.trim()===""){
					this.$refs.id.focus()
					return;
				}
				let pwd=this.$refs.pwd.value;
				if(pwd.trim()===""){
					this.$refs.pwd.focus()
					return;
				}
				let name=this.$refs.name.value;
				if(id.trim()===""){
					this.$refs.name.focus()
					return;
				}
				//alert(this.sex)
				/*
					post => params
					axios.post(url,config,params)
					- config : 파일 업로드 할 때 사용
										multipart/form-data
				 */
				axios.post('http://localhost/web/member/join_ok.do',null,{
					params:{
						id:this.id,
						pwd:this.pwd,
						name:this.name,
						sex:this.sex
					}
				}).then(response=>{
					let res=response.data;
					console.log(res)
					if(res==='no'){
						alert("회원가입 실패")
						this.id=''
						this.pwd=''
						this.name=''
						this.sex='남자'
						this.$refs.id.focus()
					} else {
						location.href="../member/login.do";
					}
				})
			}
		}
	})
</script>
</body>
</html>