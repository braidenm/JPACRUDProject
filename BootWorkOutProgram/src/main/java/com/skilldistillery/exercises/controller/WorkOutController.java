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
import com.skilldistillery.exercises.entities.Day;
import com.skilldistillery.exercises.entities.Exercises;

@Controller
public class WorkOutController {

	@Autowired
	ExerciseDAO dao;
	@Autowired
	DayDAO dayDao;

	@RequestMapping("home.do")
	public String home() {
		return "index";
	}
	
	@RequestMapping("exercises.do")
	public String exercises() {
		return "exercises";
	}

	@RequestMapping(path = "createExercise.do", method = RequestMethod.POST)
	public String create(@RequestParam("id") Integer id, @RequestParam("name") String name,
			@RequestParam("category") String category, @RequestParam("description") String description,
			@RequestParam("sets") Integer sets, @RequestParam("reps") Integer reps, @RequestParam("rest") Integer rest,
			RedirectAttributes redir) {

		Exercises exer = new Exercises(id, name, category, description, sets, reps, rest);

		redir.addFlashAttribute("exer", dao.create(exer));

		return "redirect:exercises.do";
	}

	@RequestMapping(path = "createExercise.do", method = RequestMethod.GET)
	public String createInit() {
		return "create";
	}

	@RequestMapping(path = "updateExercise.do", method = RequestMethod.POST)
	public String update(@RequestParam("id") Integer id, @RequestParam("name") String name,
			@RequestParam("category") String category, @RequestParam("description") String description,
			@RequestParam("sets") Integer sets, @RequestParam("reps") Integer reps, @RequestParam("rest") Integer rest,
			@RequestParam(required=false, name="days") Integer[] dayId, RedirectAttributes redir) {
		Exercises exer = dao.get(id);
		exer.setName(name);
		exer.setCategory(category);
		exer.setDescription(description);
		exer.setSets(sets);
		exer.setReps(reps);
		exer.setRest(rest);
				
		List<Day> days = new ArrayList<Day>();
		List<Day> exerDays = exer.getDays();
		
		if(dayId != null) {
			for (Integer day : dayId) {
				days.add(dayDao.get(day));
			}
			for (Day day : days) {
				if(!exerDays.contains(day)) {
					exer.addDay(day);
				}
			}
			List<Day> remove = new ArrayList<Day>();
			for (Day day : exerDays) {
				if(!days.contains(day)) {
					remove.add(day);
				}
			}
			for (Day day : remove) {
				exer.removeDay(day);
			}
		}
		else if(exer.getDays() !=null){
			exer.getDays().clear();
		}
		
		
		exer = dao.update(exer.getId(), exer);
		redir.addFlashAttribute("exer", exer);
		return "redirect:exercises.do";
	}

	@RequestMapping(path = "updateExercise.do", method = RequestMethod.GET)
	public String updateInit(Model model, Integer id) {
		model.addAttribute("exer", dao.get(id));
		
		List<Day> exerDays = dao.get(id).getDays();
		List<Day> alldays = dayDao.getAll();
		if(exerDays != null) {
			alldays.removeAll(exerDays);
		}
		model.addAttribute("remainingDays", alldays);
		
		return "update";
	}

	@RequestMapping(path = "getExercise.do", method = RequestMethod.POST)
	public String get(Integer id, RedirectAttributes redir) {
		Exercises exercise = dao.get(id);
		boolean displayMessage = false;
		if(exercise == null) {
			displayMessage = true;
			redir.addFlashAttribute("notFound", displayMessage );
			return "redirect:exercises.do";
		}
		else {
			redir.addFlashAttribute("notFound", displayMessage );
			redir.addFlashAttribute("exer", exercise);
			return "redirect:exercises.do";
		}
	}

	@RequestMapping(path = "getallExercise.do", method = RequestMethod.POST)
	public String getAll(RedirectAttributes redir) {
		List<Exercises> exerList = dao.getAll();
		boolean displayMessage = false;
		if(exerList.isEmpty()) {
			displayMessage = true;
			redir.addFlashAttribute("notFound", displayMessage );
			return "redirect:exercises.do";
		}
		else {
			redir.addFlashAttribute("notFound", displayMessage );
			redir.addFlashAttribute("exerList", exerList);
			return "redirect:exercises.do";
		}
	}

	@RequestMapping(path = "destroyExercise.do", method = RequestMethod.POST)
	public String destroy(@RequestParam("delete") Integer id, RedirectAttributes redir) {
		
		redir.addFlashAttribute("deleted", dao.destroy(id));
		redir.addFlashAttribute("exerid", id);

		return "redirect:exercises.do";
	}

	@RequestMapping(path = "searchExercise.do", method = RequestMethod.POST)
	public String search(@RequestParam("search") String search, RedirectAttributes redir) {
		List<Exercises> exerciseList = new ArrayList<>();
		exerciseList.addAll(dao.search(search));
		boolean displayMessage = false;
		
		if(exerciseList.isEmpty()) {
			displayMessage = true;
			redir.addFlashAttribute("notFound", displayMessage );
			return "redirect:exercises.do";
		}
		else {
			
			redir.addFlashAttribute("notFound", displayMessage );
			redir.addFlashAttribute("exerList", exerciseList);
			return "redirect:exercises.do";
		}
	}
	
}
