#!/bin/bash
#set -e
mysql -u root $MYSQL_ROOT_PASSWORD -e "
  CREATE DATABASE $MYSQL_DATABASE;
  CREATE USER $MYSQL_USER WITH ENCRYPTED PASSWORD '$MYSQL_PASSWORD';
  GRANT ALL PRIVILEGES ON DATABASE $MYSQL_DATABASE TO $MYSQL_USER;"
