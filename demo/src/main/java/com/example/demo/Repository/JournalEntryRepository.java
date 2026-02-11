package com.example.demo.Repository;

import com.example.demo.Entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntry , String> {
}
