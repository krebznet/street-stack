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

CREATE TABLE IF NOT EXISTS dunkstreet.session_signal_entity (
    "stream" int,
    "date" date,  // This column only stores the date, used for partitioning
    "entity" int,
    
   
    "time" time,  // This column stores the full timestamp including time down to the second
    "data" blob,
    PRIMARY KEY ((entity, stream, date), time)
) WITH CLUSTERING ORDER BY (time ASC);

CREATE TABLE IF NOT EXISTS dunkstreet.session_signal_type (
    "signal" int,
    "stream" int,
    "date" date,
    "entity" int,
    "time" time,
    vars frozen<Map<int,double>>,
    PRIMARY KEY ((signal, stream, date), time, entity)
) WITH CLUSTERING ORDER BY (signal_time ASC);

CREATE TABLE IF NOT EXISTS dunkstreet.entity_daily_signals (
    entity_id int,
    signal_date date,
    signal_time timestamp,
    signal_id int,
    stream_id int,
    vars frozen<Map<int,double>>,
    PRIMARY KEY ((entity_id, signal_date), signal_time, signal_id, stream_id)
) WITH CLUSTERING ORDER BY (signal_time ASC);


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








