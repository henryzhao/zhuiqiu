package com.eamon.zhuiqiu.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.eamon.zhuiqiu.util.security.CipherMethod;
import com.eamon.zhuiqiu.util.security.CodeMethod;
import com.eamon.zhuiqiu.util.security.SecurityFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eamon.zhuiqiu.user.dao.UserDao;
import com.eamon.zhuiqiu.user.entity.User;
import com.eamon.zhuiqiu.util.state.RequestLimit;
import com.eamon.zhuiqiu.util.state.StatusCode;
import com.eamon.zhuiqiu.util.state.StatusException;

/**
 * @author Eamon
 *
 */
@Service
public class UserService {

	private final Logger Log = LoggerFactory.getLogger(getClass());
	
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CaptchaService captchaService;
	
	/**
	 * 最大有效时长
	 */
	private static final long MAX_VALID_TOKEN = 1000*60*60*60*24*7;
	
	
	public Map<String, Object> register(
			String phone,
			String nickname,
			String password,
			int codeId,
			int code
			) throws StatusException{
		//检测code
		captchaService.check(codeId, code);
		
		User user = userDao.getUserByPhone(phone);
		//检测用户是否已存在
		checkUserExist(user);
		
		user = new User();
		user.setPhone(phone);
		
		//设置随机盐
		String salt = randomSalt();
		user.setSalt(salt);
		
		//设置转化后MD5密码
		password = convertPassword(password, salt);
		user.setPassword(password);
		
		//设置token
		String token = randomToken();
		user.setToken(token);
		
		//设置昵称
		user.setNickname(nickname);
		
		userDao.insertNewUser(user);
		return genUserMap(user);
	}
	
	public Map<String, Object> forget(String phone, String password, int codeId, int code) throws StatusException {
		//检测code
		captchaService.check(codeId, code);
		User user = userDao.getUserByPhone(phone);
		//检测用户是否已存在
		checkUserNull(user);
		
		//设置随机盐
		String salt = randomSalt();
		user.setSalt(salt);
		
		//设置转化后MD5密码
		password = convertPassword(password, salt);
		user.setPassword(password);
		
		//设置token
		String token = randomToken();
		user.setToken(token);
		
		userDao.updatePassword(user);
		return genUserMap(user);
	}
	
	
	
	public Map<String, Object> login(
			String phone,
			String password
			) throws StatusException{
		User user = userDao.getUserByPhone(phone);
		//检测用户
		checkUserNull(user);
		password = convertPassword(password, user.getSalt());
		//检测密码
		checkPassword(password,user.getPassword());
		
		user.setToken(randomToken());
		userDao.updateToken(user);
		return genUserMapWithToken(user);
	}
	
	public Map<String, Object> loginAdmin(String phone, String password) throws StatusException {
		User user = userDao.getUserByPhone(phone);
		//检测用户是否已存在
		checkUserNull(user);
		checkAdmin(user);
		password = convertPassword(password, user.getSalt());
		//检测密码
		checkPassword(password,user.getPassword());
		
		user.setToken(randomToken());
		userDao.updateToken(user);
		return genUserMapWithToken(user);
	}
	
	
	/**
	 * 检测用户是否不存在
	 * @param user
	 * @throws StatusException
	 */
	private void checkAdmin(User user) throws StatusException {
		if(!user.getRole().equals(RequestLimit.SUPER_ADMIN)&&
				!user.getRole().equals(RequestLimit.ADMIN))throw new StatusException(StatusCode.PERMISSION_LOW);
		
	}
	
	/**
	 * 检测用户是否不存在
	 * @param user
	 * @throws StatusException
	 */
	public void checkUserNull(User user) throws StatusException {
		if(user==null)throw new StatusException(StatusCode.USER_NULL);
		
	}
	
	
	/**
	 * 检测用户是否已存在
	 * @param user
	 * @throws StatusException
	 */
	private void checkUserExist(User user) throws StatusException {
		if(user!=null)throw new StatusException(StatusCode.USER_EXIST);
		
	}


	/**
	 * 检测密码正确性
	 * @param pwd1
	 * @param pwd2
	 * @throws StatusException
	 */
	private void checkPassword(String pwd1, String pwd2) throws StatusException {
		if(!pwd1.equals(pwd2))throw new StatusException(StatusCode.PASSWORD_ERROR);
	}


	public int logout(
			int id
			) throws StatusException{
		User user = userDao.getUserById(id);
		checkUserNull(user);
		Log.info("updateToken");
		user.setToken("");
		return userDao.updateToken(user);
	}
	
	
	
