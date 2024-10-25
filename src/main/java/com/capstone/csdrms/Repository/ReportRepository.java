package com.capstone.csdrms.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capstone.csdrms.Entity.ReportEntity;

@Repository
public interface ReportRepository  extends JpaRepository<ReportEntity, Long> {
	
	@Query("SELECT r FROM ReportEntity r WHERE (r.record.student.grade = :grade AND r.record.student.section = :section AND r.record.student.schoolYear = :schoolYear) OR r.complainant = :complainant")
	List<ReportEntity> findReportsByGradeSectionAndSchoolYearOrComplainant(@Param("grade") int grade, @Param("section") String section, @Param("schoolYear") String schoolYear, @Param("complainant") String complainant);

	
	List<ReportEntity> findAllByComplainant(String complainant);
	
	List<ReportEntity> findAllByViewedBySsoFalse();
	
	List<ReportEntity> findAllByRecord_Student_GradeAndRecord_Student_SectionAndRecord_Student_SchoolYearAndViewedByAdviserFalse(int grade, String section, String schoolYear);
	
	@Query("SELECT r FROM ReportEntity r WHERE r.complainant != :complainant")
    List<ReportEntity> findReportsExcludingComplainant(@Param("complainant") String complainant);
	

}
