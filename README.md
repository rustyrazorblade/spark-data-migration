This project is meant to accompany this blog post:

Please use it as a reference for this repo.

Required CQL for keyspace & table setup:

```
CREATE KEYSPACE tutorial WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

use tutorial;

CREATE TABLE tutorial.user (
name text primary key,
favorite_food text
);

create table tutorial.food_to_user_index ( food text, user text, primary key (food, user));
```

Let's put in some sample data:

```
insert into user (name, favorite_food) values ('Jon', 'bacon');
insert into user (name, favorite_food) values ('Luke', 'steak');
insert into user (name, favorite_food) values ('Al', 'salmon');
insert into user (name, favorite_food) values ('Chris', 'chicken');
insert into user (name, favorite_food) values ('Rebecca', 'bacon');
insert into user (name, favorite_food) values ('Patrick', 'brains');
insert into user (name, favorite_food) values ('Duy Hai', 'brains');
```

After running the spark job, we expect the following output:

```
cqlsh:tutorial> select * from food_to_user_index ;

 food    | user
---------+---------
  salmon |      Al
   steak |    Luke
 chicken |   Chris
  brains | Duy Hai
  brains | Patrick
   bacon |     Jon
   bacon | Rebecca

(7 rows)

cqlsh:tutorial> select * from food_to_user_index where food = 'brains';

 food   | user
--------+---------
 brains | Duy Hai
 brains | Patrick

(2 rows)

```
