--create Role 
INSERT INTO `leave_system`.`role` (`id`, `role_name`) VALUES ('1', 'ADMIN');
INSERT INTO `leave_system`.`role` (`id`, `role_name`) VALUES ('2', 'EMPLOYEE');
INSERT INTO `leave_system`.`role` (`id`, `role_name`) VALUES ('3', 'HR');

--Create LeaveType
INSERT INTO `leave_system`.`leave_type` (`id`, `leave_type_name`) VALUES ('1', 'Annual');
INSERT INTO `leave_system`.`leave_type` (`id`, `leave_type_name`) VALUES ('2', 'Casual');
INSERT INTO `leave_system`.`leave_type` (`id`, `leave_type_name`) VALUES ('3', 'Medical');

--Create User
INSERT INTO `leave_system`.`users` (`id`, `created_at`, `updated_at`, `email`, `password`, `user_name`, `user_status`, `role_id`) VALUES ('1', '2019-07-23 12:52:48', '2019-07-23 12:52:48', 'admin@gmail.com', '$2a$10$3/Fvf7Hva7x17oe9lzLb3e4Pr18qkU5/.eb0WcAcT61uQ9QJrHmmW', 'admin', '0', '1');
INSERT INTO `leave_system`.`users` (`id`, `created_at`, `updated_at`, `email`, `password`, `user_name`, `user_status`, `role_id`) VALUES ('2', '2019-07-23 12:52:48', '2019-07-23 12:52:48', 'Employee@gmail.com', '$2a$10$3/Fvf7Hva7x17oe9lzLb3e4Pr18qkU5/.eb0WcAcT61uQ9QJrHmmW', 'employee', '0', '2');
INSERT INTO `leave_system`.`users` (`id`, `created_at`, `updated_at`, `email`, `password`, `user_name`, `user_status`, `role_id`) VALUES ('3', '2019-07-23 12:52:48', '2019-07-23 12:52:48', 'hr@gmail.com', '$2a$10$3/Fvf7Hva7x17oe9lzLb3e4Pr18qkU5/.eb0WcAcT61uQ9QJrHmmW', 'HR', '0', '3');