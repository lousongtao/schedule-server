ALTER TABLE `schedule`.`user`
ADD COLUMN `shift_times` INT NOT NULL DEFAULT 0 COMMENT '每周班次, 可以排班几次' AFTER `available`;
