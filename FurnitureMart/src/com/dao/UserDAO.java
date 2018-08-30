package com.dao;

import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.business.User;
import com.mysql.jdbc.Blob;
import com.business.Address;
import com.business.PasswordUtil;
import com.business.Product;

public class UserDAO {

	public String registerUser(User user){
		
		String firstname=user.getFirstName();
		String lastname=user.getLastName();
		String email=user.getEmail();
		String password=user.getPassword();
		String salt=user.getSalt();		
		String phonenumber=user.getPhonenumber();
		String gender=user.getGender();
		String birthdate=user.getDOB();
		Connection connection=null;
		PreparedStatement ps=null;
		
		
        try {
        	 connection = getConnection();
			String query="INSERT INTO User(email,firstname,lastname,phonenumber,password,salt,gender,birthdate) VALUES(?,?,?,?,?,?,?,?)";
			
			ps =(PreparedStatement) connection.prepareStatement(query);  

			  ps.setString(1,email);  
	          ps.setString(2,firstname);        
	          ps.setString(3,lastname);
	          ps.setString(4, phonenumber);
	          ps.setString(5,password); 
	          ps.setString(6, salt);
	          ps.setString(7, gender);
	          ps.setString(8, birthdate);
	          
	         int i= ps.executeUpdate();
	         if(i!=0){
	        	 System.out.println("details inserted successfully");
	        	  return "success";
	        	  
	         }else{
	        	 System.out.println("not successfull");
	        	 
	         }
	         connection.close();

			
	         ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
           
            try {
               if(connection!=null)
            	   connection.close();
            } catch(SQLException se) {
               se.printStackTrace();
            }
        }
		return null;
		
	}
	
	public User checkAdmin(String userName,String password){
		Connection connection=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		User user=new User();
		
		try {
			 connection = getConnection();
			String query="SELECT * from Admin";
			ps =(PreparedStatement) connection.prepareStatement(query);
			rs=ps.executeQuery();
			
			while(rs.next()){
				if(userName.equals(rs.getString("email")) && password.equals(rs.getString("password"))){
					user.setEmail(rs.getString("email"));
					user.setFirstName(rs.getString("firstname"));
					user.setLastName(rs.getString("lastname"));
					user.setPhonenumber(rs.getString("phonenumber"));
					 return user;
				}
			}
			connection.close();
			ps.close();
	         
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
           
            try {
               if(connection!=null)
            	   connection.close();
            } catch(SQLException se) {
               se.printStackTrace();
            }
        }
		  
		
		return null;
	}
	
	public User checkUser(String userName,String password) throws NoSuchAlgorithmException{
		Connection connection=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		User user=new User();
		PasswordUtil putil=new PasswordUtil();
		
		try {
			 connection = getConnection();
			String query="SELECT *from User";
			ps =(PreparedStatement) connection.prepareStatement(query);
			rs=ps.executeQuery();
			
			while(rs.next()){
				if(userName.equals(rs.getString("email")) && putil.hashAndSaltPassword(password, rs.getString("salt")).equals(rs.getString("password"))){
					user.setEmail(rs.getString("email"));
					user.setFirstName(rs.getString("firstname"));
					user.setLastName(rs.getString("lastname"));
					user.setPhonenumber(rs.getString("phonenumber"));
					user.setImage(rs.getBlob("image"));
					 return user;
				}
			}
			connection.close();
			ps.close();
			
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
	           
            try {
               if(connection!=null)
            	   connection.close();
            } catch(SQLException se) {
               se.printStackTrace();
            }
        } 
		
		return null;
	}
	
	public User getUserInfo(String email){
		Connection connection=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		User user=new User();
		
		
		try {
			 connection = getConnection();
			String query="SELECT *from User where email=?";
			ps =(PreparedStatement) connection.prepareStatement(query);
			ps.setString(1,email);
			rs=ps.executeQuery();
			
			while(rs.next()){
				
					user.setEmail(rs.getString("email"));
					user.setFirstName(rs.getString("firstname"));
					user.setLastName(rs.getString("lastname"));
					user.setPhonenumber(rs.getString("phonenumber"));
					user.setImage(rs.getBlob("image"));
					 return user;
				
			}
			connection.close();
			ps.close();
			
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
	           
            try {
               if(connection!=null)
            	   connection.close();
            } catch(SQLException se) {
               se.printStackTrace();
            }
        } 
		
		return null;
	}
	
