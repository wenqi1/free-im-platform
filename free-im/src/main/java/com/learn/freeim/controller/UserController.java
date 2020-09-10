package com.learn.freeim.controller;


import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.learn.freeim.entity.vo.UserLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.learn.freeim.entity.Result;
import com.learn.freeim.entity.SysUser;
import com.learn.freeim.entity.VerifyImageCode;
import com.learn.freeim.service.UserService;
import com.learn.freeim.util.VerifyCodeUtil;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    
    @RequestMapping("register")
    public Result<String> register(@Validated SysUser sysUser) {
		Date now = new Date();
    	sysUser.setCreateDate(now);
    	sysUser.setUpdateDate(now);
    	userService.insert(sysUser);
    	return Result.success();
    }
    
    /*@RequestMapping("login")
    @ResponseBody
    public Result<String> login(UserLogin user, HttpServletRequest request) {
    	String checkResult = userService.checkLogin(user);
    	if(checkResult != null && !checkResult.isEmpty()){
    		return Result.fail("100", checkResult);
    	}
    	HttpSession session = request.getSession();
    	String verifyCode = (String) session.getAttribute("verifyCode");
    	session.removeAttribute("verifyCode");
    	if(verifyCode == null || !verifyCode.equals(user.getVerifyCode())){
    		return Result.fail("100", "验证码错误");
    	}
    	List<SysUser> allUser = userService.selectAll();
    	if(allUser != null){
    		for(SysUser sysUser : allUser){
    			if(sysUser.getUserName().equals(user.getUsername())
    					&& sysUser.getPassword().equals(user.getPassword())){
    				return Result.success("登录成功");
    			}
    		}
    	}
    	return Result.fail("100", "用户名或密码错误");
    }*/
    
    
    
    /*
	 * 获取验证码
	 */
	@RequestMapping(value = "/verifyCode")
	public void getVerifyCode(HttpServletResponse response, HttpServletRequest request) {
		ServletOutputStream outputStream = null;
		try {
			VerifyImageCode imageCode = VerifyCodeUtil.createVerifyImageCode();

			// 将验证码存到session
			HttpSession session = request.getSession();
			session.setAttribute("verifyCode", imageCode.getVerifyCode());
			LOGGER.info("后台生成的用户登录验证码：{}", imageCode.getVerifyCode());
			// 禁止图像缓存
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/jpeg");
			// 将图像输出到Servlet输出流中。
			outputStream = response.getOutputStream();
			ImageIO.write(imageCode.getImage(), "jpeg", outputStream);

		} catch (Exception e) {
			LOGGER.error("获取验证码出错", e);
		} finally {
			try {
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (Exception e) {
				LOGGER.error("关闭流出错", e);
			}
		}

	}
    
    
}
