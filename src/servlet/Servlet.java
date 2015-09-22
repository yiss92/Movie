package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import service.GradeService;
import service.MemberService;
import service.NowPlayingService;
import service.PremiereService;
import service.ReviewService;


@WebServlet(urlPatterns="Movie")
public class Servlet extends HttpServlet{
     @Override
     protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          process(req, resp);
     }
    
     @Override
     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          process(req, resp);
     }
    
     private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
          request.setCharacterEncoding("euc-kr");
          String type= request.getParameter("type");
          String viewpath="";         
         
          BoardService bService = BoardService.getInstance();
          GradeService gService = GradeService.getInstance();
          MemberService mService = MemberService.getInstance();
          NowPlayingService nService = NowPlayingService.getInstance();
          PremiereService pService = PremiereService.getInstance();
          ReviewService rService = ReviewService.getInstance();
         
         
          if(type == null){
               viewpath="FrontPage1.html";
          }
         
         
         
         
          RequestDispatcher dispatcher = request.getRequestDispatcher(viewpath);
          dispatcher.forward(request, response);
     }
}