	public String getSaltUser(String email){
		    Connection connection=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			
	        try {
	        	 connection = getConnection();
				String query="select salt from User where email=?";
				
				ps =(PreparedStatement) connection.prepareStatement(query);  

				  ps.setString(1,email); 
				  rs=ps.executeQuery();
				  while(rs.next()){
					  return rs.getString("salt");
				  }
				  connection.close();
		         ps.close();
		         
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
		           
	            try {
	               if(connection!=null)
	            	   connection.close();
	            } catch(SQLException se) {
	               se.printStackTrace();
	            }
	        }
			return null;
			
		}
	
	public String updateAddress(Address addres){
		
		String address=addres.getAddress();
		String city=addres.getCity();
		String state=addres.getState();
		String zipcode=addres.getZipcode();
		String email=addres.getEmail();
		Connection connection=null;
			PreparedStatement ps=null;
			
			
	        try {
	        	 connection = getConnection();
				String query="insert into Address(address,city,state,zipcode,email) values (?,?,?,?,?);";
				
				ps =(PreparedStatement) connection.prepareStatement(query);  

				  ps.setString(1,address);  
		          ps.setString(2,city);        
		          ps.setString(3,state);
		          ps.setString(4, zipcode);
		          ps.setString(5,email); 
		          
		         int i= ps.executeUpdate();
		         if(i!=0){
		        	 System.out.println("details inserted successfully");
		        	  return "success";
		        	  
		         }else{
		        	 System.out.println("not successfull");
		        	 
		         }
		         connection.close();
		         ps.close();
		        
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
		           
	            try {
	               if(connection!=null)
	            	   connection.close();
	            } catch(SQLException se) {
	               se.printStackTrace();
	            }
	        }
			return null;
			
		}
	
	public Address getAddress(String email){
		  Connection connection=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			
	        try {
	        	 connection = getConnection();
				String query="select * from Address where email=?";
				
				ps =(PreparedStatement) connection.prepareStatement(query);  

				  ps.setString(1,email); 
				  rs=ps.executeQuery();
				  while(rs.next()){
					  Address adrs=new Address();
					  adrs.setAddress(rs.getString("address"));
					  adrs.setCity(rs.getString("city"));
					  adrs.setState(rs.getString("state"));
					  adrs.setZipcode(rs.getString("zipcode"));
					   return adrs;
				  }
				  connection.close();
		         ps.close();
		         
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
		           
	            try {
	               if(connection!=null)
	            	   connection.close();
	            } catch(SQLException se) {
	               se.printStackTrace();
	            }
	        }
		return null;
	}
	
