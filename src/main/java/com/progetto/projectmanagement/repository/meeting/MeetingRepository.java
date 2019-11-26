package com.progetto.projectmanagement.repository.meeting;

import com.progetto.projectmanagement.domain.meeting.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository  extends JpaRepository<Meeting,Integer> {
}
