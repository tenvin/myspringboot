package com.twg.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;

/**
 * Created by twg on 2017/6/15.
 */

@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "用户名不能为空")
    private String name;

    @Min(value = 18,message = "年龄应该大与18")
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
