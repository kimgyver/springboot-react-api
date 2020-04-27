package com.jason.fullstack.springbootfullstackcrud.service;

import java.util.List;

import com.jason.fullstack.springbootfullstackcrud.entity.Course;

public interface CoursesService {
	public List<Course> findAll();
	public Course findById(long courseId);
	public Course save(Course course);
	public void deleteById(long courseId);
}
