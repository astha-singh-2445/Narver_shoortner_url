package com.singh.astha.narvar;

import com.singh.astha.narvar.models.Counter;
import com.singh.astha.narvar.repositories.CounterRepository;
import com.singh.astha.narvar.utils.Constants;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@Configuration
public class AppConfigurations {
    private final CounterRepository counterRepository;

    public AppConfigurations(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void afterContextRefresh() {
        boolean urlCounterExists = counterRepository.existsById(Constants.URL_COUNTER);
        if (!urlCounterExists) {
            Counter counter = Counter.builder()
                    .id(Constants.URL_COUNTER)
                    .sequenceValue(1000L)
                    .build();
            counterRepository.save(counter);
        }
    }
}
