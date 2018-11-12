package com.db;
import java.sql.*;
import java.util.Scanner;

public class connectToDB {
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//connect to the sqlite;
		Connection c=null;
				try {
					Class.forName("org.sqlite.JDBC");
					c = DriverManager.getConnection("jdbc:sqlite:info.db");
					System.out.println("connect Successfully");
					//add users
					System.out.print("1-add\n"+ "2-slection\n"+"3-Delete\n"+"4-update\n");
					Scanner reader = new Scanner(System.in);
					Statement stmt = c.createStatement();
					int processIndex = reader.nextInt();
					switch(processIndex) {
					case 1:
						Scanner username = new Scanner(System.in);
						Scanner passwords = new Scanner(System.in);
						System.out.print("Enter the user name: ");
						String user_name = username.nextLine();
						System.out.print("enter the passwords: ");
						String password = passwords.nextLine();
						//what is mean of '"+user_name+"'
						String SQLAdd = "insert into admins(user_name,password) values('"+user_name+"','"+password+"')";
						//what is stmt doing/????
						stmt.executeUpdate(SQLAdd);
						c.commit();//add//delete//uodate
						stmt.close();
						c.close();
						break;
					case 2:
						//selection data from table
						String sqlRead = "select * from admins";
						ResultSet rs = stmt.executeQuery(sqlRead);
						while(rs.next()) {
							int id = rs.getInt("id");
							String UserName = rs.getString("user_name");
							String Password = rs.getString("password");
							System.out.println(id +"\t"+UserName+"\t"+Password);
						}
						rs.close();
						stmt.close();
						c.close();
					case 3:
						//delete record;
						Scanner idIn = new Scanner(System.in);
						System.out.println("Enter ID: ");
						int id = idIn.nextInt();
						String SQLDelete = "delete from admins where id="+id;
						stmt.executeUpdate(SQLDelete);
						stmt.close();
						c.commit();
						c.close();
						break;
					case 4:
						Scanner idInd = new Scanner(System.in);
						Scanner passwordInd = new Scanner(System.in);
						System.out.print("Enter the id: ");
						int id1  = idInd.nextInt();
						System.out.print("enter new passwords: ");
						String password1 = passwordInd.nextLine();
						//what is mean of '"+user_name+"'
						String SQLUpdate = "Update admins set password= '"+password1 +"'"+ "where id="+id1;
						//what is stmt doing/????
						stmt.executeUpdate(SQLUpdate);
						c.commit();//add//delete//uodate
						stmt.close();
						c.close();
						break;
					default:
						break;
					}
					
				}
				catch(Exception ex) {
					System.out.println("cannot connectS");
					System.exit(0);
				}
				
			
	}

} 
