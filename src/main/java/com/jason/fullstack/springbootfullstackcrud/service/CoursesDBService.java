package com.jason.fullstack.springbootfullstackcrud.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.jason.fullstack.springbootfullstackcrud.dao.CourseDAO;
import com.jason.fullstack.springbootfullstackcrud.entity.Course;

@Service
@Primary
public class CoursesDBService implements CoursesService {

	private CourseDAO courseDAO;
	
	@Autowired
	public CoursesDBService(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	@Transactional
	public List<Course> findAll() {
		return courseDAO.findAll();
	}

	@Transactional
	public Course findById(long courseId) {
		return courseDAO.findById(courseId);
	}

	@Transactional
	public Course save(Course course) {
		return courseDAO.save(course);
	}

	@Transactional
	public void deleteById(long courseId) {
		courseDAO.deleteById(courseId);
	}
}
