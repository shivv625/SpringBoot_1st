package com.example.demo.cache;

import com.example.demo.Entity.ConfigJournalAppEntity;
import com.example.demo.Repository.ConfigJournalEntryRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class AppCache {

    public enum keys{
        WEATHER_API;
    }

    @Autowired
    private ConfigJournalEntryRepository configJournalEntryRepository;

    public Map<String,String> appCache ;

    @PostConstruct
    public void init(){
        appCache= new HashMap<>();
        try {
            List<ConfigJournalAppEntity> all = configJournalEntryRepository.findAll();
            for (ConfigJournalAppEntity configJournalAppEntity : all){
                appCache.put(configJournalAppEntity.getKey(),configJournalAppEntity.getValue());
            }
        } catch (Exception e) {
            log.warn("Could not load config from MongoDB during startup. Cache will be empty until next refresh. Error: {}", e.getMessage());
        }
    }
}
