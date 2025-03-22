#!/bin/sh

LOG_FILE=/var/log/container.log
mkdir -p /var/log

echo "======== Container started ========" | tee -a "$LOG_FILE"
echo "Date: $(date)" | tee -a "$LOG_FILE"
echo "Running as user: $(whoami)" | tee -a "$LOG_FILE"
echo "UID: $(id -u), GID: $(id -g)" | tee -a "$LOG_FILE"
echo "Groups: $(id)" | tee -a "$LOG_FILE"
echo "Hostname: $(hostname)" | tee -a "$LOG_FILE"
echo "Command: $@" | tee -a "$LOG_FILE"
echo "===============================" | tee -a "$LOG_FILE"

exec "$@" >> "$LOG_FILE" 2>&1
