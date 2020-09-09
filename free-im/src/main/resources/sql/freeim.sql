create table `sys_user`(
	`user_id` BIGINT NOT NULL,
	`user_name` VARCHAR(10) NOT NULL,
	`sex` VARCHAR(1) NOT NULL,
	`account` VARCHAR(11) NOT NULL,
	`password` VARCHAR(64) NOT NULL,
	`mail` VARCHAR(30) NOT NULL,
	`city` VARCHAR(10),
	`age` INT,
	`nickname` VARCHAR(10),
	`create_date` DATETIME NOT NULL,
	`update_date` DATETIME NOT NULL,
	PRIMARY KEY(`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `message_send_receive`(
	`send_user_id` BIGINT NOT NULL,
	`receive_user_id` BIGINT NOT NULL,
	`content_type` VARCHAR(1) NOT NULL,
	`create_date` DATETIME NOT NULL,
	`content` VARCHAR(1000) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `friends_group_manager`(
	`group_id` BIGINT NOT NULL,
	`belong_user_id` BIGINT NOT NULL,
	`group_name` VARCHAR(256) NOT NULL,
	`create_date` DATETIME NOT NULL,
	`update_date` DATETIME NOT NULL,
	PRIMARY KEY(`group_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `user_friends`(
	`user_id` BIGINT NOT NULL,
	`friend_user_id` BIGINT NOT NULL,
	`group_id` BIGINT NOT NULL,
	`remark` VARCHAR(10),
	`create_date` DATETIME NOT NULL,
	`update_date` DATETIME NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
