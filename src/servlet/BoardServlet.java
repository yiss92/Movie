package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.BoardService;
import vo.FreeArticle;
import vo.FreeArticlePage;

@WebServlet(urlPatterns="/board" )
public class BoardServlet extends HttpServlet{
     @Override
     protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           process( req, resp);
     }
     
     @Override
     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           process( req, resp);
     }
     
     private void process(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
           
           String type = request.getParameter( "type");
           String viewpath = "";

           BoardService service = BoardService. getInstance();
           HttpSession session = request.getSession();
           
            try {
                 if ( type == null || type.equals( "list")) {
                     FreeArticlePage freeArticlePage = service.getArticlePage( request);
                      request.setAttribute( "freeArticlePage", freeArticlePage);
                      viewpath= "FreeBoardMenu.jsp";
                     
                } else if( type.equals( "write_form")){
                      viewpath = "FreeBoardWriteForm.jsp";
                      //글쓰기 저장 버튼이 안눌려짐
                
                } else if( type.equals( "write")){			// 글쓰기 완료, 실패
                      int result = service.write( request);
                      request.setAttribute( "result", result);
                      viewpath = "write.jsp";
                     
                } else if( type.equals( "read")){			// 글조회
                     FreeArticle free= service.read( request);
                      request.setAttribute( "free", free);
                      viewpath = "read.jsp";
                     
                } else if( type.equals( "update_form")){	// 글수정 틀(보류)
                     FreeArticle ori= service.readWithOutReadCount( request);
                      session.setAttribute( "num", ori.getArticleNo());
                      request.setAttribute( "ori", ori);
                      viewpath = "update_form.jsp";
                     
                } else if( type.equals( "update")){			// 글 수정 완료
                      service.ArticleUpdate( request);
                      viewpath = "correctionPage.jsp";
                     
                } else if( type.equals( "delete_form")){
                     FreeArticle ori= service.readWithOutReadCount( request);
                      request.setAttribute( "ori", ori);
                      viewpath = "delete_form.jsp";
                
                } else if( type.equals( "delete")){			// 글 삭제 완료
                     System. out.println( "delete");                   
                      service.deleteArticle( request);                 
                      viewpath = "delete.jsp";
                }
                

           } catch (Exception e) {
                 viewpath = "board_error.jsp";
           }


           RequestDispatcher dispatcher = request.getRequestDispatcher( viewpath);
            dispatcher.forward( request, response);
     }
}
