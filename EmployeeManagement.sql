use employeedb;

delimiter $$

create procedure update_or_insert(in  date_time_value bigint)
begin

IF EXISTS (select * from user_login_record where timestamps=date_time_value) THEN
	SET SQL_SAFE_UPDATES=0;
     UPDATE user_login_record SET counts = counts + 1 WHERE timestamps=date_time_value;
     SET SQL_SAFE_UPDATES=1;
ELSE 
    insert into user_login_record(counts,timestamps) values(1,date_time_value);
END IF;

end $$

drop procedure update_or_insert;