	/**
	 * 检测Token
	 * @param user
	 * @param token
	 * @throws StatusException
	 */
	public void checkToken(User user, String token) throws StatusException {
		checkUserNull(user);
		if(token.equals(""))throw new StatusException(StatusCode.PERMISSION_LOW);
		if(!user.getToken().equals(token))throw new StatusException(StatusCode.PERMISSION_LOW);
		Date now = new Date();		
		if((now.getTime()-user.getTokenTime().getTime())>MAX_VALID_TOKEN){
			user.setToken("");
			userDao.updateToken(user);
			throw new StatusException(StatusCode.PERMISSION_LOW);
		}
	}


	private String randomSalt(){
		String salt = UUID.randomUUID().toString().substring(9, 18).toUpperCase().replaceAll("-", "");
		return salt;
	}
	
	private static String randomToken(){
		String token = UUID.randomUUID().toString().substring(0, 18).toUpperCase().replaceAll("-", "");
		return token;
	}
	
	
	private String convertPassword(String password,String salt){
		return SecurityFactory.getCodeMethod(CodeMethod.SUPPORT.CODE_MD5).encode(password + salt);
	}
	
	private Map<String, Object> genUserMapWithToken(User user){
		Map<String, Object> item = genUserMap(user);
		item.put("id", user.getId());
		item.put("nickname", user.getNickname());
		item.put("phone", convertPhone(user.getPhone()));
		item.put("role", user.getRole());
		item.put("head", user.getHead());
		item.put("token", user.getToken());
		return item;
	}
	
	private Map<String, Object> genUserMap(User user){
		Map<String, Object> item = new HashMap<>();
		item.put("id", user.getId());
		item.put("nickname", user.getNickname());
		item.put("phone", convertPhone(user.getPhone()));
		item.put("role", user.getRole());
		item.put("head", user.getHead());
		return item;
	}
	
	private String convertPhone(String phone){
		return phone.substring(0,3)+"****"+phone.substring(7,11);
	}
	
	public Map<String, Object> getUserMapById(int userId) throws StatusException {
		User user = getUserById(userId);
		return genUserMap(user);
	}

	public User getUserById(int userId) throws StatusException {
		User user = userDao.getUserById(userId);
		checkUserNull(user);
		return user;
	}
	
	public Map<String, Object> getUserMapByPhone(String phone) throws StatusException {
		User user = getUserByPhone(phone);
		return genUserMap(user);
	}
	
	public User getUserByPhone(String phone) throws StatusException {
		User user = userDao.getUserByPhone(phone);
		checkUserNull(user);
		return user;
	}
	

	public int changeNickname(int id,String nickname) {
		User user = new User();
		user.setId(id);
		user.setNickname(nickname);
		return userDao.updateNickname(user);
	}

	public int changeHead(int id,String head) {
		User user = new User();
		user.setId(id);
		user.setHead(head);
		return userDao.updateHead(user);
	}

	public int changeRole(int id, String role) {
		User user = new User();
		user.setId(id);
		user.setRole(role);
		return userDao.updateRole(user);
	}

	
	public List<Map<String,Object>> getActivityUser(int activityId){
		List<Map<String,Object>> userList = new ArrayList<>();
		userDao.selectAcitvityUser(activityId).stream().forEach((e)->{
			Map<String,Object> item = genUserMap(e);
			userList.add(item);
		});
		return userList;
	}
	
	public List<Map<String,Object>> getLessonUser(int lessonId){
		List<Map<String,Object>> userList = new ArrayList<>();
		userDao.selectLessonUser(lessonId).stream().forEach((e)->{
			Map<String,Object> item = genUserMap(e);
			userList.add(item);
		});
		return userList;
	}
	
	public List<Map<String,Object>> getLessonTeacher(int lessonId){
		List<Map<String,Object>> userList = new ArrayList<>();
		userDao.selectLessonTeacher(lessonId).stream().forEach((e)->{
			Map<String,Object> item = genUserMap(e);
			userList.add(item);
		});
		return userList;
	}
	
	public List<Map<String,Object>> getLessonAssistant(int lessonId){
		List<Map<String,Object>> userList = new ArrayList<>();
		userDao.selectLessonAssistant(lessonId).stream().forEach((e)->{
			Map<String,Object> item = genUserMap(e);
			userList.add(item);
		});
		return userList;
	}

	public List<Map<String,Object>> getUsers(int page, int rows) {
		if(page<0)return null;
		int start = (page-1)*rows;
		List<Map<String,Object>> userList = new ArrayList<>();
		userDao.selectUsers(start,rows).stream().forEach((e)->{
			Map<String,Object> item = genUserMap(e);
			userList.add(item);
		});
		return userList;
	}




	
}






