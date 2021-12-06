/**
  网关动态路由表
 */
CREATE TABLE `gateway_api_route`
(
    `id`           varchar(50)  NOT NULL,
    `path`         varchar(255) NOT NULL,
    `service_id`   varchar(50)  DEFAULT NULL,
    `url`          varchar(255) DEFAULT NULL,
    `retryable`    tinyint(1) DEFAULT NULL,
    `enabled`      tinyint(1) NOT NULL,
    `strip_prefix` int(11) DEFAULT NULL,
    `api_name`     varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO gateway_api_route (id, path, service_id, retryable, strip_prefix, url, enabled)
VALUES ('order-service', '/order/**', 'order-service', 0, 1, NULL, 1);


/**
  灰度发布表
 */
CREATE TABLE `gray_release_config`
(
    `id`                  int(11) NOT NULL AUTO_INCREMENT,
    `service_id`          varchar(255) DEFAULT NULL,
    `path`                varchar(255) DEFAULT NULL,
    `enable_gray_release` int(11) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO gray_release_config (id, service_id, path, enable_gray_release)
VALUES (1, 'order-service', '/order', 1);