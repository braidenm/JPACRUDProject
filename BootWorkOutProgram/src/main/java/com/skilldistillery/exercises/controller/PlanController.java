package com.skilldistillery.exercises.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.exercises.data.PlanDAO;
import com.skilldistillery.exercises.entities.Plan;

@Controller
public class PlanController {

	@Autowired
	PlanDAO dao;

	@RequestMapping("home.do")
	public String home() {
		return "index";
	}

	@RequestMapping(path = "create.do", method = RequestMethod.POST)
	public String create(Plan plan, RedirectAttributes redir) {

		redir.addFlashAttribute("plan", dao.create(plan));

		return "redirect:home.do";
	}

	@RequestMapping(path = "create.do", method = RequestMethod.GET)
	public String createInit() {
		return "create";
	}

	@RequestMapping(path = "update.do", method = RequestMethod.POST)
	public String update(Plan plan, RedirectAttributes redir) {
		plan = dao.update(plan.getId(), plan);
		redir.addFlashAttribute("plan", plan);
		return "redirect:home.do";
	}

	@RequestMapping(path = "update.do", method = RequestMethod.GET)
	public String updateInit(Model model, Integer id) {
		model.addAttribute("plan", dao.get(id));
		return "update";
	}

	@RequestMapping(path = "get.do", method = RequestMethod.POST)
	public String get(Integer id, RedirectAttributes redir) {
		Plan plan = dao.get(id);
		boolean displayMessage = false;
		if(plan == null) {
			displayMessage = true;
			redir.addFlashAttribute("notFound", displayMessage );
			return "redirect:home.do";
		}
		else {
			redir.addFlashAttribute("notFound", displayMessage );
			redir.addFlashAttribute("plan", plan);
			return "redirect:home.do";
		}
	}

	@RequestMapping(path = "getall.do", method = RequestMethod.POST)
	public String getAll(RedirectAttributes redir) {
		List<Plan> planList = dao.getAll();
		boolean displayMessage = false;
		if(planList.isEmpty()) {
			displayMessage = true;
			redir.addFlashAttribute("notFound", displayMessage );
			return "redirect:home.do";
		}
		else {
			redir.addFlashAttribute("notFound", displayMessage );
			redir.addFlashAttribute("planList", planList);
			return "redirect:home.do";
		}
	}

	@RequestMapping(path = "destroy.do", method = RequestMethod.POST)
	public String destroy(@RequestParam("delete") Integer id, RedirectAttributes redir) {
		
		redir.addFlashAttribute("deleted", dao.destroy(id));
		redir.addFlashAttribute("planid", id);

		return "redirect:home.do";
	}

	@RequestMapping(path = "search.do", method = RequestMethod.POST)
	public String search(@RequestParam("search") String search, RedirectAttributes redir) {
		List<Plan> planList = new ArrayList<>();
		planList.addAll(dao.search(search));
		boolean displayMessage = false;
		
		if(planList.isEmpty()) {
			displayMessage = true;
			redir.addFlashAttribute("notFound", displayMessage );
			return "redirect:home.do";
		}
		else {
			
			redir.addFlashAttribute("notFound", displayMessage );
			redir.addFlashAttribute("planList", planList);
			return "redirect:home.do";
		}
	}
	
}
