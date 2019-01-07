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
import com.skilldistillery.exercises.data.PlanDAO;
import com.skilldistillery.exercises.entities.Day;
import com.skilldistillery.exercises.entities.Plan;

@Controller
public class PlanController {

	@Autowired
	PlanDAO dao;
	@Autowired
	DayDAO dayDao;

	@RequestMapping("plan.do")
	public String exercises() {
		return "plan";
	}
	
	@RequestMapping(path = "createPlan.do", method = RequestMethod.POST)
	public String create(Plan plan, RedirectAttributes redir) {

		redir.addFlashAttribute("plan", dao.create(plan));

		return "redirect:plan.do";
	}

	@RequestMapping(path = "createPlan.do", method = RequestMethod.GET)
	public String createInit() {
		return "createplan";
	}

	@RequestMapping(path = "updatePlan.do", method = RequestMethod.POST)
	public String update(@RequestParam(name="id") Integer id, @RequestParam(name="name") String name, @RequestParam(required=false, name="days") Integer[] dayId, RedirectAttributes redir) {
		Plan plan = dao.get(id);
		plan.setName(name);
		List<Day> days = new ArrayList<Day>();
		List<Day> planDays = plan.getDays();
		
		if(dayId != null) {
			for (Integer day : dayId) {
				days.add(dayDao.get(day));
			}
			for (Day day : days) {
				if(!planDays.contains(day)) {
					plan.addDay(day);
				}
			}
			List<Day> remove = new ArrayList<Day>();
			for (Day day : planDays) {
				if(!days.contains(day)) {
					remove.add(day);
				}
			}
			for (Day day : remove) {
				plan.removeDay(day);
			}
		}
		else if(plan.getDays() !=null) {
			plan.getDays().clear();
		}
		
		plan = dao.update(plan.getId(), plan);
		redir.addFlashAttribute("plan", plan);
		return "redirect:plan.do";
	}

	@RequestMapping(path = "updatePlan.do", method = RequestMethod.GET)
	public String updateInit(Model model, Integer id) {
		model.addAttribute("plan", dao.get(id));

		List<Day> planDays = dao.get(id).getDays();
		List<Day> alldays = dayDao.getAll();
		if(planDays != null) {
			alldays.removeAll(planDays);
		}
		
		model.addAttribute("remainingDays", alldays);
		return "updatePlan";
	}

	@RequestMapping(path = "getPlan.do", method = RequestMethod.POST)
	public String get(Integer id, RedirectAttributes redir) {
		Plan plan = dao.get(id);
		boolean displayMessage = false;
		if(plan == null) {
			displayMessage = true;
			redir.addFlashAttribute("notFound", displayMessage );
			return "redirect:plan.do";
		}
		else {
			redir.addFlashAttribute("notFound", displayMessage );
			redir.addFlashAttribute("plan", plan);
			return "redirect:plan.do";
		}
	}

	@RequestMapping(path = "getallPlan.do", method = RequestMethod.POST)
	public String getAll(RedirectAttributes redir) {
		List<Plan> planList = dao.getAll();
		boolean displayMessage = false;
		if(planList.isEmpty()) {
			displayMessage = true;
			redir.addFlashAttribute("notFound", displayMessage );
			return "redirect:plan.do";
		}
		else {
			redir.addFlashAttribute("notFound", displayMessage );
			redir.addFlashAttribute("planList", planList);
			return "redirect:plan.do";
		}
	}

	@RequestMapping(path = "destroyPlan.do", method = RequestMethod.POST)
	public String destroy(@RequestParam("delete") Integer id, RedirectAttributes redir) {
		
		redir.addFlashAttribute("deleted", dao.destroy(id));
		redir.addFlashAttribute("planId", id);

		return "redirect:plan.do";
	}

	@RequestMapping(path = "searchPlan.do", method = RequestMethod.POST)
	public String search(@RequestParam("search") String search, RedirectAttributes redir) {
		List<Plan> planList = new ArrayList<>();
		planList.addAll(dao.search(search));
		boolean displayMessage = false;
		
		if(planList.isEmpty()) {
			displayMessage = true;
			redir.addFlashAttribute("notFound", displayMessage );
			return "redirect:plan.do";
		}
		else {
			
			redir.addFlashAttribute("notFound", displayMessage );
			redir.addFlashAttribute("planList", planList);
			return "redirect:plan.do";
		}
	}
	
}
