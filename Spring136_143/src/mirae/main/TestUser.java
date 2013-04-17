package mirae.main;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import mirae.user.UserDAO;
import mirae.user.UserVO;

public class TestUser {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		// TODO Auto-generated method stub
//		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		ApplicationContext context = new GenericXmlApplicationContext("/spring/springcontext/applicationContext.xml");
		
		UserDAO userDAO = context.getBean("userDAO", UserDAO.class);
		
		UserVO userVO = new UserVO();
		userVO.setId("guest");
		userVO.setName("SunSinLee");
		userVO.setPassword("1234");
		
		userDAO.addUser(userVO);
		
		System.out.println("----------User Insert----------");
		System.out.println("UserId : " + userVO.getId());
		System.out.println("--------------End--------------");
		
		UserVO newUserVO = userDAO.getUser(userVO.getId());
		
		System.out.println("----------User Select----------");
		System.out.println(newUserVO.getName());
		System.out.println(newUserVO.getPassword());
		System.out.println(newUserVO.getId());
		System.out.println("--------------End--------------");
	}

}
