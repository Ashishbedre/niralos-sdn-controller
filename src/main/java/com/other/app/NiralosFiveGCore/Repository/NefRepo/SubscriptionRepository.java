package com.other.app.NiralosFiveGCore.Repository.NefRepo;


import com.other.app.NiralosFiveGCore.model.NefStatus.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<SubscriptionStatus, Long> {
    SubscriptionStatus findFirstByOrderByIdDesc();
}
