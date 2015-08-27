#!/bin/bash

#version 0.1

PIDFile="application.pid"
source ./config/environment.properties

check_if_pid_file_exists(){
 if [ ! -f $PIDFile ]
  then
  	echo "Process stopped (already)"
#   echo "PID file not found: $PIDFile"
   exit 1
 fi
}

check_if_process_is_running(){
 if ps -p "$(print_process)" > /dev/null
 then
     return 0
 else
     return 1
 fi
}

print_process(){
    echo "$(cat $PIDFile)"
}

case "$1" in
  status)
    check_if_pid_file_exists
    if check_if_process_is_running
    then
      echo "$(print_process)" " is running"
    else
      echo "Process not running: "$(print_process)" "
    fi
    ;;
  stop)
    check_if_pid_file_exists
    if ! check_if_process_is_running
    then
      echo "Process "$(print_process)" stopped (already)"
      exit 0
    fi
    kill -TERM "$(print_process)"
    echo -ne "Waiting for process to stop"
    NOT_KILLED=1
    for i in {1..20}; do
	  check_if_pid_file_exists
      if check_if_process_is_running
      then
        echo -ne "."
        sleep 1
      else
        NOT_KILLED=0
      fi
    done
    echo
    if [ $NOT_KILLED = 1 ]
    then
      echo "Cannot kill process "$(print_process)" "
      exit 1
    fi
    echo "Process stopped"
    ;;
  start)
    if [ -f $PIDFile ] && check_if_process_is_running
    then
      echo "Process "$(print_process)" already running"
      exit 1
    fi
    nohup java $JAVA_OPTS $2 -jar ./jar/*.jar > ./log/nohup.log 2>&1 &
    echo "Process started"
    ;;
  *)
    echo "Usage: $0 {start|stop|status}"
    exit 1
esac

exit 0
