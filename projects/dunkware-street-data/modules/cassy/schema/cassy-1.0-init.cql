CREATE KEYSPACE IF NOT EXISTS dunkstreet WITH replication = {'class': 'SimpleStrategy',
    'replication_factor' : 2};

CREATE TYPE "dunkstreet"."entity_var_stats"
    (
        "stats" Map<int,double>,
        "times" Map<int,time>
    );

CREATE TABLE IF NOT EXISTS "dunkstreet"."entity_stats"
(
    "stream"    int,
    "entity"    int,
    "date"      date,
    "signals" frozen<Map<int,int>>,
    "vars"  frozen<Map<int,"dunkstreet"."entity_var_stats">>,

    PRIMARY KEY ((stream, entity), date),

);

CREATE TABLE IF NOT EXISTS "dunkstreet"."session_entity_stat"
(
    "stream"  int,
    "date"    date,
    "entity"  int,
    "stat"    int,
    "element" int,
    "value"   double,
    "time"    time,

    PRIMARY KEY ( (stream, date), entity, element, stat)

);



CREATE TABLE IF NOT EXISTS "dunkstreet"."session_entity_stats"
(

    "stream"    int,
    "date"      date,
    "entity"    int,
    "signals"   frozen<Map<int,int>>,
    "vars"      frozen<Map<int,"dunkstreet"."entity_var_stats">>,

    PRIMARY KEY ((stream, date), entity),

);


CREATE TABLE IF NOT EXISTS "dunkstreet"."stream_session"
(
    "stream"  int,
    "date"    date,
    "vars"    frozen<List<int>>,
    "entities"frozen<List<int>>,
    "stats"   frozen<List<int>>,
    "signals" frozen<List<int>>,
    "start"   time,
    "stop"    time,

    PRIMARY KEY ((stream), date),
	
)
WITH CLUSTERING ORDER BY (date DESC);








