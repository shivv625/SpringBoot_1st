package com.example.demo.Service;

import com.example.demo.Entity.JournalEntry;
import com.example.demo.Entity.User;
import com.example.demo.Repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    private JournalEntry journalEntry;
    private UserService userService;

    public void saveEntry(JournalEntry journalEntry, String userName){
        User user = userService.findByUserName(userName);
        journalEntry.setData(LocalDateTime.now());
       JournalEntry saved= journalEntryRepository.save(journalEntry);
       user.getJournalEntries().add(saved);
       userService.saveEntry(user);
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
       return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(String id) {
        return journalEntryRepository.findById(id);
    }


    public void deleteById(String id, String userName){
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
    }

}
