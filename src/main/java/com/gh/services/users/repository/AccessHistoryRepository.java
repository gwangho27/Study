package com.gh.services.users.repository;

import com.gh.services.users.domain.AccessHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessHistoryRepository extends JpaRepository<AccessHistory, Long> {
}
