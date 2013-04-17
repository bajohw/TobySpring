package mirae.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

public class UserDAO {
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	// ����ڸ� ����ϴ� �޼ҵ�
	public void addUser(UserVO userVO) throws ClassNotFoundException, SQLException{
		Connection c  = dataSource.getConnection();
		
		PreparedStatement ps = c.prepareStatement("insert into tb_test_user(id, name, password) values (?,?,?)");
		
		ps.setString(1, userVO.getId());
		ps.setString(2, userVO.getName());
		ps.setString(3, userVO.getPassword());
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	// ����ڸ� ��ȸ�ϴ� �޼ҵ�
	public UserVO getUser(String id) throws ClassNotFoundException, SQLException{
		Connection c  = dataSource.getConnection();
		
		PreparedStatement ps = c.prepareStatement("select * from tb_test_user where id=?");
		
		ps.setString(1, id);
				
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		UserVO userVO = new UserVO();
		userVO.setId(rs.getString("id"));
		userVO.setName(rs.getString("name"));
		userVO.setPassword(rs.getString("password"));
		
		rs.close();
		ps.close();
		c.close();
		
		return userVO;
	}
}
