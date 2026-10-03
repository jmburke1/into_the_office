#!/bin/bash
# Clear the terminal canvas and bring your TUI to the front of the screen
clear
echo "Launching Console Application..."

# Drop down out of root privileges and run your interactive runner script
su - player -c "cd /home/player/app && ./just_run_it.sh"