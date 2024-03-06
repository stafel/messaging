## Bauen der container images

quarkus-backend und quarkus-validator images können im jeweiligen pfad mit folgendem Befehl gebaut werden.

```
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

## Einrichten der Hilfs-Cotainer

### Erstellen des Netzwerks

```
podman network create messaging-nw
```

### Erstellen des Redpanda message brokers

```
podman run -d --name=redpanda --network messaging-nw \
-p 18081:18081 -p 18082:18082 -p 19092:19092 -p 19644:9644 \
-v redpanda-data:/var/lib/redpanda/data \
docker.redpanda.com/redpandadata/redpanda:latest \
redpanda start --kafka-addr internal://0.0.0.0:9092,external://0.0.0.0:19092 \
--advertise-kafka-addr internal://redpanda-0:9092,external://localhost:19092 \
--pandaproxy-addr internal://0.0.0.0:8082,external://0.0.0.0:18082 \
--advertise-pandaproxy-addr internal://redpanda-0:8082,external://localhost:18082 \
--schema-registry-addr internal://0.0.0.0:8081,external://0.0.0.0:18081 \
--rpc-addr redpanda-0:33145 \
--advertise-rpc-addr redpanda-0:33145 \
--mode dev-container \
--smp 1 \
--default-log-level=info
```

dazugehörige konsole:

```
podman run --rm --name=redpanda-console --network messaging-nw \
--entrypoint /bin/sh -p 8080:8080 \
-e CONFIG_FILEPATH=/tmp/config.yml \
-e CONSOLE_CONFIG_FILE="`cat panda_console_config.yaml`" \
docker.redpanda.com/redpandadata/console:latest \
-c 'echo "$CONSOLE_CONFIG_FILE" > /tmp/config.yml; cat /tmp/config.yml ;/app/console'
```

### Erstellen eines MariaDB containers auf Podman

Erstellen einen mariadb containers.

```
podman run -d --name=messaging-mariadb -p 9001:3306 --network messaging-nw -e MARIADB_USER=backend -e MARIADB_PASSWORD=<YourPassHere> -e MARIADB_ROOT_PASSWORD=<YourPassHere> mariadb:10.11
```

Erstellen der Datenbank und vergeben der Rechte auf der podman-container-Konsole.

```
mariadb -u root -p
# Login with the root password here
create database blog_backend;
create user backend@blog_backend identified by '<YourPassHere>';
GRANT ALL PRIVILEGES ON blog_backend.* To backend;
```

*In einem produktiven system darf der backend user nicht vollprivilegien haben*

