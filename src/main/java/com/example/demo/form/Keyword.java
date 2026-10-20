package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Keyword {

    @NotBlank(message = "ワードを入力してください")
    @Size(max = 100, message = "100字以内で入力してください")
    private String keyword;//　フィールド名

    public void setKeyword(String keyword){
        this.keyword = keyword;
    }
    public String getKeyword(){
        return keyword;
    }
}
