package mirae.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import mirae.user.UserDAO;
import mirae.user.UserVO;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserDAOTest {
	
	@Test
	public void addAndGet() throws SQLException, ClassNotFoundException{
		ApplicationContext context = new GenericXmlApplicationContext("/spring/springcontext/applicationContext.xml");
		
		UserDAO userDAO = context.getBean("userDAO", UserDAO.class);
		
		UserVO userVO = new UserVO();
		userVO.setId("guest");
		userVO.setName("SunSinLee");
		userVO.setPassword("1234");
		
		userDAO.addUser(userVO);
		
		UserVO newUserVO = userDAO.getUser(userVO.getId());
		
		assertThat(newUserVO.getName(), is(userVO.getName()));
		assertThat(newUserVO.getPassword(), is(userVO.getPassword()));
	}

}
