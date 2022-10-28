package org.zerock.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)

//Test for Controller
/* Servlet의 ServletContext를 이용하기 위해 @WebAppConfiguration 사용하지만
스프링에서는 WebApplicationContext 존재하기 위해서 이용 */
@WebAppConfiguration	

@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
//Java Config
//@ContextConfiguration(classes={org.zerock.config.RootConfig.class, org.zerock.config.ServletConfig.class})
@Log4j
public class BoardControllerTests {
	@Setter(onMethod_ = {@Autowired})
	private WebApplicationContext ctx;
	
	/* MockMvc는 '가짜 mvc'
	가짜로 URL과 파라미터등을 브라우저에서 사용하는 것처럼 만들어
	Controller를 실행 해볼 수 있음 */
	private MockMvc mockMvc;
	
	//@Before가 적용된 메서드는 모든 테스트 전에 매번 실행되는 메서드가 됨
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	
	  @Test public void testList() throws Exception { log.info(
	 //MockMvcRequestBuilders를 이용해 GET방식의 호출 함
	  mockMvc.perform(MockMvcRequestBuilders.get("/board/list")) 
	 //BoardController의 getList()에 반환된 결과를 이용해 
	 .andReturn() 
	 //Model에 어떤 데이터들이 담겨있는지 확인
	  .getModelAndView() .getModelMap()); }
	
	@Test
	public void testRegister() throws Exception {
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
				.param("title", "테스트 새글 제목")
				.param("content", "테스트 새글 내용")
				.param("writer", "user00")
				).andReturn()
				.getModelAndView()
				.getViewName();
		log.info(resultPage);
	}
	
	@Test
	public void testGet() throws Exception {
		
		log.info(mockMvc.perform(MockMvcRequestBuilders
				.get("/board/get")
				.param("bno", "7"))
				.andReturn()
				.getModelAndView().getModelMap());
	}
	
	@Test
	public void testModify() throws Exception  {
		
		String resultPage = mockMvc
				.perform(MockMvcRequestBuilders.post("/board/modify")
						.param("bno", "1")
						.param("title", "수정된 테스트 새글 제목")
						.param("content", "수정된 테스트 새글 내용")
						.param("writer", "user00"))
				.andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
	}
	
	@Test
	public void testRemove() throws Exception {
		//삭제전 데이터베이스에 게시물 번호 확인할 것
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
				.param("bno", "26"))
				.andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
	}
	
}
