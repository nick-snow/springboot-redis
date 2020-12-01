package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class User implements Serializable {
	private Long id;
    private String guid;
    private String name;
    private String age;
    private Date createTime;
}
