package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Observable;

import javax.swing.JOptionPane;
import javax.xml.ws.AsyncHandler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Basic {
	String id;
	static Connection con;
	public boolean logindata(String id,String password,int flag){		
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url="jdbc:sqlserver://localhost:1433;DatabaseName=stud_vote";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,"sa","lzq6z18");	
			Statement statement1=con.createStatement();
			Statement statement2=con.createStatement();
			String jid="select ID from voter where ID='"+id+"'";
			String jpassword="select passwd from voter where  passwd='"+password+"'";
			if(flag==0)
			{
				jid="select ID from admin where ID='"+id+"'";
				jpassword="select passwd from admin where passwd='"+password+"'";
			}
			ResultSet dateid=statement1.executeQuery(jid);
			ResultSet datepassword=statement2.executeQuery(jpassword);
			if(dateid.next()&&datepassword.next())
				{this.id=id;
				return true;}
			else {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	public ObservableList<List> settimes() {
		ObservableList<List>collectlist=FXCollections.observableArrayList();		
		int most=0;
		try {
			String sql="select  COUNT(distinct num) from candidate";
			Statement stmt=con.createStatement();
			ResultSet result=stmt.executeQuery(sql);
			if(result.next()){
				most=result.getInt(1);
				}
			for (int i = 0; i < most; i++) {
				List list=new List();
				list.setSdata("第"+String.valueOf(i+1)+"次");
				collectlist.add(i, list);
			}
			return collectlist;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("添加投票次数列表出错");
			return collectlist;
		}
	}

	public ObservableList<List> getCondidateList(int num) {
		ObservableList<List>collectlist=FXCollections.observableArrayList();	
		try {
			String sql="select distinct voter.name from candidate inner join voter "
					+"on candidate.num="+num+" and voter.ID=candidate.ID";
			Statement stmt=con.createStatement();
			ResultSet result=stmt.executeQuery(sql);
			/*
			PreparedStatement pStmt = con.prepareStatement("select distinct voter.name from candidate inner join voter "
					+"on candidate.num= ?"
					+" and voter.ID=candidate.ID");
			pStmt.setInt(1, num);
			*/
			while (result.next()) {
				List list=new List();
				list.setSdata(result.getString("name"));
				collectlist.add(list);
			}
			return  collectlist;
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("获取候选人失败");
		return  collectlist;
	}
	}
	
	public int getCondidateListTiket(int num,String name) {
		int ticket=0;
		try {
			String sql="select  COUNT(candidate_ID) " + 
					"from votion " + 
					"where num="+num+" and candidate_ID= " + 
					"(select ID " + 
					"from voter " + 
					"where name='"+name+"') ";
			Statement stmt=con.createStatement();
			ResultSet result=stmt.executeQuery(sql);
			if(result.next()){
				ticket=result.getInt(1);
				}
			return ticket;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("获取票数失败");
			return ticket;
		}
	}
	public void GiveVote(int num,String CondidateName) {
		try {
			String sql="declare @id nvarchar(20) "  + 
					"select @id=ID " + 
					"from voter " + 
					"where name='"+CondidateName+"' " + 
					"insert into votion values(1,'123',@id)";
			Statement stmt=con.createStatement();
			stmt.executeUpdate(sql);
//			ResultSet result=stmt.executeQuery(sql);			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("插入投票失败");
		}
	}
	public boolean WeatheVoted(int num,String CondidateName) {
		try {
		String sql="select * from votion "
				+ "where num="+num+" and voter_id="+id+" and candidate_id="
				+"(select ID " 
				+"from voter "
				+"where name='"+CondidateName+"')";
		Statement stmt=con.createStatement();
		ResultSet rs= stmt.executeQuery(sql);
		if(rs.next())
			return true;
		return false;
//		ResultSet result=stmt.executeQuery(sql);			
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("查询投票失败");
		return false;
	}
	}

	
	public String GetSelfId() {
		return id;
	}
	public void closedata() {
		try {
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
