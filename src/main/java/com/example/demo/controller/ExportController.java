/**  
* <p>Title: ExportController.java</p>  
* <p>Description: </p>  
* @author koko 
* @date 2018年11月2日  
* @version 1.0  
*/ 
package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.ExportBean;
import com.example.demo.bean.User;
import com.example.demo.service.IExportService;

/**  
* <p>Title: ExportController</p>  
* <p>Description: </p>  
* @author koko  
* @date 2018年11月2日  
*/
@RestController
@RequestMapping("/excel")
public class ExportController {

	@Autowired
	private IExportService exportService;
	
	@PostMapping("/exportData")
	public void exportData(HttpServletResponse response) {
		ExportBean bean=new ExportBean();
		String[] field= {"id","name","age","sex"};
		bean.setField(field);
		String[] head= {"id","姓名","年龄","性别"};
		bean.setHead(head);
		bean.setFileName("用户信息表");
		List<User> list=new ArrayList<>();
		User user1=new User();
		user1.setId(1);
		user1.setAge("12");
		user1.setName("zhangsan");
		user1.setSex("男");
		list.add(user1);
		
		User user2=new User();
		user2.setId(2);
		user2.setAge("16");
		user2.setName("李四");
		user2.setSex("女");
		list.add(user2);
		exportService.exportData(list, bean, response);
		
	}
}
