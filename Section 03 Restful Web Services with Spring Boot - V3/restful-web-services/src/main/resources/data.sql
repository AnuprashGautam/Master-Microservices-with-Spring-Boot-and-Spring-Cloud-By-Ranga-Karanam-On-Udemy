insert into user_details(id, birth_date, name)
values(10001, current_date(), 'Anuprsh Gautam'),
	  (10002, current_date(), 'Anirudh Sharma'),
	  (10003, current_date(), 'Rachit Singh'),
	  (10004, current_date(), 'Ujjawal Vats');
	  
insert into post(id,description,user_id)
values(20001, 'I want to learn AWS.', 10001),
	  (20002, 'I want to learn Spring.', 10001),
	  (20003, 'I want to learn Angular.', 10001),
	  (20004, 'I want to learn Cybersecurity.', 10004);