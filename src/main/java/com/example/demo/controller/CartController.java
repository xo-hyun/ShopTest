package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Member;
import com.example.demo.service.CartService;

import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Controller
@Setter
public class CartController {
	@Autowired
	private CartService cs;
	
	@GetMapping("/cart/list")
	public void list(Model model, HttpSession session) {
		String id = ((Member)session.getAttribute("user")).getId();
		model.addAttribute("list", cs.listCart(id));
	}
	
	
	@GetMapping("/cart/add/{no}")
	public String add(@PathVariable("no") int no, HttpSession session) {
		String view = "/cart/add";
		Authentication authentication
		= SecurityContextHolder.getContext().getAuthentication();
		User user = (User)authentication.getPrincipal();
		String id = user.getUsername();
		Cart c= new Cart();
		c.setId(id);
		c.setGno(no);
		cs.insert(c);
		return view;
	}
}









