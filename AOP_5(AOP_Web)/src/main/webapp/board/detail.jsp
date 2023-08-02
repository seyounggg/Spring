<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row {
  margin: 0px auto;
  width:800px;
}
</style>
</head>
<body>
<div class="wrapper row3">
  <main class="container clear">
    <div class="row">
      <h2 class="sectiontitle">내용 보기</h2>
       <table class="table">
	      <tr>
	        <th class="text-center" width=20%>번호</th>
	        <td class="text-center" width=30%>${vo.no }</td>
	        <th class="text-center" width=20%>작성일</th>
	        <td class="text-center" width=30%>${vo.dbday }</td>
	      </tr>
	      <tr>
	        <th class="text-center" width=20%>이름</th>
	        <td class="text-center" width=30%>${vo.name }</td>
	        <th class="text-center" width=20%>조회수</th>
	        <td class="text-center" width=30%>${vo.hit }</td>
	      </tr>
	      <tr>
	        <th class="text-center" width=20%>제목</th>
	        <td colspan=3>${vo.subject }</td>
	      </tr>
	      <tr>
	        <td colspan=4 class="text-left" valign="top" height="200">
	          <pre style="white-space: pre-wrap;background-color: white;border: none">${vo.content }</pre>
	        </td>
	      </tr>
	      <tr>
	        <td colspan=4 class="text-right">
	          <a href="../board/reply.do?pno=${vo.no }" class="btn btn-xs btn-info">답변</a>
	          <a href="../board/update.do?no=${vo.no }" class="btn btn-xs btn-warning">수정</a>
	          <a href="../board/delete.do?no=${vo.no }" class="btn btn-xs btn-danger">삭제</a>
	          <a href="../board/list.do" class="btn btn-xs btn-success">목록</a>
	        </td>
	      </tr>
	    </table>
    </div>
  </main>
</div>
</body>
</html>