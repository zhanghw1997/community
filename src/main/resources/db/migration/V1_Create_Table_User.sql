create table account
(
  id          int auto_increment
    primary key,
  name        varchar(100) null,
  account_id  varchar(100) null,
  token       char(50)     null,
  user_image  varchar(100) null,
  create_time bigint       null,
  update_time bigint       null
)
  comment '用户信息';