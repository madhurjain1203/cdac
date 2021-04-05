package dao;

import static utils.DBUtils.fetchDBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import pojos.Candidate;

public class CandidateDaoImp implements ICandidateDao {
	
	private Connection conn;
	private PreparedStatement pst1,pst2;
	public List<String> list=new ArrayList<String>();
	
	public CandidateDaoImp() throws ClassNotFoundException,SQLException {
			conn = fetchDBConnection();
			pst1 = conn.prepareStatement("select name from candidates");
			pst2 = conn.prepareStatement("update candidates  set votes=votes+1 where id=?");
			System.out.println("Candidate dao created");
		
	}
	
	@Override
	public List<String> getAllCandidates() throws SQLException{
		
		try(ResultSet rst =pst1.executeQuery()){
			while(rst.next()) {
				list.add(rst.getString(2));
				
			}
		}
		
		return list;
	
		
	}
	
	public String incrVote(int id) throws SQLException{
		pst2.setInt(1,id);
		
		int rst = pst2.executeUpdate();
		if(rst>0) {
			return "Vote updated";
		}
		return "Vote not updated";
		
		
	
	}


}
