package com.mygdx.game;
import java.io.File;
import java.sql.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class DBConnection {
	private static String DBpath;
	public Connection getConnection() {
		Connection c = null;
		try {
			FileHandle fileHandle = Gdx.files.internal("vocab/Vocab.accdb");
			File file = fileHandle.file();
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
//			String path = DBpath+"\\core\\assets\\vocab\\Vocab.accdb";
			String path = file.getPath();
			String url = "jdbc:ucanaccess://"+path;
			c = DriverManager.getConnection(url);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	public static void setDBPath(String path) {
		DBpath = path;
	}
}
