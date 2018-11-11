package com.mygdx.game;
import java.sql.*;
public class ReadVocabs {
		private static Connection theConn;
		private static ResultSet resultSet;
		private static PreparedStatement ps;
		private static String sql, word, meaning;
		private static int id;
        private static int[] random;
        private static Vocab[] vocab;
		public static boolean isConnect() {
			vocab = new Vocab[20];
			random = new int[20];
			try {
			DBConnection MyCon = new DBConnection();
			theConn = MyCon.getConnection();
			sql = "SELECT * FROM animals";
			//Try to make your ResultSet scrollable:
			ps = theConn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			resultSet = ps.executeQuery();
			return true;
			}catch (Exception e) {
				System.out.println("e");
				return false;
				
			}
		}
        public static Vocab[] getData(){
                 if(isConnect()) {
	             	 try {
						resultSet.last();
						id= resultSet.getInt(1);
						//random 0-last id-row;
						randomRow(id);         
						for(int i=0;i< 20;i++){   
	                    	  // random row from vocab table
							resultSet.absolute(random[i]); 
							id= resultSet.getInt(1);
							word = resultSet.getString(2);
							meaning = resultSet.getString(3);
							     System.out.println("id: "+id +" "+word+" "+ meaning);
							vocab[i] = new Vocab(id, word, meaning);
		
	                 }
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        
	     			try {
	     				ps.close();
						resultSet.close();	
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                 return vocab;
                 }
                 else {
                	 System.out.println("can not get Data");
                	 return null;
                 }
           } 
         
         public static void randomRow(int lastNum){
             int n, index = 0;
             boolean state = false;
             do{
                  // lastNum is number of words
                   n = (int) (Math.random()*lastNum)+1;
                   
                   for(int i=0; i<random.length;i++){
                        if(random[i] == n){
                             state = true;
                             break;
                        }
                   }
                   if(state){
                        state = false;
                   }else{
                        random[index] = n;
                        System.out.println(n);
                        index++;
                   }
             }while(index <= 19);
         }
	public static void main(String[] args) {

       
       for(Vocab vocab: ReadVocabs.getData()) {
    	   System.out.println(vocab);
       }
 
	}

}
