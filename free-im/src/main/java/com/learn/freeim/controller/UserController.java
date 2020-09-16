package com.learn.freeim.controller;

import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.freeim.entity.Result;
import com.learn.freeim.entity.SysUser;
import com.learn.freeim.entity.VerifyImageCode;
import com.learn.freeim.exception.CommonException;
import com.learn.freeim.service.UserService;
import com.learn.freeim.util.SnowflakeIdGenerator;
import com.learn.freeim.util.VerifyCodeUtil;

@RestController
@RequestMapping("user")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private SnowflakeIdGenerator idGenerator;

	@RequestMapping("register")
	public Result<String> register(@Validated SysUser sysUser) {
		userService.usernameHasExist(sysUser);
		Date now = new Date();
		sysUser.setCreateDate(now);
		sysUser.setUpdateDate(now);
		sysUser.setUserId(idGenerator.nextId());
		userService.insert(sysUser);
		return Result.success();
	}

	@RequestMapping("login")
	public Result<String> login(SysUser user, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String verifyCode = (String) session.getAttribute("verifyCode");
		session.removeAttribute("verifyCode");
		if (verifyCode == null || !verifyCode.equals(user.getVerifyCode())) {
			throw new CommonException("1004", LOGGER);
		}
		SysUser sourceUser = userService.queryUserByUsername(user);
		if (sourceUser == null) {
			throw new CommonException("1005", LOGGER);
		}
		if (sourceUser.getUserName().equals(user.getUserName())
		        && sourceUser.getPassword().equals(user.getPassword())) {
			return Result.success();
		}
		throw new CommonException("1006", LOGGER);
	}

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
