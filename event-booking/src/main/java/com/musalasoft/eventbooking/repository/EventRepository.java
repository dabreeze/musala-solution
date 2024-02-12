package com.musalasoft.eventbooking.repository;

import com.musalasoft.eventbooking.models.Category;
import com.musalasoft.eventbooking.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
     List<Event> findEventByUserId(Long userId);
     Event findEventByTicketId(Long ticketId);
     List<Event> findEventByStartDate(LocalDate startDate);

    void deleteById(Long aLong);

    List<Event> findByNameContainingIgnoreCase(String name);
    List<Event> findByCategory(Category category);


    @Query("SELECT e FROM Event e " +
            "WHERE (:name IS NULL OR e.name LIKE %:name%) " +
            "AND (:startDate IS NULL OR e.startDate >= :startDate) " +
            "AND (:endDate IS NULL OR e.endDate <= :endDate) " +
            "AND (:category IS NULL OR e.category = :category)")
    List<Event> findByFilters(@Param("name") String name,
                              @Param("startDate") LocalDate startDate,
                              @Param("endDate") LocalDate endDate,
                              @Param("category") Category category);


}
