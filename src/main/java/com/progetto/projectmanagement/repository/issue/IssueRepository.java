package com.progetto.projectmanagement.repository.issue;

import com.progetto.projectmanagement.domain.issue.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface IssueRepository  extends JpaRepository<Issue,Integer> {

    @Query(value = "SELECT * FROM issue WHERE member_id=:mem_id",nativeQuery = true)
    ArrayList<Issue> getIssueByMember(@Param("mem_id") String taskId);

    @Query(value = "SELECT * FROM issue WHERE taskId=:taskId",nativeQuery = true)
    ArrayList<Issue> getIssueByTask(@Param("taskId") String taskId);
}
