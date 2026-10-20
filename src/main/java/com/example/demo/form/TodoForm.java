package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Data;

@Data 
public class TodoForm {
    
    private Integer id;

    @NotBlank(message = "タイトルを入力してください")
    @Size(max = 100, message = "100字以内で入力してください")
    private String title;// フィールド名
    private Boolean completed;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;


    /*public TodoForm(){
        
    }*/
    
    
}
