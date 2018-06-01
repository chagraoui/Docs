package org.tux.config.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.tux.dao.UserRepository;
import org.tux.entites.User;;



/**
*
* UserDetails service that reads the user credentials from the database, using
* a JPA repository.
*
*/

@Service("securityUserDetailsService")
public class UserDetailsServiceImp implements UserDetailsService {

		private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImp.class);

		@Autowired
		private UserRepository userRepository;

		@Override
		public UserDetails loadUserByUsername(String username) {
			User user = null;
			String password = null;
			try {
				user = userRepository.findByEmail(username);
				password = user.getPassword();

				//verify user 
				verifyUserAccount(user);
				// get authorities of logged user based on group
				List<GrantedAuthority> permissions = new ArrayList<>();
//				permissions.add(new SimpleGrantedAuthority(user.getGroup().getPermissions().toString()));

				logger.info("Found user in database: {}" , user.getEmail());
				return new org.springframework.security.core.userdetails.User(username, password, permissions);
			} catch (Exception e) {
				logger.error(e.toString());
				throw new UsernameNotFoundException(e.getMessage());
			}

		}

		/**
		 * verifyUserCount
		 * @param user
		 * @throws ParseException
		 */
		private void verifyUserAccount(User user){
			if (user == null) {
				String message = "No user was found in database :: input user null";
				logger.info(message);
				throw new UsernameNotFoundException(message);
			} 
			}
	}