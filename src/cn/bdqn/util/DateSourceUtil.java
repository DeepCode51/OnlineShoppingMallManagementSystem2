package cn.bdqn.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DateSourceUtil {
	//驱动地址
	private static String driver;
	//要访问的数据库地址
	private static String url;
	//账户
	private static String root;
	//密码
	private static String pwd;
	private static Properties params;
	public static void init(){
		//创建一个Properties对象
		params=new Properties();
		//声明要读取的配置文件的名称
		String configFile="database.properties";
		//用输入流 吧配置文件读取进来
		InputStream is=DateSourceUtil.class.getClassLoader().getResourceAsStream(configFile);
		try {
			//把输入流里面的文件加载进来
			params.load(is);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		driver=params.getProperty("jdbc.driver_class");
		url=params.getProperty("jdbc.connection.url");
		root=params.getProperty("jdbc.connection.username");
		pwd=params.getProperty("jdbc.connection.password");	
	}
	//创建数据库链接
	public static Connection openConnection() throws SQLException{
		Connection connection=null;
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//创建链接
			connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/graduation_easybuy","root","123456");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return connection;
	}
	//关闭数据库的链接
	public static void closeConnection(Connection connection){
		if(null!=connection){
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
