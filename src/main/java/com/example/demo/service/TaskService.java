package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.tasks;
import com.example.demo.mapper.TaskMapper;
import java.time.LocalDateTime;
import com.example.demo.form.TodoListForm;
import com.example.demo.form.TodoForm;


@Service 
public class TaskService {
    private final TaskMapper taskMapper;
    public TaskService(TaskMapper taskMapper){
        this.taskMapper = taskMapper;
    }

    // 1ページでn件分取得
    private static final int PAGE_SIZE = 5;
    public List<tasks> findPage(int page){

        int offset_value = (page - 1) * PAGE_SIZE;

        return taskMapper.findPage(PAGE_SIZE, offset_value);
    }
    // テーブルの件数をすべての取得↑とセットで使う（totalPages）
    public int getTotalPages(){
        int totalCount = taskMapper.count();

        return (int) Math.ceil((double) totalCount / PAGE_SIZE);// まだ何かいてるのかわからない。
    }


    //　全件取得
    public List<tasks> findAll(){
        return taskMapper.findAll();
    }
    
    public void add(String title, Boolean completed, LocalDateTime created_at){// ここももしかして時間は自動で格納されるなら定義しなくてもいい？
        tasks task = new tasks();
        task.setTitle(title);
        task.setCompleted(completed);
        task.setCreated_at(created_at);
        taskMapper.insert(task);// taskMapperのトロッコにtaskインスタンスのデータの箱を載せてそのままMapper.xmlに持ってく？
    }

    public void update(TodoListForm todoListForm){

        //    ↓ココ注意。id, title, ~, がはいるTodoFormクラス’型’のって指定する。
        for(TodoForm items : todoListForm.getTodos()){

            tasks task = new tasks();
            task.setId(items.getId());
            task.setTitle(items.getTitle());
            task.setCompleted(items.getCompleted());
            task.setCreated_at(items.getCreated_at());
            task.setUpdated_at(items.getUpdated_at());

            taskMapper.update(task);
        }
        

        
    }

    public void delete(Integer id){

        taskMapper.delete(id);
    }

    public List<tasks> findByTitle(String keyword){

        return taskMapper.findByTitle(keyword);

        
    }

}
