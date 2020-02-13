package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uniovi.entities.Teacher;
import com.uniovi.services.TeachersService;

@RestController
public class TeachersController {

	@Autowired
	private TeachersService teachersService;

	@RequestMapping("/teacher/list")
	public String getList() {
		System.out.println(teachersService.getTeachers().toString());
		return teachersService.getTeachers().toString();
	}

	@RequestMapping(value = "/teacher/add", method = RequestMethod.POST)
	public String setMark(@ModelAttribute Teacher teacher) {
		teachersService.addTeacher(teacher);
		return teacher.toString() + " added.";
	}

	@RequestMapping("/teacher/details/{id}")
	public String getDetail(@PathVariable Long id) {
		return teachersService.getTeacher(id).toString();
	}

	@RequestMapping("/teacher/delete/{id}")
	public String deleteMark(@PathVariable Long id) {
		teachersService.deleteTeacher(id);
		return "Teacher with id= " + id + " deleted.";
	}

	@RequestMapping(value = "/teacher/edit/{id}")
	public String getEdit(Model model, @PathVariable Long id) {
		model.addAttribute("teacher", teachersService.getTeacher(id));
		return "Editting teacher with id=" + id;
	}

}
