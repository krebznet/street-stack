CREATE KEYSPACE IF NOT EXISTS dunkstreet WITH replication = {'class': 'SimpleStrategy',
    'replication_factor' : 2};

CREATE TABLE IF NOT EXISTS "dunkstreet"."stream_data_var_snapshot"
(
    "stream"   int,
    "date"     date,
    "time" time,
    "entity"   int,
    "var"      int,
    "value" double,
    PRIMARY KEY ((stream, date, entity, var), time)
);

CREATE TABLE IF NOT EXISTS "dunkstreet"."stream_data_signal_type"
(
    "stream" int,
    "date" date,
    "time" time,
    "entity" int,
    "signal" int,
    PRIMARY KEY ((stream, date, signal), entity,time)
);


CREATE TABLE IF NOT EXISTS "dunkstreet"."stream_data_signal_entity"
(
    "stream" int,
    "date" date,
    "time" time,
    "entity" int,
    "signal" int,
    PRIMARY KEY ((stream, date,entity),signal,time)
);

CREATE TABLE IF NOT EXISTS "dunkstreet"."stream_stat_entity"
 (
    "stream" int,
    "date" date,
    "entity" int,
    "stat" int,
    "value" double,
    "time" time,
    PRIMARY KEY ( (stream,entity,stat),date )
);
