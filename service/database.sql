CREATE TABLE `t_comment` (
                             `id` varchar(32) NOT NULL,
                             `publish_user` varchar(32) NOT NULL,
                             `subject_id` varchar(32) NOT NULL,
                             `create_time` datetime DEFAULT NULL,
                             `content` mediumtext,
                             `version` int(10) DEFAULT '1',
                             `deleted` int(1) DEFAULT '0',
                             PRIMARY KEY (`id`),
                             KEY `fk3` (`publish_user`),
                             KEY `fk4` (`subject_id`),
                             CONSTRAINT `fk3` FOREIGN KEY (`publish_user`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                             CONSTRAINT `fk4` FOREIGN KEY (`subject_id`) REFERENCES `t_post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `t_message` (
                             `id` varchar(32) NOT NULL,
                             `name` varchar(45) NOT NULL,
                             `email` varchar(45) NOT NULL,
                             `subject` varchar(100) NOT NULL,
                             `msg` mediumtext NOT NULL,
                             `create_time` datetime DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `t_module` (
                            `id` int(2) NOT NULL DEFAULT '-1',
                            `topic` varchar(45) NOT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `fmodule_name_UNIQUE` (`id`),
                            UNIQUE KEY `topic_UNIQUE` (`topic`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `t_post` (
                          `id` varchar(32) NOT NULL,
                          `publish_user` varchar(32) NOT NULL,
                          `create_time` datetime DEFAULT NULL,
                          `update_time` datetime DEFAULT NULL,
                          `title` varchar(200) DEFAULT NULL,
                          `briefing` varchar(400) DEFAULT NULL,
                          `module` int(2) DEFAULT '-1',
                          `version` int(10) DEFAULT '1',
                          `deleted` int(1) DEFAULT '0',
                          `detail` longtext COMMENT '帖子内容',
                          `type` int(1) DEFAULT '-1',
                          PRIMARY KEY (`id`),
                          UNIQUE KEY `id_UNIQUE` (`id`),
                          KEY `uuu` (`publish_user`),
                          KEY `vvv_idx` (`module`),
                          CONSTRAINT `uuu` FOREIGN KEY (`publish_user`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                          CONSTRAINT `vvv` FOREIGN KEY (`module`) REFERENCES `t_module` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `t_star` (
                          `user_id` varchar(32) NOT NULL,
                          `subject_id` varchar(32) NOT NULL,
                          PRIMARY KEY (`user_id`,`subject_id`),
                          KEY `fk2` (`subject_id`),
                          CONSTRAINT `fk1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                          CONSTRAINT `fk2` FOREIGN KEY (`subject_id`) REFERENCES `t_post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `t_tag` (
                         `name` varchar(100) NOT NULL,
                         `id` varchar(32) NOT NULL,
                         PRIMARY KEY (`name`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `t_user` (
                          `id` varchar(32) NOT NULL,
                          `fullname` varchar(45) NOT NULL,
                          `username` varchar(45) NOT NULL,
                          `email` varchar(45) NOT NULL,
                          `password` varchar(45) NOT NULL,
                          `gender` int(1) DEFAULT '-1',
                          `minor_photo` mediumblob,
                          `create_time` datetime DEFAULT NULL,
                          `version` int(10) DEFAULT '1',
                          `deleted` int(1) DEFAULT '0',
                          `photo` mediumblob,
                          PRIMARY KEY (`id`),
                          UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

