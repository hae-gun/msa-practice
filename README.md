# msa-practice

kafka connectors 설정


http://localhost:8083/connectors

source-connect
-POST
{
    "name" : "my-source-connect",
    "config" : {
        "connector.class" : "io.confluent.connect.jdbc.JdbcSourceConnector",
        "connection.url":"jdbc:mysql://localhost:3306/mydb",
        "connection.user":"root",
        "connection.password":"qwer1234",
        "mode": "incrementing",
        "incrementing.column.name" : "id",
        "table.whitelist":"mydb.users",
        "topic.prefix" : "my_topic_",
        "tasks.max" : "1"
        }
}

sink-connect
-POST
{
    "name" : "my-sink-connect",
    "config" : {
        "connector.class" : "io.confluent.connect.jdbc.JdbcSinkConnector",
        "connection.url":"jdbc:mysql://localhost:3306/mydb",
        "connection.user":"root",
        "connection.password":"qwer1234",
        "auto.create": "true",
        "auto.evolve" : "true",
        "delete.enabled":"false",
        "tasks.max" : "1",
        "topics" : "my_topic_users"
        }
}

커넥터 정보확인

-GET 127.0.0.1:8083/connectors/

상세정보 확인

-GET 127.0.0.1:8083/connectors/{커넥터명}/status

