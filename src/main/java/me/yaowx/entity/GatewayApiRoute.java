package me.yaowx.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author: Yaowx
 * @email worthyyao@qq.com
 * @Date: 2021/12/1
 */
@Data
@Entity
public class GatewayApiRoute {
    @Id
    private String id;

    private String path;

    private String serviceId;

    private String url;

    private Integer retryable;

    private Integer enabled;

    private Integer stripPrefix;

    private String apiName;
}
