package me.yaowx.entity;

import lombok.Data;

import javax.persistence.*;


/**
 * @author: Yaowx
 * @email worthyyao@qq.com
 * @Date: 2021/12/1
 */
@Data
@Entity
public class TUser {

    @Id
    @GeneratedValue
    private Integer id ;
    private String name;
    private String password;
    private String phone;
}
