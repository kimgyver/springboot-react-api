package com.jason.fullstack.springbootfullstackcrud.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jason.fullstack.springbootfullstackcrud.entity.Course;
import com.jason.fullstack.springbootfullstackcrud.service.CoursesService;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://react-using-springboot.s3-website-ap-southeast-2.amazonaws.com" })
@RestController
public class CourseResource {
	
	@Autowired
	//@Qualifier("coursesDBService")
	//@Qualifier("coursesHardcodedService")
	@Qualifier("courseJpaRepositoryService")
	private CoursesService courseManagementService;
	

	@GetMapping("/instructors/{username}/courses")
	public List<Course> getAllCourses(@PathVariable String username) {
		return courseManagementService.findAll();
	}
	
	@GetMapping("/instructors/{username}/courses/{id}")
	public Course getCourseById(@PathVariable String username, @PathVariable long id) {
		return courseManagementService.findById(id);
	}
	
	@DeleteMapping("/instructors/{username}/courses/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable String username, @PathVariable long id) {
		Course course = courseManagementService.findById(id);
		if (course == null) {
			return ResponseEntity.notFound().build();
		}
		
		courseManagementService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/instructors/{username}/courses/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable String username, @PathVariable long id,
			@RequestBody Course course) {
		Course courseUpdated = courseManagementService.save(course);
		return new ResponseEntity<Course>(courseUpdated, HttpStatus.OK);
	}
	
	@PostMapping("/instructors/{username}/courses")
	public ResponseEntity<Void> createCourse(@PathVariable String username, @RequestBody Course course) {
		Course createdCourse = courseManagementService.save(course);
	
		// Location
		// Get current resource url
		/// {id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdCourse.getId())
				.toUri();		
		return ResponseEntity.created(uri).build();
	}	
}