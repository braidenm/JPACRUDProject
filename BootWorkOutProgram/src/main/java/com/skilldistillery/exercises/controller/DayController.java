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

import com.skilldistillery.exercises.data.DayDAO;
import com.skilldistillery.exercises.entities.Day;

@Controller
public class DayController {

	@Autowired
	DayDAO dao;

	@RequestMapping("home.do")
	public String home() {
		return "index";
	}

	@RequestMapping(path = "create.do", method = RequestMethod.POST)
	public String create(Day day, RedirectAttributes redir) {

		redir.addFlashAttribute("exer", dao.create(day));

		return "redirect:home.do";
	}

	@RequestMapping(path = "create.do", method = RequestMethod.GET)
	public String createInit() {
		return "create";
	}

	@RequestMapping(path = "update.do", method = RequestMethod.POST)
	public String update(Day day, RedirectAttributes redir) {
		System.out.println(day);
		day = dao.update(day.getId(), day);
		redir.addFlashAttribute("day", day);
		
		
		return "redirect:home.do";
	}

	@RequestMapping(path = "update.do", method = RequestMethod.GET)
	public String updateInit(Model model, Integer id) {
		model.addAttribute("day", dao.get(id));
		return "update";
	}

	@RequestMapping(path = "get.do", method = RequestMethod.POST)
	public String get(Integer id, RedirectAttributes redir) {
		Day day = dao.get(id);
		boolean displayMessage = false;
		if(day == null) {
			displayMessage = true;
			redir.addFlashAttribute("notFound", displayMessage );
			return "redirect:home.do";
		}
		else {
			redir.addFlashAttribute("notFound", displayMessage );
			redir.addFlashAttribute("day", day);
			return "redirect:home.do";
		}
	}

	@RequestMapping(path = "getall.do", method = RequestMethod.POST)
	public String getAll(RedirectAttributes redir) {
		List<Day> dayList = dao.getAll();
		boolean displayMessage = false;
		if(dayList.isEmpty()) {
			displayMessage = true;
			redir.addFlashAttribute("notFound", displayMessage );
			return "redirect:home.do";
		}
		else {
			redir.addFlashAttribute("notFound", displayMessage );
			redir.addFlashAttribute("dayList", dayList);
			return "redirect:home.do";
		}
	}

	@RequestMapping(path = "destroy.do", method = RequestMethod.POST)
	public String destroy(@RequestParam("delete") Integer id, RedirectAttributes redir) {
		
		redir.addFlashAttribute("deleted", dao.destroy(id));
		redir.addFlashAttribute("dayid", id);

		return "redirect:home.do";
	}

	@RequestMapping(path = "search.do", method = RequestMethod.POST)
	public String search(@RequestParam("search") String search, RedirectAttributes redir) {
		List<Day> dayList = new ArrayList<>();
		dayList.addAll(dao.search(search));
		boolean displayMessage = false;
		
		if(dayList.isEmpty()) {
			displayMessage = true;
			redir.addFlashAttribute("notFound", displayMessage );
			return "redirect:home.do";
		}
		else {
			
			redir.addFlashAttribute("notFound", displayMessage );
			redir.addFlashAttribute("exerList", dayList);
			return "redirect:home.do";
		}
	}
	
}
