package com.dunkware.stream.data.cassy.schema;

//TODO: AVINASHANV-08 Cassandra Schema 
/**
 * The schema has to be designed with careful thought on how you want to search data
 * you never want to search daa that is in more than 1 partiion, partiions is how 
 * cassandra scales, we have a 6 node cluster deployed and when adding new nodes
 * it will rebalance itself. bit concept on using composite keys 
 * 
 * description of capturing signals, we have two tables first we have all the signal 
 * triggers for a entity for a specific session or date 
 * 
 * the PRIMARY KEY first ( ) scoped are the fields that define the parition so 
 * in this case we have the entity id, the stream id and the date, a new session
 * will create another partition to search for signals across multiple days we will 
 * async execute many queries at once.  you don't want partiions to get more that 10MB
 * having a million partitions is not an issue size is
 * 
 * CREATE TABLE IF NOT EXISTS "timestream"."session_signal_entity"
(
    entity int,
    stream int,
    date date,
    time time,
    vars frozen<map<int, double>>,
    PRIMARY KEY ((entity, stream, date), time)
) WITH CLUSTERING ORDER BY (time ASC);

second table stores all signals for a signal type in a session so we can search for signals on a given signal type
that is created in script . if you look at the primary key composite its similar to above but uses the 
signal type id instread of the entity id 

CREATE TABLE IF NOT EXISTS "timestream"."session_signal_type"
(
    "signal" int,
    "stream" int,
    "date" date,
    "entity" int,
    "time" time,
    vars frozen<Map<int,double>>,
    PRIMARY KEY ((signal, stream, date), time, entity)
) WITH CLUSTERING ORDER BY (time ASC);

the WITH CLUSTERING ORDER BY defines in what order the data will be sorted it helps us make queries 
faster in this case we are sorting by time so its quick to get signal triggers with a time ranage

 */
public class CassySchema {

	/**
	 * DROP KEYSPACE IF EXISTS timestream;
CREATE KEYSPACE IF NOT EXISTS timestream WITH replication = {'class': 'SimpleStrategy', 'replication_factor' : 3};

CREATE TYPE "timestream"."entity_var_stats"
(
    "stats" Map<int,double>,
    "times" Map<int,time>
);

CREATE TABLE IF NOT EXISTS "timestream"."entity_stats"
(
    "stream" int,
    "entity" int,
    "date" date,
    "signals" frozen<Map<int,int>>,
    "vars" frozen<Map<int,"timestream"."entity_var_stats">>,

    PRIMARY KEY ((stream, entity), date),
);

CREATE TABLE IF NOT EXISTS "timestream"."session_entity_stat"
(
    "stream" int,
    "date" date,
    "entity" int,
    "stat" int,
    "element" int,
    "value" double,
    "time" time,

    PRIMARY KEY ( (stream, date), entity, element, stat)
);

CREATE TABLE IF NOT EXISTS "timestream"."session_entity_stats"
(
    "stream" int,
    "date" date,
    "entity" int,
    "signals" frozen<Map<int,int>>,
    "vars" frozen<Map<int,"timestream"."entity_var_stats">>,

    PRIMARY KEY ((stream, date), entity),
);


CREATE TABLE IF NOT EXISTS "timestream"."session_signal_entity"
(
    entity int,
    stream int,
    date date,
    time time,
    vars frozen<map<int, double>>,
    PRIMARY KEY ((entity, stream, date), time)
) WITH CLUSTERING ORDER BY (time ASC);

CREATE TABLE IF NOT EXISTS "timestream"."session_signal_type"
(
    "signal" int,
    "stream" int,
    "date" date,
    "entity" int,
    "time" time,
    vars frozen<Map<int,double>>,
    PRIMARY KEY ((signal, stream, date), time, entity)
) WITH CLUSTERING ORDER BY (time ASC);

CREATE TABLE IF NOT EXISTS "timestream"."entity_daily_signals"
(
    entity_id int,
    signal_date date,
    signal_time time,
    signal_id int,
    stream_id int,
    vars frozen<Map<int,double>>,
    PRIMARY KEY ((entity_id, signal_date,stream_id), signal_time, signal_id)
) WITH CLUSTERING ORDER BY (signal_time ASC);


CREATE TABLE IF NOT EXISTS "timestream"."stream_session"
(
    "stream" int,
    "date" date,
    "vars"  frozen<List<int>>,
    "entities" frozen<List<int>>,
    "stats" frozen<List<int>>,
    "signals" frozen<List<int>>,
    "start" time,
    "stop" time,
    "version" double,
    PRIMARY KEY ((stream), date)
) WITH CLUSTERING ORDER BY (date DESC);

CREATE TABLE IF NOT EXISTS timestream.entity_snapshots (
    stream_id int,
    date date,
    entity_id int,
    time time,
    variables map<int, double>,
    signals map<int, int>,
    PRIMARY KEY ((stream_id, date, entity_id), time)
) WITH CLUSTERING ORDER BY (time ASC);

	 */
}
