package com.example.demo.entity;

import java.time.LocalDateTime;
import lombok.Data;

@Data 
public class tasks {
  private Integer id;
  private String title;
  private Boolean completed = false;
  private LocalDateTime created_at;
  private LocalDateTime updated_at;

  /*public tasks(){
    
  }*/
  
}


