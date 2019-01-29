package org.ltq.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.ltq.entity.Comment;
import org.ltq.entity.User;
import org.ltq.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/commentController")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	/**
	 * 请求的form表单的enctype="multipart/form-data"，request会自动转换成multipartRequest，所有参数都不为null，且String类型的值不为null和""
	 * @param content，评论内容
	 * @param comPic 评论图片(数字，表示图片数)
	 * @param session HttpSession
	 * @return
	 */
	@RequestMapping("/addCommentControl")
	public String addCommentControl(@RequestParam(value="content") String content,@RequestParam(value="comPic",required=false) MultipartFile comPic,HttpSession session,Map<String,Object> map) {
			//判断，图片和文字都没有时返回失败
			if(content.trim().length()==0 && comPic.getOriginalFilename().trim().length()==0) {
				map.put("resultType", 6);
				return "requestResult";
			}
			
			InputStream in = null;
			OutputStream out = null;
			//存储信息
			Date time =new Date(System.currentTimeMillis());
			User user = (User)session.getAttribute("user");
			String uname = user.getName();
			String account = user.getAccount();
			//当没传文件时
			if(comPic.getOriginalFilename().trim().length()==0) {
				Comment comment = new Comment(account, uname, time, content);
				commentService.addComment(comment);
				map.put("resultType", 5);
				return "requestResult";
			//当传文件时
			}else{
				//文件名以及文件的后缀
				String originalFilename = comPic.getOriginalFilename();
				String fileSubString = originalFilename.substring(originalFilename.lastIndexOf("."));
				
				//判断文件名是否为.jpg.jpeg.png.gif结尾（为图片文件）
				if(fileSubString.equals(".jpg")||fileSubString.equals(".jpeg")||fileSubString.equals(".png")) {
					Comment comment = new Comment(account, uname, time, content,1);
					commentService.addComment(comment);
					
					//存储在服务器的目录
					String dirPath = "D:\\MyForumUploadDir\\comment_picture";
					File file = new File(dirPath);
					
					//判断是否存在dirPath目录，没有则创建目录
					if(!file.exists()) {
						file.mkdirs();
					}
					
					//临时查询对象
					Comment tempCom = new Comment(account,time);	
					BigInteger commentId = commentService.queryCommentIdByAccountAndTime(tempCom);
					
					//文件写出时修改文件名为[commentId.jpg]
					File outputFile = new File(dirPath+"\\"+commentId+".jpg");
					try {
						in = new BufferedInputStream(comPic.getInputStream());
						out = new BufferedOutputStream(new FileOutputStream(outputFile));
						int len = -1;
						byte flush[] = new byte[1024];
						while((len=in.read(flush))!=-1) {
							out.write(flush, 0, len);
						}
						out.flush();
						map.put("resultType", 5);
						return "requestResult";
					} catch (FileNotFoundException e) {
						e.printStackTrace();
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
		map.put("resultType", 6);
		return "requestResult";
	}
	
	@RequestMapping("/queryAllCommentsControl")
	public String queryAllCommentsControl(HttpSession session,Map<String,Object> map) {
		List<Comment> comments = commentService.queryAllComments();
		map.put("comments", comments);
		return "comment";
	}
	
	@RequestMapping("/deleteCommentControl")
	public String deleteCommentControl(@RequestParam("commentid")BigInteger commentId,Map<String,Object> map) {
		commentService.deleteCommentByCommentId(commentId);
		map.put("resultType", 7);
		return "requestResult";
	}
}
