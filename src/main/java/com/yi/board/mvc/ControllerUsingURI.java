package com.yi.board.mvc;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerUsingURI extends HttpServlet{
	//command와 class가 들어가있는 map
	//command : ex) simple.do
	//class : ex) mvc.simple.Simplehandler
	
	//command와 class 매치되도록해준다.==> properties 파일에 있는 /simple.do = mvc.simple.SimpleHandler
	private HashMap<String, CommandHandler> commandHandlerMap = new HashMap<>();
	
	//init => 서버시작 시 자동으로 호출
	@Override
	public void init() throws ServletException {
											//web.xml에 있는 param-name과 이름이 같아야 함.
		String configFile = getInitParameter("configFile");	//properties의 파일주소
		Properties prop = new Properties();
		String configFilePath = getServletContext().getRealPath(configFile); //상대주소를 절대주소로 바꿔서 가져옴. ==> configFilePath : 절대주소
		//properties에 있는 내용들을 꺼낸다.(try~catch)
		try(FileReader fis = new FileReader(configFilePath)) {
			prop.load(fis);	
		} catch (Exception e) {
			throw new ServletException(e);
		}
		Iterator keyIter = prop.keySet().iterator();
		while(keyIter.hasNext()){
			String command = (String) keyIter.next();	//프로퍼티 key ==> 실행 시 properties에 있는 /simple.do가 key가 됨.
			String handlerClassName = prop.getProperty(command);	//프로퍼티 key에 해당하는 값	=> /simple.do의 값 : mvc.simple.SimpleHandler
			try {
				Class<?> handlerClass = Class.forName(handlerClassName);	//38번째의 단순한 문자를 클래스화 시킨다.
				CommandHandler handlerInstance = (CommandHandler) handlerClass.newInstance(); 	//클래스 생성(new), new 사용하지 못하는 이유 : 어떤 클래스인지 몰라서
				commandHandlerMap.put(command, handlerInstance);	//map에 command와 클래스를 집어넣는다.
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}
	
	//Servlet => get(주소창에서 직접 입력할 때 호출), post(form에서 submit버튼 눌렀을 떄 호출) 만들 수 있음.
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//process 함수 호출하기.
		process(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(req, resp);
	}
	
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String command = request.getRequestURI();	//요청이 들어온 uri를 가지고 온다. ex) /chapter16MVC/simple.do
		
		//uri에서 프로젝트 이름으로 시작하는 지 판단.
		if(command.indexOf(request.getContextPath()) == 0){	//프로젝트 이름으로 시작한다면
			//request.getContextPath() => 프로젝트 이름 반환(/chapter16MVC)
			command = command.substring(request.getContextPath().length());	//프로젝트 이름을 잘라낸다 => /simple.do만 남게 됨.(/simple.do를 뽑아낸다.)
		}
		//여기서 /simple.do를 입력하면 /simple.do에 해당하는 값을 가져온다. ==> /simple.do에 해당하는 클래스가 반환됨.(여기서는 SimpleHandler 클래스)
		//왜냐하면 properties 파일에서 키랑 값을 맞춰놨기 때문에 클래스를 가져올 수 있음.
		CommandHandler handler = commandHandlerMap.get(command);
		
		//맵 안에 키에 해당하는 클래스가 없을 경우(properties에서 키랑 값 설정안했을 경우)
		if(handler == null){	//command에 해당하는 클래스가 없을 경우
			handler = new NullHandler();
		}
		
		String viewPage = null;
		try {
			//화면에 보이는 페이지
			viewPage = handler.process(request, response);	//simpleForm.jsp파일 보이게 됨.(SimpleHandler에서 get일 때 return 값을 "simpleForm.jsp"로 했기 때문)
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException();
		}
		
		if(viewPage != null){
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			//최종적으로 보이는 화면
			dispatcher.forward(request, response);
		}
	}
	
	
}
