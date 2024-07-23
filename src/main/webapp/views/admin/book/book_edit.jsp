<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.book.admin.book.vo.Book"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 도서 등록</title>
<link rel="stylesheet" href="../../resources/css/create_book.css">
</head>
<body>
<header>
    <nav id="header_nav">
        <a href="#" id="main_logo">Knock Book</a>
        <ul>
            <li><a href="#" class="header_list">회원 관리</a></li>
            <li><a href="#" class="header_list">관리자 페이지</a></li>
            <li><a href="#" class="header_list" id="header_join">admin</a></li>
            <li><a href="#" class="header_list" id="header_join">회원가입</a></li>
        </ul>
    </nav>
</header>
<main>
    <section id="left">
        <ul id="my">
            <li>회원관리</li>
            <li>이벤트 관리</li>
            <li>도서 / 관리 페이지</li>
            	<ul>
            		<li><a href="/book/create">도서 등록</a></li>
					<li><a href="/book/list">도서 목록 조회</a></li>
					<li><a href="/book/requestEnd">도서 신청 조회</a></li>
            	</ul>
            <li>문의 사항 관리 페이지</li>
        </ul>
    </section>
     <%@ page import="com.book.admin.book.vo.Book, java.util.*" %>
    <section id="right">
        <div class="book_icon">
            <div class="book">
          <% 
                List<Map<String, String>> resultList = (List<Map<String, String>>) request.getAttribute("resultList");
                Map<String, String> book = null;
                if (resultList != null && !resultList.isEmpty()) {
                    book = resultList.get(0);
                %>
                <form action="/book/edit" name="book_edit_form" method="post">
                    <input type="hidden" name="books_no" id="books_no" value="<%= book.get("books_no") %>">
                    <label for="book_img">이미지 등록:</label>
                    <input type="text" name="books_img" id="book_img" value="<%= book.get("books_img") %>">
                    <hr>
                    <label for="book_title">도서명: </label>
                    <input type="text" name="books_title" id="book_title" value="<%= book.get("books_title") %>">
                    <hr>
                    <label for="books_author">저자: </label>
                    <input type="text" name="books_author" id="book_author" value="<%= book.get("books_author") %>">
                    <hr>
                    <label for="books_publisher_name">출판사: </label>
                    <input type="text" name="books_publisher_name" id="books_publisher_name" value="<%= book.get("books_publisher_name") %>">
                    <hr>
                    <label for="books_category_no">카테고리: </label>
                    <select name="books_category_no" id="books_category_no">
                        <option value="0">선택안함</option>
                        <option value="1" <%= "1".equals(book.get("books_category_no")) ? "selected" : "" %>>총류</option>
                        <option value="2" <%= "2".equals(book.get("books_category_no")) ? "selected" : "" %>>철학</option>
                        <option value="3" <%= "3".equals(book.get("books_category_no")) ? "selected" : "" %>>종교</option>
                        <option value="4" <%= "4".equals(book.get("books_category_no")) ? "selected" : "" %>>사회과학</option>
                        <option value="5" <%= "5".equals(book.get("books_category_no")) ? "selected" : "" %>>순수과학</option>
                        <option value="6" <%= "6".equals(book.get("books_category_no")) ? "selected" : "" %>>기술과학</option>
                        <option value="7" <%= "7".equals(book.get("books_category_no")) ? "selected" : "" %>>예술</option>
                        <option value="8" <%= "8".equals(book.get("books_category_no")) ? "selected" : "" %>>언어</option>
                        <option value="9" <%= "9".equals(book.get("books_category_no")) ? "selected" : "" %>>문학</option>
                        <option value="10" <%= "10".equals(book.get("books_category_no")) ? "selected" : "" %>>역사</option>
                    </select>
                    <hr>
                    <input type="submit" value="수정" onclick="book_update();">
                </form>
                    <input type="button" value="초기화" onclick="book_reset();">
                <% } else { %>
                <p>도서 정보를 불러올 수 없습니다.</p>
                <% } %>
                </div>
        </div>
    </section>
</main>
<script>
function book_update() {
    const form = document.book_edit_form;
    if (!form.book_img.value) {
        alert("이미지주소를 입력하세요");
    } else if (!form.book_title.value) {
        alert("도서명을 입력하세요");
    } else if (!form.book_author.value) {
        alert("저자명을 입력하세요");
    } else if (!form.book_publihser.value) {
        alert("출판사를 입력하세요");
    }else if (form.book_category.value == 0) {
        alert("카테고리를 선택해주세요");
    } 
    else {
        alert("등록되었습니다!");
        form.submit();
    };
}

function book_reset() {
    const form = document.book_edit_form;
    form.book_img.value = '';
    form.book_title.value = '';
    form.book_author.value = '';
    form.book_publisher.value = '';
    form.book_category.selectedIndex = 0;  
}
</script>
</body>
</html>