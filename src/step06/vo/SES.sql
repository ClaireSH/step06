create table human (
jumin varchar(20) primary key,
type varchar(20) not null,
name varchar(20) not null,
age number(3))

create table trainee(
jumin varchar(20) not null, 
constraint trainee_jumin_fk foreign key (jumin) references human(jumin) on delete cascade,
stdNo varchar(20) not null 
);

create table professor(
jumin varchar(20) not null, 
constraint professor_jumin_fk foreign key (jumin) references human(jumin) on delete cascade,
major varchar(20) not null 
);

create table staff(
jumin varchar(20) not null, 
constraint staff_jumin_fk foreign key (jumin) references human(jumin) on delete cascade,
field varchar(20) not null 
);


create table shares(
       ssn varchar(15) not null,
   constraint shares_ssn_fk foreign key (ssn) references customer(ssn) on delete cascade,
       symbol varchar(8) not null,
   constraint shares_symbol_fk foreign key (symbol) references stock(symbol) on delete cascade,
       quantity number not null) ;
create table shares(
ssn varchar(15) not null,
   constraint shares_ssn_fk foreign key (ssn) references customer(ssn) on delete cascade,
       symbol varchar(8) not null,
   constraint shares_symbol_fk foreign key (symbol) references stock(symbol) on delete cascade,
       quantity number not null) ;
