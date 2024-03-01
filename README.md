## Bauen der container images

quarkus-backend und quarkus-validator images können im jeweiligen pfad mit folgendem Befehl gebaut werden.

```
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

## Einrichten der Hilfs-Cotainer

Erstellen des Netzwerks

```
podman network create messaging-nw
```

### Erstellen eines MariaDB containers auf Podman

Erstellen einen mariadb containers.

```
podman run -d --name=messaging-mariadb -p 9001:3306 --network messaging-nw -e MARIADB_USER=backend -e MARIADB_PASSWORD=<YourPassHere> -e MARIADB_ROOT_PASSWORD=<YourPassHere> mariadb:latest
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
