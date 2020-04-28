package com.jason.fullstack.springbootfullstackcrud.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.jason.fullstack.springbootfullstackcrud.dao.CourseRepository;
import com.jason.fullstack.springbootfullstackcrud.entity.Course;

@Service
public class CourseJpaRepositoryService implements CoursesService {

	private CourseRepository courseRepository;
	
	@Autowired
	public CourseJpaRepositoryService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@Transactional
	public List<Course> findAll() {
		return courseRepository.findAll();
	}

	@Transactional
	public Course findById(long courseId) {
		//return courseDAO.findById(courseId);
		Optional<Course> result = courseRepository.findById(courseId);
		Course course = null;
		if (result.isPresent()) {
			course = result.get();
		} else {
			throw new RuntimeException("Course is not found - id: " + courseId);
		}
		return course;
	}

	@Transactional
	public Course save(Course course) {
		return courseRepository.save(course);
	}

	@Transactional
	public void deleteById(long courseId) {
		courseRepository.deleteById(courseId);
	}
}
