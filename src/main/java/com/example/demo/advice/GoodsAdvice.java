package com.example.demo.advice;
import java.io.FileWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class GoodsAdvice {

	@Pointcut("execution(public * com.example.demo.controller..*(..))")
	public void gsmethod() {}
	
	@Before("gsmethod()")
	public void before(JoinPoint joinPoint) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		/*
		 * 사용자의 모든요청을 수행하기 전에
		 * before어드바이스를 적용하여
		 * 요청명, 요청날짜, ip주소를 파일로 기록하도록 합니다.
		 */
		
		String methodName = joinPoint.getSignature().toShortString();
		
		Date today = new Date();
		int yaer = today.getYear()+1900;	//년	
		int mon = today.getMonth()+1;		//월
		int day = today.getDate();			//일
		int hour = today.getHours();
		int min = today.getMinutes();
		int sec = today.getSeconds();
		
		String ipaddr = request.getRemoteAddr();
		//System.out.println("사용자 ip: "+ipaddr);
		
		String str = "";
		str += "요청명: "+methodName +"\r\n";
		str += "요청날짜: "+yaer+"년,"+mon+"월,"+day+"일,"+hour+"시,"+min+"분,"+sec+"초"+"\r\n";
		str += "요청IP:"+ipaddr+"\r\n";
		str += "======================================"+"\r\n";
		
		try{
			FileWriter fw = new FileWriter("C:\\Users\\haji\\Desktop\\상품사진\\record.txt",true);			
			fw.write(str);			
			fw.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
}
