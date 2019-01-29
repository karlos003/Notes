package org.ltq.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ltq.entity.Post;
import org.ltq.entity.User;
import org.ltq.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/postController")
public class PostController {
	
	@Autowired
	PostService postService;
	
	@RequestMapping("/addPostControl")
	public String addPostControl(@RequestParam("post_title")String post_title,@RequestParam("post_content")String post_content,@RequestParam("post_image")List<MultipartFile> fileList,HttpServletRequest request,HttpSession session,Map<String,Object> map) {
		//判断标题内容是否为空，为空返回失败
		if(post_title.trim()==null||post_title.trim()=="") {
			map.put("resultType", 15);
			return "requestResult";
		}
		int post_image = 0;
		//计算图片数量，用于后面存储，并判断文件类型，类型错误返回错误页面
		for(MultipartFile temp:fileList) {
			if(temp.getOriginalFilename().trim().length()>0) {
				String originalFilename = temp.getOriginalFilename();
				String fileSubString = originalFilename.substring(originalFilename.lastIndexOf("."));
				if(originalFilename.trim().length()>0) {
					post_image++;
				}
				if(!(fileSubString.equals(".jpg")||fileSubString.equals(".jpeg")||fileSubString.equals(".png"))) {
					map.put("resultType", 8);
					return "requestResult";
				}
			}
		}
		//获取Post信息以及用户信息，用于存储入数据库
		String post_type = request.getParameter("post_type");
		User user = (User) session.getAttribute("user");
		String user_account = user.getAccount();
		String user_name = user.getName();
		Date post_time = new Date(System.currentTimeMillis());
		Post post = new Post(post_type, post_title, post_content, user_account, user_name, post_time, post_image);
		postService.addPost(post);
		
		//当有文件时
		if(post_image>0) {
			//存储在服务器的目录
			String dirPath = "D:\\MyForumUploadDir\\post_picture";
			File file = new File(dirPath);
			//判断是否存在dirPath目录，没有则创建目录
			if(!file.exists()) {
				file.mkdirs();
			}
			//查询postId的临时Post对象
			Post tempPost = new Post(user_account,post_time);
			BigInteger resultPostId = postService.selectPostIdByAccountAndTime(tempPost);
			int i = 1;
			
			for(MultipartFile temp:fileList) {
				InputStream in = null;
				OutputStream out = null;
				try {
					in = new BufferedInputStream(temp.getInputStream());
					out = new BufferedOutputStream(new FileOutputStream(new File(dirPath+"\\"+resultPostId+"_"+i+".jpg")));
					int len = -1;
					byte[] flush = new byte[1024];
					while((len=in.read(flush))!=-1) {
						out.write(flush, 0, len);
					}
					out.flush();
					i++;
				} catch (IOException e) {
					e.printStackTrace();
				}finally {
					try {
						if(in!=null) {
							in.close();
						}
						if(out!=null) {
							out.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		map.put("resultType", 9);
		return "requestResult";
	}
	
	@RequestMapping("/queryAllJavaPostsControl")
	public String queryAllJavaPostsControl(Map<String,Object> map) {
		List<Post> posts = postService.queryPostInfoByTypeOrdByTime("java");
		map.put("posts", posts);
		return "javaForum";
	}
	
	
}
