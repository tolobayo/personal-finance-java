package com.example.finance.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.finance.entities.User;
import com.example.finance.repositories.UserRepository;

//Note: service annotation allows service class to be injected in controller(api) without instantiating new instance (instantiating new instances is bad in java)
@Service
public class UserService {

	private final UserRepository userRepository;

	//Dependency injection allows us to make us of the User repository. For this to work, user repository must also be defined as a bean 
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

    public List<User> getUsers() {
		return userRepository.findAll();
	}

    public void addNewUser(User user) {
        // TODO: validate user email is formatted properly
		//Optional<User> indicates the value may be empty to avoid null pointer exceptions
		Optional<User> newUser = userRepository.findUserByEmail(user.getEmail());

		if (newUser.isPresent()) throw new IllegalStateException("User has already been registered");
		else userRepository.save(user);

        System.out.println(user);
    }

	public void deleteUser(Long id) {
		boolean userExists = userRepository.existsById(id);

		if (!userExists) throw new IllegalStateException("User with the id " + id + " does not exist");
		else userRepository.deleteById(id);
	}

	// used to perform multiple database transactions like saving or updating multiple entities
	@Transactional
	public void updateUser(Long id, String firstName, String lastName) {
		boolean userExists = userRepository.existsById(id);
		if (!userExists) throw new IllegalStateException("Update failed: user with the id " + id + " does not exist");

		User user = userRepository.findById(id).orElseThrow();

		if (firstName != null && firstName.length() > 0 && !Objects.equals(user.getFirstName(), firstName)) {
			user.setFirstName(firstName);
		}
		if (lastName != null && lastName.length() > 0 && !Objects.equals(user.getLastName(), lastName)) {
			user.setLastName(lastName);
		}


	}


}
