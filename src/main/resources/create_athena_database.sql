CREATE DATABASE applications

CREATE EXTERNAL TABLE IF NOT EXISTS applications.application_roles (
  `application_name` string,
  `user_id` string,
  `first_name` string,
  `last_name` string,
  `role` string 
)
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe'
WITH SERDEPROPERTIES (
  'serialization.format' = ',',
  'field.delim' = ','
) LOCATION 's3://groman-athena-example/application_roles/'
TBLPROPERTIES (
  'has_encrypted_data'='false',
  'skip.header.line.count'='1'
  );