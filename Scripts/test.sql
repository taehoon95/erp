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