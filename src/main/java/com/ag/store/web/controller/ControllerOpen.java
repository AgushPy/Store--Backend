package com.ag.store.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saludo")
public class ControllerOpen {

	@GetMapping("/")
	public String h() {
		return "hello";
	}
}
