package com.example.spring01.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring01.model.PointDTO;
import com.example.spring01.model.ProductDTO;

@Controller
public class MainController {
    @RequestMapping("/")
    public String main(Model model) {
    	model.addAttribute("message", "welcome");
    	return "main";
    	
    }
    @RequestMapping("multi_table.do")
    public String gugu() {
    	return "test/multi_table";
    }
    @RequestMapping("table_result.do")
    public String gugu_result(@RequestParam(defaultValue = "3") int num, Model model) {
    	String result = "";
    	for (int i=1; i<=9; i++) {
    		result += num + "x" + i + "=" + num*i+ "<br>";
    	}
    	model.addAttribute("result", result);
    	return "test/table_result";
    }
    
    @RequestMapping("point.do")
    public String point() {
    	return "test/point";
    }
       
    
     @RequestMapping("point_result.do")
    public String point_result(@ModelAttribute PointDTO dto, Model model) {
    	dto.setTotal(dto.getKor()+dto.getEng()+dto.getMat());
    	String avg=String.format("%.2f", dto.getTotal()/3.0);
    	dto.setAverage(Double.parseDouble(avg));
    	model.addAttribute("dto", dto);
    	return "test/point_result";
    }
    @RequestMapping("move.do")
    public String move() throws Exception {
    	String name=URLEncoder.encode("나코딩 입니다", "utf-8");
    	return "redirect:/result.do?name="+name+"&age=20";
    	
    }
    @RequestMapping("result.do")
    public String result(Model model,
    		@RequestParam(defaultValue="noname") String name,
    		@RequestParam(defaultValue="10") int age) throws Exception {
    	name=URLDecoder.decode(name, "utf-8");
    	model.addAttribute("name", name);
    	model.addAttribute("age", age);
    	return "test/result";
    }
    @RequestMapping("mav.do")
    public ModelAndView mav() {
    	Map<String,Object> map=new HashMap<>();
    	map.put("dto", new ProductDTO("pen",1000));
    	return new ModelAndView("test/mav_result", "map", map);
    	
    }
    
    
    @RequestMapping("ajax.do")
    public String ajax() {
    	return "test/ajax";
    }
    @RequestMapping("background.do")
    public @ResponseBody ProductDTO background() {
    	ProductDTO dto=new ProductDTO("TV", 500000);
    	return dto;
    }
    
    
    @RequestMapping("login.do")
    public String login() {
    	return "test/login";
    }
    @RequestMapping("login_result.do")
    public String login_result(String id, String pw, Model model) {
    	String result="";
    	if(id.equals("kim")&&pw.equals("1234")) {
    		result="환영합니다";
      	}else {
      		result="아이디 또는 비밀번호가 틀렸습니다.";
      	}
    	model.addAttribute("result", result);
    	return "test/login_result";
    }
}
