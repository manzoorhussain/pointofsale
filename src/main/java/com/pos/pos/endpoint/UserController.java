package com.pos.pos.endpoint;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pos.pos.handler.UserHandler;
import com.pos.pos.model.LoginUserModel;
import com.pos.pos.model.UserModel;
import com.pos.pos.model.WsResponse;
import com.pos.pos.pk.ProductPK;
import com.pos.pos.service.UserAutherationService;
import com.pos.pos.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserAutherationService userAutherationService;
	
	@PostMapping("/add")
	public WsResponse addUser(@RequestParam Map<String, String> formData,@RequestParam("userimage") MultipartFile userimage) throws IOException {
		
		String userId=formData.get("id");
		String userName=formData.get("name");
		String textPassword=formData.get("password");
		String status=formData.get("status");
		//byte[] userImage=userimage.getBytes();
	
		
		String password=UserHandler.hashPassword(textPassword);
		
		UserModel userModel=new UserModel(userId,userName,password,status);
		
		 return userService.addUser(userModel);
		
		
	}
	
	@DeleteMapping(path="/delete")
    public WsResponse deleteUser(@RequestParam String userCode) {
		 return userService.deleteUser(userCode);
    }
	
	@GetMapping("/get")
	 public WsResponse getUserById(@RequestParam String userCode){
	   return userService.getUserByCode(userCode);
	  }
	
	
	@GetMapping("/all")
	 public WsResponse getUsers(){
	   return userService.getUsers();
	  }
	
	
	@RequestMapping(value = "/login", produces = "application/json", method = RequestMethod.POST)
	 public WsResponse login(@RequestBody LoginUserModel userModel){
		
		String userId=userModel.getLoginId();
		String password=userModel.getPassword();
		System.out.println(userId+"---"+password);
		
      return userService.login(userId, password);
	  }
	
	
	
	@RequestMapping(value = "/delete-usercode/{userCode}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public int deleteUserCode(@PathVariable String userCode) {
		System.out.println("delete-usercode--call");
		 return userAutherationService.deleteByUserCode(userCode);
    }

}
