package com.jason.fullstack.springbootfullstackcrud.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jason.fullstack.springbootfullstackcrud.entity.Course;

@Repository
public class CourseDAO {
	private EntityManager entityManager;
	
	@Autowired
	public CourseDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public List<Course> findAll() {
		Session session = entityManager.unwrap(Session.class);
		Query<Course> query = session.createQuery("from Course", Course.class);
		List<Course> courses = query.getResultList();
		return courses;

	}
	
	public Course findById(long courseId) {
		Session session = entityManager.unwrap(Session.class);
		Course course = session.get(Course.class, courseId);
		return course;
		
	}

	public Course save(Course course) {
		Session session = entityManager.unwrap(Session.class);
		//session.saveOrUpdate(course);
		if (course.getId() == -1L) {
			session.save(course);
		} else {
			session.update(course);
		}
		
		return course;
	}
	
	public void deleteById(long courseId) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("delete from Course where id=:courseId");
		query.setParameter("courseId", courseId);
		query.executeUpdate();
	}
}
