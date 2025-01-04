
CREATE TABLE EMPL (numemp int Primary key,nom varchar(15),salaire int,commission int);
insert into empl values(7369,'Smith',2500,500);
insert into empl values(7698,'FORD',3500,4500);




CREATE OR REPLACE package pack1 as
function fonct1(v_noempl int) return int;
function fonct2 return int;
procedure proc1(v_numero varchar);
end pack1;


create or replace package body pack1 as
function fonct1(v_noempl int) return int is
v_salaire int;
begin
select salaire into v_salaire from empl where numemp=v_noempl;
return v_salaire;
end;

function fonct2 return int is
v_salaire_max int;
begin
select max(salaire) into v_salaire_max from empl;
return v_salaire_max;
end;

procedure proc1(v_numero varchar) is 
v_nom varchar(15);
v_salaire int;
begin 
select nom,salaire into v_nom,v_salaire from empl where numemp=v_numero;
dbms_output.put_line(v_nom||' '||v_salaire);
end;
end pack1;

set SERVEROUTPUT ON
declare
v_sal int;
begin
v_sal:=pack1.fonct1(7369);
DBMS_OUTPUT.PUT(v_sal);
end;

SELECT pack1.fonct1(7369) AS salaire from empl;

select pack1.fonct2() as salairemax from empl;

exec pack1.proc1(7369);




CREATE OR REPLACE package packtp5 as
FUNCTION operation(a number, b number, v_operation varchar) return number;
FUNCTION Factorielle(p_MyNum INTEGER)RETURN NUMBER ;
procedure p_printEmps;
procedure PROC_Voitures(nb_voitures number, v_marque varchar);
   
end packtp5;


create or replace package body packtp5 as
FUNCTION operation(a number, b number, v_operation varchar) return number is
v_result double precision;
BEGIN
if v_operation = 'division' THEN 
v_result:= a/b;
DBMS_OUTPUT.put_line(v_result);
elsif v_operation = 'addition' THEN
v_result:= a+b;
DBMS_OUTPUT.put_line(v_result);
elsif (v_operation = 'multiplication') THEN 
v_result:= a*b;
DBMS_OUTPUT.put_line(v_result);
elsif (v_operation = 'soustraction') THEN 
v_result:= a-b;
DBMS_OUTPUT.put_line(v_result);
ELSE 
DBMS_OUTPUT.put_line('Saisies incorrecte');
END IF;
RETURN v_result;
END;

FUNCTION Factorielle(p_MyNum INTEGER)RETURN
NUMBER is
BEGIN 
IF p_MyNum = 1 THEN 
RETURN 1; 
ELSE 
RETURN(p_MyNum * Factorielle(p_MyNum-1));
END IF;
END;


procedure PROC_Voitures(nb_voitures number, v_marque varchar) is
v_immatriculation Voitures.immatriculation%type;
begin
for i in 1..nb_voitures loop
v_immatriculation:=v_marque||i;
insert into voiture values (v_immatriculation,v_marque);
end loop;
end;

PROCEDURE p_printEmps IS 
CURSOR c_emp IS select * from client; 
r_emp c_emp%ROWTYPE; 
BEGIN 
OPEN c_emp; 
LOOP 
FETCH c_emp INTO r_emp; 
EXIT WHEN c_emp%NOTFOUND; 
DBMS_OUTPUT.PUT_LINE(r_emp.nom); 
END LOOP; 
CLOSE c_emp; 
END; 
end packtp5;

SET SERVEROUTPUT ON;
DECLARE
v_a number(10):= &number1;
v_b number(10):= &number2;
v_operation VARCHAR2(20):= '&operation';
v_result DOUBLE PRECISION;
begin  
v_result:=operation(v_a,v_b,v_operation);
end;

set serveroutput on;
declare
i number:=1;
nb_voitures number:=&nombre_de_voiture;
marque varchar2(20):= '&marque';
immatriculation varchar2(20);
begin
PROC_Voitures(nb_voitures, marque);
end;



set SERVEROUTPUT ON
begin 
p_printEmps;
end;


Set SERVEROUTPUT ON
Declare
v_n number :='&Entrez_un_nombre_svp';
v_result number;
BEGIN
V_result:=Factorielle(v_n);
DBMS_OUTPUT.PUT_LINE(v_result);
END;
