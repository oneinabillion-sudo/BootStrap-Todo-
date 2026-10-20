package com.example.demo.mapper;

//下にマッパーで必要なインポートをする
import org.apache.ibatis.annotations.Mapper;
import com.example.demo.entity.tasks;
import java.util.List;
// ワード検索で引数を受け取る用のインポート↓
import org.apache.ibatis.annotations.Param;


@Mapper 
public interface TaskMapper{


    void createTable();

    void insert(tasks task);// 1件追加

    List<tasks> findPage(@Param("limit_value") int limit_value,
                         @Param("offset_value") int offset_value);
    int count();

    List<tasks> findAll(); 

    void update(tasks task);

    void delete(Integer id);

    List<tasks> findByTitle(@Param("keyword") String keyword);
    
}
