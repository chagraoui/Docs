package org.tux.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tux.dao.UserRepository;
import org.tux.entites.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



@RestController
@Api(value = "user controller", description = "Operations for users management")
@RequestMapping(value="/rest/users")
public class UserContoller {


	@Autowired
	UserRepository userRepository;

	private Logger logger = Logger.getLogger(UserContoller.class);


	@RequestMapping(value = "/singIN", method = RequestMethod.POST)
    @ApiOperation(value = "get all articles", notes = "get articles list")
	public User  saveArticle(@RequestBody  User user){

		User u =new User("demo@demo.com","demo");
		u=userRepository.save(u);

		logger.info("save "+ u);
		User u1 =new User();
		u1=userRepository.findByEmail(user.getEmail());
		logger.info("get "+ u);
		return u1;
	}

}
