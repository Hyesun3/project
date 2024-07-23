<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <title>마이페이지 프로필 수정</title> 
	<script src="../../../resources/javascript/mypageSidebar.js"></script>  
	<style>

	.main_content {
	    max-width: 1300px; 
	    height: 750px;
	    margin: 5rem auto;
	    background-color: rgb(247, 247, 247);
	    display: flex;
	    flex-direction: row;
	}
	/* 사이드바 */
	.section1{
	    width: 20%;
	    margin-right: 2rem;
	    height: 100%;
	    background-color: white;
	    font-family: 'BMHANNAPro';
	}
	.menu {
	    list-style-type: none;
	    padding: 0;
	    height: 600px;
	    background-color: white;
	}
	
	.menu-item {
	    width: 100%;
	    background-color: white;
	    font-family: 'BMHANNAPro';
	    font-size: 22px;
	}
	
	.menu-item a {
	    color: black;
	    text-decoration: none;
	    display: block;
	    padding: 20px;
	    padding-left: 30px;
	    background-color: white;
	    transition: background-color 0.3s ease;
	    font-family: 'BMHANNAPro';
	}
	
	
	.menu-item a:hover {
	    background-color: rgb(247, 247, 247);
	}
	@keyframes slide-down {
	    0% {
	        opacity: 0;
	        transform: translateY(-10px);
	    }
	    100% {
	        opacity: 1;
	        transform: translateY(0);
	    }
	}
	
	@keyframes slide-up {
	    0% {
	        opacity: 1;
	        height: auto;
	    }
	    100% {
	        opacity: 0;
	        height: 0;
	        padding: 0;
	        margin: 0;
	        border: 0;
	    }
	}
	.submenu {
	    display: none;
	    list-style-type: none;
	    padding: 0;
	    margin-top: 5px;
	    overflow: hidden;
	}
	
	.submenu li a {
	    color: black;
	    text-decoration: none;
	    padding: 20px;
	    display: block;
	    transition: background-color 0.3s ease;
	}
	.submenu li a:hover {
	    background-color: white;
	}
	/* 나의 정보 form */
	.section2{
	    width: 100%;
	    background-color: white;
	    display: flex;
	    flex-direction: column;
	    justify-content: flex-start;
	    align-items: center;
	}
	</style>
<body>
    <%@ include file="../../include/header.jsp" %>
    <div class="main_content">
        <div class="section1">
            <ul class="menu">
                <li class="menu-item"><a href="#">나의 정보</a></li>
                <li class="menu-item">
                    <a href="#">독후감 목록</a>
                    <ul class="submenu">
                        <li><a href="#">&nbsp;&nbsp;&nbsp;&nbsp; 작성된 독후감</a></li>
                        <li><a href="#">&nbsp;&nbsp;&nbsp;&nbsp; 나만보기</a></li>
                    </ul>
                </li>
                <li class="menu-item"><a href="#">이벤트 참여 내역</a></li>
                <li class="menu-item"><a href="#">도서 신청</a></li>
                <li class="menu-item">
                    <a href="#">문의 사항</a>
                    <ul class="submenu">
                        <li><a href="#">&nbsp;&nbsp;&nbsp;&nbsp; 문의 사항 작성</a></li>
                        <li><a href="#">&nbsp;&nbsp;&nbsp;&nbsp; 문의 사항 목록</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <div class="section2">
			<%@ include file="/views/member/user/edit.jsp" %> 
        </div> 
    </div>
</body> 
    <!-- 마이페이지 드롭다운 -->
    <script>
	  document.addEventListener("DOMContentLoaded", function() {
	      const menuItems = document.querySelectorAll(".menu-item > a");
	
	      menuItems.forEach(function(item) {
	          const submenu = item.nextElementSibling;
	          let isOpen = false;
	
	          item.addEventListener("click", function(event) {
	              if (submenu) {
	
	                  if (isOpen) {
	                      submenu.style.animation = "slide-up 0.3s ease";
	                      
	                      setTimeout(function(){
	                          submenu.style.display = "none";
	                          submenu.style.animation = "";
	                      }, 300);
	
	                      isOpen = false;
	                  } else {
	                      submenu.style.display = "block";
	                      submenu.style.height = "auto";
	                      void submenu.offsetWidth;
	                      submenu.style.animation = "slide-down 0.3s ease";
	                      submenu.style.height = submenu.scrollHeight + "px";
	
	                      isOpen = true;
	                  }
	              }
	          });
	      });
	
	      const submenuLinks = document.querySelectorAll(".submenu li a");
	      submenuLinks.forEach(function(link) {
	          link.addEventListener("click", function(event) {
	          });
	      });
	  });
	  </script>
</html>