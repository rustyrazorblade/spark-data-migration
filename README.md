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

create table tutorial.food_to_user_index ( food text, name text, primary key (food, name));
```

Some sample data:

```
insert into user (user_id, name, favorite_food) values (1, 'Jon', 'bacon');
insert into user (user_id, name, favorite_food) values (2, 'Luke', 'steak');
insert into user (user_id, name, favorite_food) values (3, 'Al', 'salmon');
insert into user (user_id, name, favorite_food) values (4, 'Chris', 'chicken');
insert into user (user_id, name, favorite_food) values (5, 'Rebecca', 'bacon');
insert into user (user_id, name, favorite_food) values (6, 'Patrick', 'brains');
insert into user (user_id, name, favorite_food) values (7, 'D', 'brains');
```

After running the spark job, we expect the following output:


