package me.yaowx.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author: Yaowx
 * @email worthyyao@qq.com
 * @Date: 2021/12/6
 */
@Data
@Entity
public class GrayReleaseConfig {

    @Id
    private Integer id;

    private String serviceId;

    private String path;

    private Integer enableGrayRelease;
}
