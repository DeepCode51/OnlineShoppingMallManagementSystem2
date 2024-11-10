package cn.bdqn.util;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

public class PrintUtil {

	private static void print(String msg,HttpServletResponse response){
        PrintWriter writer=null;
		try {
            if(null != response){
                writer=response.getWriter();
                writer.print(msg);
                writer.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
            writer.close();
        }
    }
	public static void write(Object obj,HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		String json = JSONObject.toJSONString(obj);
		print(json,response);
	}
}
