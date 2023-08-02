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
      <h2 class="sectiontitle">답변하기</h2>
      <form method="post" action="../board/reply_ok.do">
    <!-- 파일 업로드시 "enctype="multipart/form-data" 무족권@@ -->
    <table class="table">
      <tr>
        <th width=15% class="text-right">이름</th>
        <td width=85%>
          <input type=text name=name size=15 class="input-sm">
          <input type=hidden name=pno value="${pno }">
        </td>
      </tr>
      <tr>
        <th width=15% class="text-right">제목</th>
        <td width=85%>
          <input type=text name=subject size=50 class="input-sm">
        </td>
      </tr>
      <tr>
        <th width=15% class="text-right">내용</th>
        <td width=85%>
          <textarea rows="10" cols="50" name="content"></textarea>
        </td>
      </tr>
      <tr>
        <th width=15% class="text-right">비밀번호</th>
        <td width=85%>
          <input type=password name=pwd size=10 class="input-sm">
        </td>
      </tr>
      <tr>
        <td colspan=2 class="text-center">
          <button class="btn btn-sm btn-primary">답변</button>
          <input type=button value=취소 class="btn btn-sm btn-warning" onclick="javascript:history.back()">
        </td>
      </tr>
    </table>
    </form>
    </div>
  </main>
</div>
</body>
</html>