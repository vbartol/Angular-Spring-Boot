package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.service.ApplicationService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class Controller {

	@Autowired
	ApplicationService applicationService = new ApplicationService();

	@PostMapping(value = "/users/save")
	public void postCustomer(@RequestBody List<User> user) throws Exception {
		applicationService.savingCustomer(user);
	}
}
