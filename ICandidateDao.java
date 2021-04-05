package dao;

import java.sql.SQLException;
import java.util.List;

public interface ICandidateDao {
	
	List<String> getAllCandidates() throws SQLException;
	String incrVote(int id) throws SQLException;

}
