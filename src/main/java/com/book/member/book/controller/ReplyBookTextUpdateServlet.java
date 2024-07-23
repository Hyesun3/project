package com.book.member.book.controller;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.member.book.dao.BookReplyDao;

@WebServlet("/member/book/btUpdateReply")
public class ReplyBookTextUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ReplyBookTextUpdateServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 독후감 번호는 페이지 조회할 때만
        int btNo = Integer.parseInt(request.getParameter("bt_no"));

        // 업데이트는 댓글번호, 업데이트할댓글내용
        int btReplyNo = Integer.parseInt(request.getParameter("bt_reply_no"));
        String btReply = request.getParameter("btUpdateContent");
        int result = new BookReplyDao().bkReplyupdateReply(btReplyNo,btReply);

        if(result > 0) {
            System.out.println("성공");
            // 댓글 추가 성공시 원래 있던 페이지 ( 상세조회 )
            response.sendRedirect(request.getContextPath() + "/book/detail?bt_no="+btNo);
        }else {
            System.out.println("실패");
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}