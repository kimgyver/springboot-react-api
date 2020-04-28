package com.jason.fullstack.springbootfullstackcrud.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jason.fullstack.springbootfullstackcrud.entity.Course;

@Primary
public interface CourseRepository extends JpaRepository<Course, Long> {

}
