package dao;

import java.sql.*;
import pojos.*;

public interface IVoterDao {
	
	Voter authenticateVoter(String email,String password) throws SQLException;
	
	String changeVotingStatus(int id) throws SQLException;

}
