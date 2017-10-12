package com.javatpoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	@RequestMapping("/hello")
	public ModelAndView mymethod(HttpServletRequest request,HttpServletResponse response){
		String name=request.getParameter("name");  
        String password=request.getParameter("password");
        String message = "HELLO "+name;
       /* if(password.equals("admin")){  
            String message = "HELLO "+name;  
            return new ModelAndView("emp", "msg", message);  
            }  
            else{  
                return new ModelAndView("errorpage", "msg","Sorry, username or password error");  
            } */ 
		//return new ModelAndView("hellopage","msg","Hello First Spring");
        
        return new ModelAndView("emp", "msg", message);  
	}
}
