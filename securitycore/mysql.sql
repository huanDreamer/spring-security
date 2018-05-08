CREATE TABLE `security-demo`.`t_user` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(128) NOT NULL,
  `passsword` VARCHAR(128) NOT NULL,
  `status` TINYINT(4) UNSIGNED NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
COMMENT = '用户信息';
