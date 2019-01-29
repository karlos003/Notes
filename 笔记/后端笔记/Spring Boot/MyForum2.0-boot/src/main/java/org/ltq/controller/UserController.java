package org.ltq.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ltq.entity.User;
import org.ltq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@RequestMapping("/userController")
@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/signUpControl")
	public String signUpControl(User user,HttpSession session,HttpServletResponse response,Map<String,Object> map) {
		if(user.getName().trim()==null||user.getName()==""||user.getAccount().trim()==null||user.getAccount()==""||user.getPwd().trim()==null||user.getPwd()=="") {
			map.put("resultType", 4);
			return "requestResult";
		}
		boolean result = userService.addUser(user);
		if(result) {
			session.setAttribute("user", user);
			Cookie accountCookie = new Cookie("account",user.getAccount());
			Cookie pwdCookie = new Cookie("pwd",user.getPwd());
			accountCookie.setMaxAge(604800);
			accountCookie.setPath("/");
			pwdCookie.setMaxAge(604800);
			pwdCookie.setPath("/");
			response.addCookie(pwdCookie);
			response.addCookie(accountCookie);
			map.put("resultType", 2);
			return "requestResult";
		}else {
			map.put("resultType", 3);
			return "requestResult";
		}
	}
	
	@RequestMapping(value="/loginControl")
	public String loginControl(@RequestParam("account")String account,@RequestParam("upwd")String pwd,HttpSession session,HttpServletResponse response,Map<String,Object> map) {
		User user = userService.queryUserByAccount(account);
		if(user!=null) {
			if(user.getPwd().equals(pwd)) {
				session.setAttribute("user", user);
				Cookie accountCookie = new Cookie("account",account);
				Cookie pwdCookie = new Cookie("pwd",pwd);
				accountCookie.setMaxAge(604800);
				accountCookie.setPath("/");
				pwdCookie.setMaxAge(604800);
				pwdCookie.setPath("/");
				response.addCookie(accountCookie);
				response.addCookie(pwdCookie);
				map.put("resultType", 1);
				return "requestResult";
			}
			map.put("resultType", 10);
			return "requestResult";
		}
		map.put("resultType", 10);
		return "requestResult";
	}
	
	@RequestMapping("/logOffControl")
	public String logOffControl(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/homePage.jsp";
	}
	
	@RequestMapping("/updateUserPhotoControl")
	public String updateUserPhotoControl(@RequestParam(value="user_photo")MultipartFile img  ,HttpSession session,Map<String,Object> map) {
		try {
			//获得图片全名、类型
			String originalFilename = img.getOriginalFilename();
			//判断图片是否为空
			if(originalFilename.trim()==null||originalFilename.trim()=="") {
				map.put("resultType", 16);
				return "requestResult";				
			}
			String fileSubString = originalFilename.substring(originalFilename.lastIndexOf("."));
			
			//判断图片类型为jpg jpeg png
			if(fileSubString.equals(".jpg")||fileSubString.equals(".jpeg")||fileSubString.equals(".png")) {
				
				//获取当前用户
				User user =(User)session.getAttribute("user");
				BufferedInputStream in = new BufferedInputStream(img.getInputStream());
				
				//存储在服务器中的目录
				String dirPath = "D:\\photo";
				File file = new File(dirPath);
				
				//判断有无目录，无则创建
				if(!file.exists()) {
					file.mkdirs();
				}
				
				//IO流操作
				//将输出文件的文件名修改为[account.jpg]
				File imgOut = new File(dirPath+"\\"+user.getAccount()+".jpg");
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(imgOut));
				int len = -1;
				byte[] flush = new byte[1024];
				while((len=in.read(flush))!=-1) {
					out.write(flush, 0, len);
				}
				out.flush();
				//关闭流
				in.close();
				out.close();
				User tempUser = new User(user.getAccount(),1);
				userService.updateUserPhotoByAccount(tempUser);
				user.setUser_photo(1);
				
				//更新session中的user.photo信息
				session.setAttribute("user", user);
				map.put("resultType", 11);
				return "requestResult";
			}else {
				map.put("resultType", 12);
				return "requestResult";
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/userInfo.jsp";
	}
	
	@RequestMapping(value="/changePasswordControl")
	public String changePasswordControl(@RequestParam("pwd")String newPwd,@RequestParam("currentPwd")String inputPwd ,HttpSession session,Map<String,Object> map) {
		if(newPwd.trim().length()==0||inputPwd.trim().length()==0) {
			map.put("resultType", 17);
			return "requestResult";
		}
		User user = (User)session.getAttribute("user");
		String systemPwd = userService.queryUserPwdByAccount(user.getAccount());
		if(!systemPwd.equals(inputPwd)) {
			map.put("resultType", 13);
			return "requestResult";
		}else{
			User tempUser = new User(user.getAccount(),newPwd);
			userService.updateUserPwdByAccount(tempUser);
			user.setPwd(newPwd);
			session.setAttribute("user", user);
			map.put("resultType", 14);
			return "requestResult";
		}
		
	}
}
