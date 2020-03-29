package com.example.demo.controller;

import java.io.FileOutputStream;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.GoodsDao;
import com.example.demo.vo.GoodsVo;
import com.google.gson.Gson;

@RestController
public class GoodsController {

	@Autowired
	private GoodsDao dao;

	public GoodsDao getDao() {
		return dao;
	}
	
	@RequestMapping("/insertGoods")
	public String insertGoods(GoodsVo g, HttpServletRequest request) {
		
		String ip = request.getRemoteAddr();
		
		 String id = request.getParameter("id");
		 if(id == null || "".equals(id)) id = "";
		  
		 HashMap<String,Object> map = new HashMap<String,Object>(); map.put("id", id);
		
		 String str = "";
		
		 String path = "C:\\spring boot\\bitshop\\src\\main\\resources\\static\\img";
		 MultipartFile uploadFile = g.getUploadFile();
		 String fname = "";
		 if(uploadFile != null) {
			fname = uploadFile.getOriginalFilename();
			try {
				byte []data = uploadFile.getBytes();
				FileOutputStream fos = new FileOutputStream(path +"\\"+fname);
				fos.write(data);
				fos.close();
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		 }
		 g.setFname(fname);
		 dao.insertGoods(g);
		 return str;
	}
	
	@RequestMapping(value = "/listGoods", produces = "application/json;cahrset=UTF-8")
	public String listGoods() {
		String str = "";
		Gson gson = new Gson();
		str = gson.toJson(dao.listGoods());
		return str;
	}
}
