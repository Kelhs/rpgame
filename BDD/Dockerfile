FROM mysql:8.0.29
ADD ./init.d /docker-entrypoint-initdb.docker

ENV MYSQL_ROOT_PASSWORD root
ENV MYSQL_INITDB_SKIP_TZINFO 1