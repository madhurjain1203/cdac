package dao;

import java.sql.*;
import static utils.DBUtils.fetchDBConnection;
import pojos.Voter;

public class VoterDaoImp implements IVoterDao {
	
	private Connection conn;
	private PreparedStatement pst1,pst4;	
	
	public VoterDaoImp() throws ClassNotFoundException,SQLException
	{
		conn = fetchDBConnection();
		pst1 = conn.prepareStatement("select * from voters where email=? and password=?");
	//	pst2 = conn.prepareStatement("select status from voters where email=?");
	//	pst3 = conn.prepareStatement("select role from voters where email=?");
		pst4 = conn.prepareStatement("update voters set status=? where id =?");
		System.out.println("Voter DAO created..");
		
	}

	@Override
	public Voter authenticateVoter(String email, String password) throws SQLException {
		pst1.setString(1, email);
		pst1.setString(2, password);
		try (ResultSet rst = pst1.executeQuery()) {
			if (rst.next())
				return new Voter(rst.getInt(1), rst.getString(2), email, password, rst.getInt(5),
						rst.getString(6));
		}
		return null;
	}
	
	@Override
	public String changeVotingStatus(int id) throws SQLException {
		pst4.setInt(1,1);
		pst4.setInt(2, id);
		int rst=pst4.executeUpdate();
		if(rst>0) {
			return "you have voted successfully";
		}
		return "Not voted yet";
	}
	
	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		if (conn != null)
			conn.close();
		System.out.println("Voter dao cleaned up....");
	}

}
