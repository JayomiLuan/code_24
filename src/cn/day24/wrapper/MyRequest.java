package cn.day24.wrapper;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyRequest extends HttpServletRequestWrapper {
	private HttpServletRequest request = null;
	
	public MyRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	/**
	 * 重写父类的getParameter方法，对原有Request对象的getParameter方法进行增强
	 * 使用父类的原始方法得到原始字符串（此时得到的是乱码的字符串）
	 * 对此乱码字符串进行重新编码，并返回
	 */
	@Override
	public String getParameter(String name){
		//得到乱码字符串
		String temp = super.getParameter(name);

		//判断当前请求的方式，如果是GET，则进行编码，如果是POST方式，
		if( "GET".equals( request.getMethod() ) ){
			try {
				//对字符串进行重新编码，解决乱码问题
				temp = new String( temp.getBytes("ISO-8859-1") , "UTF-8" );
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		return temp;
	}

}
