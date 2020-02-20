package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Teacher;
import com.uniovi.services.TeachersService;
import com.uniovi.validators.AddTeacherFormValidator;

@Controller
public class TeachersController {

	@Autowired
	private TeachersService teachersService;

	@Autowired
	private AddTeacherFormValidator addTeacherValidator;

	@RequestMapping("/teacher/list")
	public String getList(Model model) {
		model.addAttribute("teacherList", teachersService.getTeachers());
		return "teacher/list";
	}

	@RequestMapping(value = "/teacher/add", method = RequestMethod.POST)
	public String setTeacher(@ModelAttribute Teacher teacher, BindingResult result) {
		addTeacherValidator.validate(teacher, result);
		if (result.hasErrors()) {
			return "/teacher/add";
		}
		teachersService.addTeacher(teacher);
		return "redirect:/teacher/list";
	}

	@RequestMapping(value = "/teacher/add")
	public String getTeacher(Model model) {
		model.addAttribute("teacher", new Teacher());
		return "teacher/add";
	}

	@RequestMapping("/teacher/details/{id}")
	public String getDetail(Model model, @PathVariable Long id) {
		model.addAttribute("teacher", teachersService.getTeacher(id));
		return "teacher/details";
	}

	@RequestMapping("/teacher/delete/{id}")
	public String deleteMark(@PathVariable Long id) {
		teachersService.deleteTeacher(id);
		return "redirect:/teacher/list";
	}

	@RequestMapping(value = "/teacher/edit/{id}")
	public String getEdit(Model model, @PathVariable Long id) {
		model.addAttribute("teacher", teachersService.getTeacher(id));
		return "teacher/edit";
	}

	@RequestMapping(value = "/teacher/edit", method = RequestMethod.POST)
	public String setEdit(Model model, @ModelAttribute Teacher teacher, @RequestParam Long id) {
		teacher.setId(id);
		teachersService.addTeacher(teacher);
		return "redirect:/teacher/details/" + id;
	}
}