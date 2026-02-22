package com.example.demo.Controller;
import java.time.LocalDateTime;
import java.util.*;
import java.util.ArrayList;



import com.example.demo.Entity.JournalEntry;
import com.example.demo.Entity.User;
import com.example.demo.Service.JournalEntryService;
import com.example.demo.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;

    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName) {
        User user = userService.findByUserName(userName);
        List<JournalEntry> all = user.getJournalEntries();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> creatEntry(@RequestBody JournalEntry myEntry,@PathVariable String userName){
       try {
           journalEntryService.saveEntry(myEntry, userName);
           return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
       } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

    @GetMapping("id/{myid}")
    public ResponseEntity<JournalEntry> getJoutnalEntryByID(@PathVariable String myid){
        Optional<JournalEntry> journalEntry = journalEntryService.findById(myid);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<?> DeleteJoutnalEntryByID(@PathVariable String myId, @PathVariable String userName){
        journalEntryService.deleteById(myId,userName);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("id/{userName}/{myId}")
    public ResponseEntity<?> updateJournalById(
            @PathVariable String id,
            @RequestBody JournalEntry newEntry,
            @PathVariable String userName) {
        JournalEntry old = journalEntryService.findById(id).orElse(null);
        if (old != null) {
            old.setName(
                    newEntry.getName() != null && !newEntry.getName().equals("")
                            ? newEntry.getName()
                            : old.getName()
            );
            old.setContent(
                    newEntry.getContent() != null && !newEntry.getContent().equals("")
                            ? newEntry.getContent()
                            : old.getContent()
            );
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
