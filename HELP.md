# Getting Started

## Springboot and Liberty POC

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

### Springboot profiles running

Define the application-<env>.xml you want to run in the VM Options

* dev: for local development, fill the VM Options with this
```
-Dspring.profiles.active=dev
```

* default: you dont need config the VM Options. The server will recognize it automatically


### H2 localhost

You can see the H2 database through this endpoint

```
http://localhost:<PORT>/h2-ui
```

### Logstash Logging Env Vars

Use the Env vars in the App Config as 

* false: when you want the default format
```
LOGGER_JSON_FORMAT=false
```

* true: when you want the JSON format
```
LOGGER_JSON_FORMAT=true
```

### User Authorization

When you start the auth config by default you will receive the password by the server.
This password will be showed in the Console after the message below in UUID format and it is owned by the user called **user**
```
Using generated security password: c30bea2b-0890-4e95-902e-db55d03b8a00
```

### Docker at a glance

Install the docker compose files using the command below:

* for default docker-compose.yml
```
docker-compose up -d
```
* for redis 
```
docker-compose -f docker-compose-redis.yml up -d
```
* for rabbitmq
```
docker-compose -f docker-compose-rabbitmq.yml up -d
```

### Docker at a glance on Ubuntu Linux

* for redis on a common folder
```
sudo docker compose -f docker-compose-redis.yml up -d
```
* for redis on a specific folder
```
sudo docker compose -f /home/<your_user>/docker-compose-redis.yml up -d
```
* for rabbitmq on a common folder
```
sudo docker compose -f docker-compose-rabbitmq.yml up -d
```
* for rabbitmq on a specific folder
```
sudo docker compose -f /home/<your_user>/docker-compose-rabbitmq.yml up -d
```
* remove all containers
```
sudo docker rm -f $(sudo docker ps -a -q)
```
* remove all volumes
```
sudo docker volume prune
```
