package com.example.demo.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.demo.service.TaskService;
import org.springframework.validation.BindingResult;

import com.example.demo.form.Keyword;
import com.example.demo.form.TodoForm;
import com.example.demo.form.TodoListForm;
import org.springframework.web.bind.annotation.RequestParam;


@Controller 
public class TasksController {
    
    private final TaskService taskService;
    public TasksController(TaskService taskService){
        this.taskService = taskService;
    }
    // トップページ表示ハンドラーメソッド
    @GetMapping("/task")
    public String showTaskPage(
        @RequestParam(defaultValue = "1") int page,
        @ModelAttribute TodoForm todoForm, 
        @ModelAttribute Keyword keyword,Model model) {
    
        int totalPages = taskService.getTotalPages();
        //                          ↑Serviceから総ページ数を取得

        if(page < 1){
            page = 1;
        }
        if (totalPages > 0 && page > totalPages) {
            page = totalPages;
        }
        
        model.addAttribute("tasks", taskService.findPage(page));
        model.addAttribute("page", page);
        model.addAttribute("totalPages", totalPages);
        
        return "task";
    }

    // ページのRequestParamを受け取りn件分のレコードを返す表示メソッド
    @PostMapping("/getPage")
    public String getPage(@RequestParam(defaultValue = "1") int page, Model model,
                          @ModelAttribute TodoForm todoForm,@ModelAttribute Keyword keyword)
    {
        
        return "task";
    }

    // 追加メソッド
    @PostMapping("/add")
    public String takeBackVali
     (@Validated @ModelAttribute TodoForm todoForm,
        BindingResult result, @ModelAttribute Keyword keyword,Model model)  
    {
        // 入力エラーがある場合
        if(result.hasErrors()){
            return "task";
        }
        // 正常な場合に1件分追加する。
        taskService.add( todoForm.getTitle(), todoForm.getCompleted(), todoForm.getCreated_at() );
        return "redirect:/task";
    }

    // 一覧表示メソッド
    @GetMapping ("/selectAll")
    public String showAll(@ModelAttribute TodoForm todoForm,@ModelAttribute Keyword keyword ,Model model) {
    
        model.addAttribute("tasks", taskService.findAll());

        return "task";
    }
    


    // 編集画面に遷移するメソッド
    @GetMapping("/edit")
    public String showEditPage(@ModelAttribute TodoListForm todoListForm, @ModelAttribute TodoForm todoForm, Model model) {
        model.addAttribute("tasks", taskService.findAll());

        return "edit";
    }

    // 更新処理メソッド
    @PostMapping("/tasks/update")
    public String update(@Validated @ModelAttribute TodoListForm todoListForm, BindingResult result,
            Model model){// 本文の completed
                        
        if(result.hasErrors()){
            model.addAttribute("tasks", taskService.findAll());
            return "edit";
        }

        // 正常に更新出来たら、更新後確認画面に遷移する
        taskService.update(todoListForm);  

       model.addAttribute("tasks", taskService.findAll());
        return "chgconfirm";
    }

    // 1レコード文削除メソッド
    @PostMapping("tasks/{id}/delete")
    public String postMethodName(@PathVariable Integer id) {
        
        taskService.delete(id);

        return "redirect:/edit";
    }

    
    @PostMapping("/pointSearch")
    public String takeBackSearchRs(@Validated @ModelAttribute Keyword keyword,
         BindingResult result, @ModelAttribute TodoForm todoForm,
        Model model){
        
        if(result.hasErrors()){// エラー時の処理
            return "task";
        }
        // 検索処理

        model.addAttribute("tasks", taskService.findByTitle(keyword.getKeyword()));
    
        return "task";
    }    
    
}

