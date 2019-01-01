package com.skilldistillery.exercises.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.exercises.data.ExerciseDAO;
import com.skilldistillery.exercises.entities.Exercises;

@Controller
public class WorkOutController {

	@Autowired
	ExerciseDAO dao;

	@RequestMapping("/.do")
	public String home() {
		return "index";
	}

	@RequestMapping(path = "create.do", method = RequestMethod.POST)
	public String create(@RequestParam("id") Integer id, @RequestParam("name") String name,
			@RequestParam("category") String category, @RequestParam("description") String description,
			@RequestParam("sets") Integer sets, @RequestParam("reps") Integer reps, @RequestParam("rest") Integer rest,
			RedirectAttributes redir) {

		Exercises exer = new Exercises(id, name, category, description, sets, reps, rest);

		redir.addFlashAttribute("exer", dao.create(exer));

		return "redirect:/.do";
	}

	@RequestMapping(path = "create.do", method = RequestMethod.GET)
	public String createInit() {
		return "create";
	}

	@RequestMapping(path = "update.do", method = RequestMethod.POST)
	public String update(@RequestParam("id") Integer id, @RequestParam("name") String name,
			@RequestParam("category") String category, @RequestParam("description") String description,
			@RequestParam("sets") Integer sets, @RequestParam("reps") Integer reps, @RequestParam("rest") Integer rest,
			RedirectAttributes redir) {
		Exercises exer = new Exercises(id, name, category, description, sets, reps, rest);
		System.out.println(exer);
		exer = dao.update(exer.getId(), exer);
		redir.addFlashAttribute("exer", exer);
		return "redirect:/.do";
	}

	@RequestMapping(path = "update.do", method = RequestMethod.GET)
	public String updateInit(Model model, int id) {
		model.addAttribute("exer", dao.get(id));
		return "update";
	}

	@RequestMapping(path = "get.do", method = RequestMethod.POST)
	public String get(int id, RedirectAttributes redir) {
		redir.addFlashAttribute("exer", dao.get(id));
		return "redirect:/.do";
	}

	@RequestMapping(path = "getall.do", method = RequestMethod.POST)
	public String getAll(RedirectAttributes redir) {
		redir.addFlashAttribute("exerList", dao.getAll());
		return "redirect:/.do";
	}

	@RequestMapping(path = "destroy.do", method = RequestMethod.POST)
	public String destroy(@RequestParam("delete") Integer id, RedirectAttributes redir) {
		redir.addFlashAttribute("deleted", dao.destroy(id));
		redir.addFlashAttribute("exerid", id);

		return "redirect:/.do";
	}

	@RequestMapping(path = "search.do", method = RequestMethod.POST)
	public String search(@RequestParam("search") String search, RedirectAttributes redir) {
		List<Exercises> exerciseList = new ArrayList<>();
		exerciseList.addAll(dao.search(search));
		redir.addFlashAttribute("exerList", exerciseList);
		
		
		return "redirect:/.do";
	}
	
}
