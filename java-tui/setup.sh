#!/bin/bash
apt-get update -y && apt-get install -y openjdk-21-jdk
useradd -m -s /bin/bash player
cd /home/player && git clone https://github.com/jmburke1/into_the_office app
chmod +x /home/player/app/gradlew
chown -R player:player /home/player
su - player -c "cd /home/player/app && ./gradlew clean shadowJar --no-daemon"
# Clear the terminal canvas and bring your TUI to the front of the screen
clear
echo "Launching Console Application..."

# Drop down out of root privileges and run your interactive runner script
su - player -c "cd /home/player/app && ./just_run_it.sh"