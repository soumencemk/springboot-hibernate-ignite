# Ignite - Spring Boot - Hibernate L2 cache mode

## Install DB and Ignite

### 1. Postgres
```bash
docker run -d -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 postgres
```
### 2. Ignite
```bash
docker run -d -p 10800:10800 apacheignite/ignite 
```
or
```bash
docker run -d -p 10800:10800 gridgain/community
```
