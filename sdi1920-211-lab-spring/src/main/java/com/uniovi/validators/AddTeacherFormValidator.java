package com.uniovi.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Teacher;
import com.uniovi.services.TeachersService;

@Component
public class AddTeacherFormValidator implements Validator {

	@Autowired
	private TeachersService teachersService;

	@Override
	public boolean supports(Class<?> aClass) {
		return Teacher.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Teacher teacher = (Teacher) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");

		String dni = String.valueOf(teacher.getDni());
		String name = String.valueOf(teacher.getNombre());
		String cat = String.valueOf(teacher.getCategoria());
		
		if (dni.length() != 9) {
			errors.rejectValue("dni", "Error.teacher.dni.length");
		}

		if (!isLetter(dni.charAt(dni.length() - 1))) {
			errors.rejectValue("dni", "Error.teacher.dni.character");
		}

		if (teachersService.getTeacherByDni(teacher.getDni()) != null) {
			errors.rejectValue("dni", "Error.teacher.dni.duplicate");
		}
		
		if(name.length() <2 ) {
			errors.rejectValue("nombre", "Error.teacher.name.empty");
		}
		
		if(!cat.contains("Cat") ) {
			errors.rejectValue("categoria", "Error.teacher.cat.notValid");
		}

	}

	private boolean isLetter(char caracter) {
		return Character.isLetter(caracter);
	}

}