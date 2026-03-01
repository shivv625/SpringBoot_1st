package com.example.demo.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document (collection = "journal_db")
@Data
@NoArgsConstructor
public class JournalEntry {
    @Id
    private String id;
    private String name;
    private String content;
    private LocalDateTime date;

}