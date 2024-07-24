<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.book.member.user.vo.User" %>
<meta charset="UTF-8">
<style>
   @charset "UTF-8";
   
   @font-face {
       font-family: 'JalnanGothic';
       src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_231029@1.1/JalnanGothic.woff') format('woff');
       font-weight: normal;
       font-style: normal;
   }
   
    body {
        display: flex;
        justify-content: center;
        align-items: center;
        background-color: #fff;
        height: 100vh;
        padding: 0;
        margin: 0;
        font-family: Arial, sans-serif;
    }

    .container {
        width: 100%;
        max-width: 600px;
        margin-top: -50px; 
    }

    #main_logo {
        font-size: 33px;
        color: rgb(224, 195, 163);
        text-decoration: none;
        font-family: 'JalnanGothic';
    }
   
   .navbar {
       display: flex;
       justify-content: center;
       align-items: center;
       height: 100px;
   }
   
   .text-center {
       margin-bottom: 30px; 
   }
   
   .form-group {
       margin-bottom: 15px;
       display: flex;
       align-items: center;
   }
   
   label {
       width: 150px;
       font-weight: bold;
   }
   
   input[type="password"]{
       flex: 1;
       padding: 10px;
       border: 1px solid #ccc;
       border-radius: 5px;
       font-size: 16px;
   }
   
   .button-group {
       display: flex;
       justify-content: center;
       gap: 20px;
       margin-top: 40px; 
   }
   
   .btn {
       padding: 10px 20px;
       background-color: #007BFF;
       color: white;
       border: none;
       border-radius: 5px;
       cursor: pointer;
       width: 100px;
   }
   
   .btn-secondary {
       background-color: #6c757d;
   }
   
</style> 
    <div class="container">
        <div class="text-center">
        <%User u= (User)session.getAttribute("user");%>
            <h2>회원 탈퇴</h2>
            <hr>
            <strong><p>탈퇴 정책</p></strong>
            <p>탈퇴한 아이디는 다시 사용할 수 없습니다.</p>
        </div>
        <div>
            <form name="delete_form" action="/user/deleteEnd" method="post">
                <div class="form-group">
                    <div style="width: 150px;font-weight: bold;">현재 아이디 :</div>
                    <%=u.getUser_id() %>
                </div>
                <div class="form-group">
                    <label for="pw">현재 비밀번호 :</label>
                    <input type="password" id="pw" name="pw" placeholder="현재 비밀번호">
                </div>
                <div class="button-group">
                    <button type="button" onclick="delete_button();" class="btn">확인</button>
                    <button type="button" class="btn btn-secondary" onclick="location.href='/'">취소</button>
                </div>
            </form>
        </div>
    </div>
    <script>
       function delete_button() {
        const form = document.delete_form;
        if (form.pw.value == '') {
            alert('비밀번호를 입력하세요.');
            form.name.focus();  
        } else {
            form.submit();
           }
       }
    </script>