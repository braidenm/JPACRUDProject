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
import com.skilldistillery.exercises.data.ExerciseDAO;
import com.skilldistillery.exercises.data.PlanDAO;
import com.skilldistillery.exercises.entities.Day;
import com.skilldistillery.exercises.entities.Exercises;
import com.skilldistillery.exercises.entities.Plan;

@Controller
public class DayController {

	@Autowired
	DayDAO dao;
	@Autowired
	ExerciseDAO exerDao;
	@Autowired
	PlanDAO planDao;
	
	@RequestMapping(path="day.do")
	public String day() {
		return "day";
	}

	@RequestMapping(path = "createDay.do", method = RequestMethod.POST)
	public String create(Day day, RedirectAttributes redir) {

		redir.addFlashAttribute("day", dao.create(day));

		return "redirect:day.do";
	}

	@RequestMapping(path = "createDay.do", method = RequestMethod.GET)
	public String createInit() {
		return "createday";
	}

	@RequestMapping(path = "updateDay.do", method = RequestMethod.POST)
	public String update(@RequestParam(name="id") Integer id, @RequestParam(name="name") String name, @RequestParam(name="day") String weekDay,
			@RequestParam(required=false, name="exercises" ) Integer[] exersId, @RequestParam(required=false, name="plans") Integer[] plansId, RedirectAttributes redir) {
		Day day = dao.get(id);
		day.setName(name);
		day.setDay(weekDay);
				
		List<Exercises> exers = new ArrayList<>();
		List<Exercises> dayExers = day.getExercises();
		
		if (exersId != null) {
			for (Integer exer : exersId) {
				exers.add(exerDao.get(exer));
			}
			for (Exercises exer : exers) {
				if(!dayExers.contains(exer)) {
					day.addExercise(exer);
				}
			}
			List<Exercises> remove = new ArrayList<>();
			for (Exercises exer : dayExers) {
				if(!exers.contains(exer)) {
					remove.add(exer);
				}
			}
			for (Exercises exer : remove) {
				day.removeExercise(exer);
			}
		}
		else if(day.getExercises() !=null){
			day.getExercises().clear();
		}
		List<Plan> plans = new ArrayList<>();
		List<Plan> dayPlans = day.getPlans();
		
		if(plansId != null) {
			for (Integer plan : plansId) {
				plans.add(planDao.get(plan));
			}
			for (Plan plan : plans) {
				if(!dayPlans.contains(plan)) {
					day.addPlan(plan);
				}
			}
			List<Plan> removePlan = new ArrayList<>();
			for (Plan plan : dayPlans) {
				if(!plans.contains(plan)) {
					removePlan.add(plan);
				}
			}
			for (Plan plan : removePlan) {
				day.removePlan(plan);
			}
		}
		else if(day.getPlans() !=null){
			day.getPlans().clear();
		}
		
		day = dao.update(day.getId(), day);
		redir.addFlashAttribute("day", day);
		
		return "redirect:day.do";
	}

	@RequestMapping(path = "updateDay.do", method = RequestMethod.GET)
	public String updateInit(Model model, Integer id) {
		model.addAttribute("day", dao.get(id));
		
		List<Exercises> dayExers = dao.get(id).getExercises();
		List<Plan> dayPlans = dao.get(id).getPlans();
		List<Plan> allPlans = planDao.getAll();
		List<Exercises> allExers = exerDao.getAll();
		if(dayExers != null){
			allExers.removeAll(dayExers);
		}
		if(dayPlans != null){
			allPlans.removeAll(dayPlans);
		}
		
		model.addAttribute("remainingPlans", allPlans);
		model.addAttribute("remainingExercises", allExers);
		
		return "updateDay";
	}

	@RequestMapping(path = "getDay.do", method = RequestMethod.POST)
	public String get(Integer id, RedirectAttributes redir) {
		Day day = dao.get(id);
		boolean displayMessage = false;
		if(day == null) {
			displayMessage = true;
			redir.addFlashAttribute("notFound", displayMessage );
			return "redirect:day.do";
		}
		else {
			redir.addFlashAttribute("notFound", displayMessage );
			redir.addFlashAttribute("day", day);
			return "redirect:day.do";
		}
	}

	@RequestMapping(path = "getallDay.do", method = RequestMethod.POST)
	public String getAll(RedirectAttributes redir) {
		List<Day> dayList = dao.getAll();
		boolean displayMessage = false;
		if(dayList.isEmpty()) {
			displayMessage = true;
			redir.addFlashAttribute("notFound", displayMessage );
			return "redirect:day.do";
		}
		else {
			redir.addFlashAttribute("notFound", displayMessage );
			redir.addFlashAttribute("dayList", dayList);
			return "redirect:day.do";
		}
	}

	@RequestMapping(path = "destroyDay.do", method = RequestMethod.POST)
	public String destroy(@RequestParam("delete") Integer id, RedirectAttributes redir) {
		
		redir.addFlashAttribute("deleted", dao.destroy(id));
		redir.addFlashAttribute("dayId", id);

		return "redirect:day.do";
	}

	@RequestMapping(path = "searchDay.do", method = RequestMethod.POST)
	public String search(@RequestParam("search") String search, RedirectAttributes redir) {
		List<Day> dayList = new ArrayList<>();
		dayList.addAll(dao.search(search));
		boolean displayMessage = false;
		
		if(dayList.isEmpty()) {
			displayMessage = true;
			redir.addFlashAttribute("notFound", displayMessage );
			return "redirect:day.do";
		}
		else {
			
			redir.addFlashAttribute("notFound", displayMessage );
			redir.addFlashAttribute("dayList", dayList);
			return "redirect:day.do";
		}
	}
	
}
