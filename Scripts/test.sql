select * from title;

-- 해당 직책을 가지고 있는 사원목록을 검색
select empname, empno
  from employee e 
  join title t
    on e.title  = t.tno
 where tno = 5; 
  
select * from employee;
select * from department;

select empno,empname from employee e join department d on e.dept = d.deptno where deptno = 2;  

select * from employee where empno = 1003;

-- pass 길이 확인
-- 단방향 함수(Hash:MD5)
select password('aaa123;l123/.,.,'), length(password('aaa123;l123/.,.,')) from dual;

-- emp_detail insert 
INSERT INTO erp.emp_detail (empno, pass, pic, gender, hiredate) VALUES(?, ?, ?, ?, ?);

select empno,pic,gender,hiredate,pass from emp_detail where empno = 1003;

update emp_detail set pic = ? ,gender = ?, hiredate = ?, pass = password(?) where empno = ?;

delete from emp_detail where empno = 1003;

select * from emp_detail;

