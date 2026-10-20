package com.example.demo.form;
 
import java.util.List;
import java.util.ArrayList;
import jakarta.validation.Valid;

public class TodoListForm {
    
    private List<@Valid TodoForm> todos = new ArrayList<>();
    
    public List<TodoForm> getTodos(){ return todos; }

    public void setTodos(List<TodoForm> todos){ this.todos = todos; }
}

