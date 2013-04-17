package mirae.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import mirae.user.UserDAO;
import mirae.user.UserVO;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

public class UserDAOTest {
	private UserDAO userDAO;
	private UserVO user1;
	private UserVO user2;
	private UserVO user3;
	
	@Before
	public void setUp(){
		ApplicationContext context = new GenericXmlApplicationContext("/spring/springcontext/applicationContext.xml");
		
		this.userDAO = context.getBean("userDAO", UserDAO.class);
		this.user1 = new UserVO("gyumee", "박성철", "springno1");
		this.user2 = new UserVO("leegw700", "이길원", "springno2");
		this.user3 = new UserVO("bumjin", "박범진", "springno3");
	}
	
	@Test
	public void addAndGet() throws SQLException{
		UserVO user1 = new UserVO("gyumee", "박성철", "springno1");
		UserVO user2 = new UserVO("leegw700", "이길원", "springno2");
		
		userDAO.deleteAll();
		assertThat(userDAO.getCount(), is(0));
		
		userDAO.addUser(user1);
		userDAO.addUser(user2);
		assertThat(userDAO.getCount(), is(2));
		
		UserVO newUserVO_1 = userDAO.getUser(user1.getId());
		assertThat(newUserVO_1.getName(), is(user1.getName()));
		assertThat(newUserVO_1.getPassword(), is(user1.getPassword()));
		
		UserVO newUserVO_2 = userDAO.getUser(user2.getId());
		assertThat(newUserVO_2.getName(), is(user2.getName()));
		assertThat(newUserVO_2.getPassword(), is(user2.getPassword()));
	}
	
	@Test
	public void count() throws SQLException{
		userDAO.deleteAll();
		assertThat(userDAO.getCount(), is(0));
		
		userDAO.addUser(user1);
		assertThat(userDAO.getCount(), is(1));
		
		userDAO.addUser(user2);
		assertThat(userDAO.getCount(), is(2));
		
		userDAO.addUser(user3);
		assertThat(userDAO.getCount(), is(3));
	}
	
	@Test(expected=EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException{
		userDAO.deleteAll();
		assertThat(userDAO.getCount(), is(0));
		
		userDAO.getUser("unknown_id");
	}
}
