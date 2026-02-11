package com.example.demo.Controller;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;



import com.example.demo.Entity.JournalEntry;
import com.example.demo.Service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll(){
        return null;
    }

    @PostMapping
    public boolean creatEntry(@RequestBody JournalEntry myEntry){
        journalEntryService.saveEntry(myEntry);
        return true;
    }

    @GetMapping("id/{myid}")
    public JournalEntry getJoutnalEntryByID(@PathVariable long myid){
        return null;
    }

    @DeleteMapping("id/{myid}")
    public JournalEntry DeleteJoutnalEntryByID(@PathVariable long myid){
        return null;
    }

    @PutMapping("id/{myid}")
    public JournalEntry UpdateJoutnalEntryByID(@PathVariable long myid, @RequestBody JournalEntry myEntry){
        return null;
    }
}
