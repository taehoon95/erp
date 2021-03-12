
-- 계정 권한 부여
grant all 
	   on erp.*
	   to 'user_erp'@'localhost'
	   identified by 'rootroot';
	   
-- file 권한(backup, load) -- root만 권한 부여 가능
grant file 
   on *.*
   to 'user_erp'@'localhost';