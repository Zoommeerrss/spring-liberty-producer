# spring-liberty-producer

## Visão Geral

O `spring-liberty-producer` é um projeto Java Liberty que atua como um produtor de mensagens para um sistema de mensageria. 

Ele é construído utilizando o framework Spring Boot, o que facilita a configuração e o desenvolvimento do aplicativo. 

O projeto é leve e eficiente, permitindo a produção de mensagens de forma rápida e confiável.

Para persistencia de mensagens vai utilizar RabbitMQ e para Cache de dados vai utilizar Redis.

## Redis!

Aqui sera possivel validar dados em cache com 2 opcoes de Redis:

- Redis Standalone: Uma instância única do Redis, ideal para ambientes de desenvolvimento ou produção de pequeno porte. Ele é fácil de configurar e gerenciar, mas não oferece alta disponibilidade ou escalabilidade.
- Redis Sentinel: Uma configuração de alta disponibilidade do Redis que utiliza um conjunto de instâncias para garantir a continuidade do serviço. O Redis Sentinel monitora as instâncias e realiza failover automático em caso de falhas, garantindo que o sistema continue funcionando mesmo em situações de falha.

Para executar a imagem do Redis Sentinel vamos utilizar o docker e nele criaremos uma subnet para isolar os containers do Redis.

A configuração do Redis Sentinel com subnet será feita atraves do arquivo `docker-compose.yml`, onde definiremos os serviços do Redis Sentinel e a subnet para isolar os containers.

```
networks:
  local-net:
    driver: bridge
    ipam:
      config:
        - subnet: 172.21.0.0/16 <-- Aqui é onde definimos a subnet para os servicos
```

Depois disso, basta referenciar a subnet nos serviços que deverao subir no docker-compode:

```
    redis-insight:
    image: redis/redisinsight:latest
    container_name: redis-insight
    ports:
      - "5540:5540"
    networks:
      local-net:
        ipv4_address: 172.21.0.9 <-- Aqui é onde referenciamos a subnet para o container do Redis Insight
```


## Referências
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Mockito](https://site.mockito.org/)
- [Documentação oficial do projeto](./HELP.md)
