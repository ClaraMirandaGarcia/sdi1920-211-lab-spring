package com.uniovi.services;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.uniovi.entities.Teacher;

@Service
public class TeachersService {

	private List<Teacher> teacherList = new LinkedList<Teacher>();

	@PostConstruct
	public void init() {
		teacherList.add(new Teacher(1L, "12345678A", "Luis", "González", "Cat1"));
		teacherList.add(new Teacher(2L, "23456789B", "Ana", "Barrios", "Cat2"));
	}

	public List<Teacher> getTeachers() {
		return teacherList;
	}

	public Teacher getTeacher(Long id) {
		return teacherList.stream().filter(teacher -> teacher.getId().equals(id)).findFirst().get();
	}

	public void addTeacher(Teacher teacher) {
		if (teacher.getId() == null) {
			teacher.setId(teacherList.get(teacherList.size() - 1).getId() + 1);
		}

		teacherList.add(teacher);
	}

	public void deleteTeacher(Long id) {
		teacherList.removeIf(teacher -> teacher.getId().equals(id));
	}
	
	public void editTeacher(Long id) {
		
	}
}
