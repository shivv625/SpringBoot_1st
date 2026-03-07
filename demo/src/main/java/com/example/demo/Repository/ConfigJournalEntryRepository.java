package com.example.demo.Repository;

import com.example.demo.Entity.ConfigJournalAppEntity;
import com.example.demo.Entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalEntryRepository extends MongoRepository<ConfigJournalAppEntity, String> {
}
