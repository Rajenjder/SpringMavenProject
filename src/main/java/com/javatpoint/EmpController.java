package com.javatpoint;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.beans.Emp;
import com.util.JsonUtil;


@Controller
public class EmpController {
	
	@RequestMapping("/empform")  
    public ModelAndView showform(){  
         //command is a reserved request attrempformibute name, now use <form> tag to show object data  
        return new ModelAndView("empform","command",new Emp());  
    }  
	
	/*@RequestMapping(value="/save",method = RequestMethod.POST)  
    public ModelAndView save(@ModelAttribute("emp") Emp emp){  
        //write code to save emp object  
        //here, we are displaying emp object to prove emp has data  
        System.out.println(emp.getName()+" "+emp.getSalary()+" "+emp.getDesignation());  
        List<Emp> list=new ArrayList<Emp>();  
              
        //return new ModelAndView("empform","command",emp);//will display object data  
        return new ModelAndView("redirect:/viewemp");//will redirect to viewemp request mapping  
    }  */
	@RequestMapping(value="/save",method = RequestMethod.POST)
    public @ResponseBody String save(@RequestBody Emp empDetails)
    {
        boolean nameExist = false;       
        String jsonEmpList= "";
        try{
    		System.out.println("--------************------------->>");
    		System.out.println("--------************------------->>"+empDetails.getId());
    		System.out.println("--------************------------->>"+empDetails.getName());
	        Class.forName("com.mysql.jdbc.Driver");
	        String url="jdbc:mysql://localhost:3306/test";
	        String un="root";
	        String pwd="rajender@123";
	        Connection con=
	        DriverManager.getConnection(url,un,pwd);
	        String sql="insert into empdetails(EmpId,Name,Salary,Designation) values (?,?,?,?) ";
	        PreparedStatement pst=con.prepareStatement(sql);
	        pst.setInt(1, empDetails.getId());
	        pst.setString(2, empDetails.getName());
	        pst.setString(3, empDetails.getSalary());
	        pst.setString(4, empDetails.getDesignation());
	        
	        int result = pst.executeUpdate();
	        
	        /*if(result>0){
	        	System.out.println("***************** Insertion success ***********");
	        	
	        	List<Emp> list=new ArrayList<Emp>();
	        	
	        	try{
	        		System.out.println("--------************------------->>");
	            Class.forName("com.mysql.jdbc.Driver");
	            String url1="jdbc:mysql://localhost:3306/test";
	            String un1="root";
	            String pwd1="rajender@123";
	            Connection con1=
	            DriverManager.getConnection(url1,un1,pwd1);
	            String sql1="select EmpId,Name,Salary,Designation from empdetails ";
	            PreparedStatement pst1=con.prepareStatement(sql1);
	            
	            ResultSet rs=pst1.executeQuery();
	            while(rs.next()){
	            	Emp emp=new Emp();
	            	System.out.println("EmpId-->>"+rs.getInt("EmpId"));
	            	emp.setId(rs.getInt("EmpId"));
	            	emp.setName(rs.getString("Name"));
	            	emp.setSalary(rs.getString("Salary"));
	            	emp.setDesignation(rs.getString("Designation"));
	            	list.add(emp);
	            	
	            	
	            
	            }
	            System.out.println("list.size-->>"+list.size());
	            
	            jsonEmpList = JsonUtil.convertJavaToJson(list);
	            
	            System.out.println("jsonEmpList-->>"+jsonEmpList);
	            if(pst1!=null){
	            	pst1.close();
	            }
	            if(rs!=null){
	            	rs.close();
	            }
	            if(con1!=null){
	            	con1.close();
	            }
	            
	        	}catch(Exception e){
	        		e.printStackTrace();
	        	}
	        	
	        	
	        }else{
	        	System.out.println("***************** Error while insertion ***********");
	        }*/
	        if(pst!=null){
            	pst.close();
            }
	        if(con!=null){
	        	con.close();
            }
        
        }catch(Exception e){e.printStackTrace();}
        
        /*for(UserDetails ud : empDetails)
        {
            if(ud.getName().equals(userDetails.getName()))
            {
                nameExist = true;
                ud.setDepartment(userDetails.getDepartment());
                break;
            }
        }*/
        /*if(!nameExist)
        {
            userDetailsList.add(userDetails);
        }*/
        
        //return new ResponseEntity(HttpStatus.OK);
        
        //HttpHeaders responseHeaders = new HttpHeaders();
        //responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        //return new ResponseEntity<String>(jsonEmpList, responseHeaders, HttpStatus.UNAUTHORIZED);
        //return ResponseEntity.ok(jsonEmpList);
        //return jsonEmpList;
        jsonEmpList = viewemp();
        return jsonEmpList;
    }
    @RequestMapping(value="/viewemp",method=RequestMethod.GET,produces="application/json")  
    public @ResponseBody String viewemp(){  
        //write the code to get all employees from DAO  
        //here, we are writing manual code of list for easy understanding  
    	List<Emp> list=new ArrayList<Emp>();
    	String jsonEmpList= "";
    	try{
    		System.out.println("--------************------------->>");
        Class.forName("com.mysql.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/test?useSSL=false";
        String un="root";
        String pwd="rajender@123";
        Connection con=
        DriverManager.getConnection(url,un,pwd);
        String sql="select EmpId,Name,Salary,Designation from empdetails ";
        PreparedStatement pst=con.prepareStatement(sql);
        
        ResultSet rs=pst.executeQuery();
        while(rs.next()){
        	Emp emp=new Emp();
        	System.out.println("EmpId-->>"+rs.getInt("EmpId"));
        	emp.setId(rs.getInt("EmpId"));
        	emp.setName(rs.getString("Name"));
        	emp.setSalary(rs.getString("Salary"));
        	emp.setDesignation(rs.getString("Designation"));
        	list.add(emp);
        	
        	
        
        }
        System.out.println("list.size-->>"+list.size());
        
        jsonEmpList = JsonUtil.convertJavaToJson(list);
        
        System.out.println("jsonEmpList-->>"+jsonEmpList);
        
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	//return new ModelAndView("viewemp","list",jsonEmpList);
    	return jsonEmpList;
    }  

}
