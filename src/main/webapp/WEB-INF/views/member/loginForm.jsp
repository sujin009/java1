<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 폼</title>
<style>
    .container {
        max-width: 300px;
        margin-top: 100px;
        text-align: center; /* 컨테이너 내의 모든 요소를 가운데 정렬 */
    }
    .form-group {
        text-align: left; /* 폼 그룹 요소는 왼쪽 정렬 */
    }
    .form-group > button {
        display: inline-block; /* 버튼을 인라인 블록으로 설정 */
    }
</style>
</head>
<body>
<div class="container">
    <form action="login.do" method="post">
        <h1>Login</h1><br><br>
        <div class="form-group">
            <label for="id">ID:</label>
            <input type="text" class="form-control" placeholder="ID 입력" id="id" name="id" autocomplete="none">
        </div>
        <div class="form-group">
            <label for="pw">Password:</label>
            <input type="password" class="form-control" placeholder="Password 입력" id="pw" name="pw">
        </div>
        <button type="submit" class="btn btn-dark">Login</button>
    </form>
</div>
</body>
</html>
