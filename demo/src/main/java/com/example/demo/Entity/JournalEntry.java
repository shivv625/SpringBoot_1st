package com.example.demo.Entity;

import jdk.jfr.DataAmount;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;

@Document (collection = "journal_db")
@Data
@NoArgsConstructor
public class JournalEntry {
    @Id
    private String id;
    private String name;
    private String content;
    private LocalDateTime data;

}