#!/bin/bash

p_pid_roll=0
i_pid_roll=0
d_pid_roll=0
p_pid_pitch=0
i_pid_pitch=0
d_pid_pitch=0

for i in {1..1000}
do 
   printf 'doing %s\n' $(echo "scale=4; $i/1000" | bc )
   sudo ./config_maker roll 0$(echo "scale=3; $i/1000" | bc ) 0.0 0.0 0.0 400
   echo "press enter if ready"
   read VARNAME
   
   echo "enter rating  and press enter"
   read rating
   echo $rating >> database.txt
   killall pielectricseagles
   

done