	public String delAddress(String email){
		
		Connection connection=null;
		PreparedStatement ps=null;
		
		
        try {
            connection = getConnection();
			String query="Delete from Address where email=?";
			
			ps =(PreparedStatement) connection.prepareStatement(query);  
			 ps.setString(1,email);  
	         int i= ps.executeUpdate();
	         if(i!=0){
	        	 System.out.println("details removed successfully");
	        	  return "success";
	        	  
	         }else{
	        	 System.out.println("not successfull");
	        	 
	         }
	         connection.close();
	         ps.close();
	         
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        finally {
           
            try {
               if(connection!=null)
            	   connection.close();
            } catch(SQLException se) {
               se.printStackTrace();
            }
        }
		return null;
		
	}
	
	public ArrayList<Product> getProduct(){
		Connection connection=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<Product> prodList=new ArrayList<Product>();
		
		try {
			 connection = getConnection();
			String query="SELECT * from Product";
			ps =(PreparedStatement) connection.prepareStatement(query);
			rs=ps.executeQuery();
			
			if(rs!=null){
				
				while(rs.next()){
					    Product prod=new Product();
						prod.setProductId(rs.getString("productId"));
						prod.setDescription(rs.getString("description"));
						prod.setProductname(rs.getString("productname"));
						prod.setProductprice(rs.getString("productprice"));
						prod.setQuantity(rs.getString("quantity"));
						prod.setProdImage(rs.getBlob("productImage"));
						prodList.add(prod);
				}
			}
			if(!prodList.isEmpty()){
				
				return prodList;
			}
			connection.close();
			 ps.close();
	         
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
           
            try {
               if(connection!=null)
            	   connection.close();
            } catch(SQLException se) {
               se.printStackTrace();
            }
        }
		  
		
		return null;
	}
	
	public String updatePicture(String email,InputStream inps){
		
		
		Connection connection=null;
			PreparedStatement ps=null;
			
			
	        try {
	        	 connection = getConnection();
				String query="Update User Set image=? WHERE email = ?";
				
				ps =(PreparedStatement) connection.prepareStatement(query);  
				
		          if (inps != null) {
		                //files are treated as BLOB objects in database
		                //here we're letting the JDBC driver
		                //create a blob object based on the
		                //input stream that contains the data of the file
		        	 
		                ps.setBlob(1, inps);
		            }
		          ps.setString(2, email);
		         int i= ps.executeUpdate();
		         if(i!=0){
		        	 System.out.println("details inserted successfully");
		        	  return "success";
		        	  
		         }else{
		        	 System.out.println("not successfull");
		        	 
		         }
		         connection.close();
		         ps.close();
		         
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
	           
	            try {
	               if(connection!=null)
	            	   connection.close();
	            } catch(SQLException se) {
	               se.printStackTrace();
	            }
	        }
			return null;
			
		}
	
	public String addProduct(Product product,InputStream inps){
		
	String productId=product.getProductId();
	String description=product.getDescription();
	String productname=product.getProductname();
	String productprice=product.getProductprice();
	String quantity=product.getQuantity();
	
	Connection connection=null;
		PreparedStatement ps=null;
		
		
        try {
        	 connection = getConnection();
			String query="INSERT INTO Product(productId,description,productname,productprice,quantity,productImage) VALUES(?,?,?,?,?,?)";
			
			ps =(PreparedStatement) connection.prepareStatement(query);  

			  ps.setString(1,productId);  
	          ps.setString(2,description);        
	          ps.setString(3,productname);
	          ps.setString(4, productprice);
	          ps.setString(5,quantity); 
	          if (inps != null) {
	                //files are treated as BLOB objects in database
	                //here we're letting the JDBC driver
	                //create a blob object based on the
	                //input stream that contains the data of the file
	                ps.setBlob(6, inps);
	            }
	          
	         int i= ps.executeUpdate();
	         if(i!=0){
	        	 System.out.println("details inserted successfully");
	        	  return "success";
	        	  
	         }else{
	        	 System.out.println("not successfull");
	        	 
	         }
	         connection.close();
	         ps.close();
	         
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
           
            try {
               if(connection!=null)
            	   connection.close();
            } catch(SQLException se) {
               se.printStackTrace();
            }
        }
		return null;
		
	}
	
	public String editProduct(Product product){
	
	String productId=product.getProductId();
	String description=product.getDescription();
	String productname=product.getProductname();
	String productprice=product.getProductprice();
	String quantity=product.getQuantity();
	Connection connection=null;
		PreparedStatement ps=null;
		
		
        try {
        	 connection = getConnection();
			String query="UPDATE Product "
					+ "Set description=?"
					+ ",productname=?"
					+ ",productprice=?"
					+ ",quantity=? "
					+ " Where productId="+productId;
			
			ps =(PreparedStatement) connection.prepareStatement(query);  

			  ps.setString(1,description);  
	          ps.setString(2,productname);        
	          ps.setString(3,productprice);
	          ps.setString(4, quantity);
	          
	          
	         int i= ps.executeUpdate();
	         if(i!=0){
	        	 System.out.println("details updated successfully");
	        	  return "success";
	        	  
	         }else{
	        	 System.out.println("not successfull");
	        	 
	         }
	         connection.close();
	         ps.close();
	        
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
           
            try {
               if(connection!=null)
            	   connection.close();
            } catch(SQLException se) {
               se.printStackTrace();
            }
        }
		return null;
		
	}
	
	public String removeProduct(String productId){
	
	
	    Connection connection=null;
		PreparedStatement ps=null;
		
		
        try {
            connection = getConnection();
			String query="Delete from Product where productId="+productId;
			
			ps =(PreparedStatement) connection.prepareStatement(query);    
	         int i= ps.executeUpdate();
	         if(i!=0){
	        	 System.out.println("details removed successfully");
	        	  return "success";
	        	  
	         }else{
	        	 System.out.println("not successfull");
	        	 
	         }
	         connection.close();
	         ps.close();
	         
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        finally {
           
            try {
               if(connection!=null)
            	   connection.close();
            } catch(SQLException se) {
               se.printStackTrace();
            }
        }
		return null;
		
	}

	public static Connection getConnection() throws ClassNotFoundException,SQLException 
	{
		 Class.forName("com.mysql.jdbc.Driver");
		 String dbName = "muscle";
		 String userName = "root";
		 String password = "Telugu@1";
		 String hostname = "localhost";
		 String port = "3306";
		 String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName +
				 "?user=" + userName + "&password=" + password;
				  return DriverManager.getConnection(jdbcUrl);
			 
	}
	

}
