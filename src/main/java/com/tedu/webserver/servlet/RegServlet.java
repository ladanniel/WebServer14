package com.tedu.webserver.servlet;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Arrays;

import com.tedu.webserver.http.HttpRequest;
import com.tedu.webserver.http.HttpResponse;

/**
 * 处理注册业务
 * @author adminitartor
 *
 */
public class RegServlet extends HttpServlet{
	public void service(HttpRequest request,HttpResponse response){
		try {
			System.out.println("开始处理注册业务！！");
			/*
			 * 注册业务流程
			 * 1:通过request获取用户在注册页面上输入的
			 *   注册信息
			 * 2:使用RandomAccessFile打开user.dat文件
			 * 3:将该用户信息写入文件
			 * 4:设置response，响应注册成功页面。 
			 */
			//1
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String nickname = request.getParameter("nickname");
			int age = Integer.parseInt(request.getParameter("age"));
			//还要对用户数据进行必要验证
			
			//2
			try(
				RandomAccessFile raf 
					= new RandomAccessFile("user.dat","rw");
			){
				raf.seek(raf.length());
				//写用户名
				byte[] data = username.getBytes("UTF-8");
				data = Arrays.copyOf(data, 32);
				raf.write(data);
				
				//写密码
				data = password.getBytes("UTF-8");
				data = Arrays.copyOf(data, 32);
				raf.write(data);
				
				//写昵称
				data = nickname.getBytes("UTF-8");
				data = Arrays.copyOf(data, 32);
				raf.write(data);
				
				//写年龄
				raf.writeInt(age);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//3响应客户端注册成功页面
			forward("/myweb/reg_success.html", request, response);		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}







